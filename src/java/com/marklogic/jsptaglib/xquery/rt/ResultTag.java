/*
 * Copyright (c)2004 Mark Logic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The use of the Apache License does not indicate that this project is
 * affiliated with the Apache Software Foundation.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import java.io.IOException;
import java.io.BufferedReader;

/**
 * @jsp:tag name="result" description="Invoked once per item in the result"
 *  body-content="JSP"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class ResultTag extends BodyTagSupport implements TryCatchFinally
{
	private String var = null;
	private String scope = null;

	private XDBCResultSequence xdbcResultSequence;
	private int index = 0;
	private boolean readerFetched = false;

	// -----------------------------------------------------------

	public void release ()
	{
		var = null;
		scope = null;
		xdbcResultSequence = null;
		index = 0;
		readerFetched = false;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setVar (String var) throws JspException
	{
		this.var = var;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setScope (String scope) throws JspException
	{
		this.scope = scope;
	}

	// -----------------------------------------------------------

	protected BufferedReader getCurrentReader() throws JspException, XDBCException
	{
		if (var != null) {
			throw new JspException ("Cannot stream if var attribute is set on result tag");
		}

		if (readerFetched) {
			throw new JspException ("Attempt to fetch stream reader twice");
		}

		readerFetched = true;

		return (xdbcResultSequence.nextReader());
	}

	// -----------------------------------------------------------

	public int doStartTag() throws JspException
	{
		if (TagSupport.findAncestorWithClass (this, ExecuteTag.class) == null) {
			throw new JspException ("result tag must be nested in an execute tag");
		}

		// reset body's content, otherwise it could result in an error if container is pooling
		// tag handlers (see bug 26863 for more details)
		super.bodyContent = null;

		try {
			ExecuteTag executeTag = (ExecuteTag) findAncestorWithClass (this, ExecuteTag.class);

			xdbcResultSequence = executeTag.executeQuery();

			if (xdbcResultSequence.hasNext() == false) {
				return SKIP_BODY;
			}

			index = 0;

			setupNextItem (xdbcResultSequence, index);
		} catch (XDBCException e) {
			throw new JspException ("Executing query: " + e, e);
		}

		return EVAL_BODY_BUFFERED;
	}

	public int doAfterBody() throws JspException
	{
		try {
			if (xdbcResultSequence.hasNext()) {
				index++;
				setupNextItem (xdbcResultSequence, index);

				return EVAL_BODY_BUFFERED;
			}
		} catch (XDBCException e) {
			throw new JspException ("Iterating ResultSequenceTag, index=" + index + ": " + e, e);
		}

		return EVAL_PAGE;
	}

	public int doEndTag() throws JspException
	{
		if (var != null) {
			AttributeHelper.removeScopedAttribute (pageContext, var, scope);
		}

		try {
			if ((getBodyContent() != null) && (getPreviousOut() != null)) {
				getPreviousOut().write (getBodyContent().getString());
			}
		} catch (IOException e) {
			throw new JspException ("Writing end-tag result: " + e, e);
		} finally {
			try {
				xdbcResultSequence.close();
			} catch (XDBCException e) {
				e.printStackTrace();
			}
		}

		TagPropertyHelper.assignAncestorProperty (this, ExecuteTag.class, "queryExecuted", true);

		// we have to call this guy manually now
		// with the spec clarification
		release();

		return EVAL_PAGE;
	}

	// -----------------------------------------------------------

	private void setupNextItem (XDBCResultSequence xdbcResultSequence, int index)
		throws XDBCException
	{
		if (var == null) {
			if ((readerFetched == false) && (index != 0)) {
				xdbcResultSequence.next();
			}

			readerFetched = false;
		} else {
			xdbcResultSequence.next();

			AttributeHelper.setScopedAttribute (pageContext, var,
				new ResultItemImpl (xdbcResultSequence, index), scope);
		}
	}

	// -----------------------------------------------------------

	public void doCatch (Throwable throwable) throws Throwable
	{
		throw throwable;
	}

	public void doFinally ()
	{
		if (xdbcResultSequence != null) {
			try {
				xdbcResultSequence.close();
			} catch (XDBCException e) {
				// nothing
			}
		}
	}
}
