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
import com.marklogic.xqrunner.XQException;
import com.marklogic.xqrunner.XQResult;
import com.marklogic.xqrunner.XQResultItem;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import java.io.IOException;
import java.io.Reader;

/**
 * @jsp:tag name="result" description="Invoked once per item in the result"
 *  body-content="JSP"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class ResultTag extends BodyTagSupport implements TryCatchFinally
{
	private String var = null;
	private String scope = null;

	private XQResult result = null;
	private XQResultItem currentItem = null;
	private boolean readerFetched = false;

	// -----------------------------------------------------------

	public void release ()
	{
		var = null;
		scope = null;
		result = null;
		currentItem = null;
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

	protected Reader getCurrentReader() throws JspException
	{
		if (var != null) {
			throw new JspException ("Cannot stream if var attribute is set on result tag");
		}

		if (readerFetched) {
			throw new JspException ("Attempt to fetch stream reader twice");
		}

		readerFetched = true;

		try {
			return currentItem.asReader();
		} catch (XQException e) {
			throw new JspException ("Marshalling item value as Reader: " + e, e);
		}
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

			if (var == null) {
				result = executeTag.executeQueryStreaming();
			} else {
				result = executeTag.executeQuery();
			}

			if ( ! setupNextItem()) {
				return SKIP_BODY;
			}
		} catch (XQException e) {
			throw new JspException ("Executing query: " + e, e);
		}

		return EVAL_BODY_BUFFERED;
	}

	public int doAfterBody() throws JspException
	{
		try {
			if (setupNextItem()) {
				return EVAL_BODY_BUFFERED;
			}
		} catch (XQException e) {
			throw new JspException ("Marshalling next item value: " + e, e);
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
		}

		TagPropertyHelper.assignAncestorProperty (this, ExecuteTag.class, "queryExecuted", true);

		// we have to call this guy manually now
		// with the spec clarification
		release();

		return EVAL_PAGE;
	}

	// -----------------------------------------------------------

	private boolean setupNextItem() throws XQException
	{
		readerFetched = false;
		currentItem = result.nextItem();

		if (currentItem == null) {
			return (false);
		}

		if (var != null) {
			AttributeHelper.setScopedAttribute (pageContext, var,
				new ResultItemAdapter (currentItem), scope);
		}

		return (true);
	}

	// -----------------------------------------------------------

	public void doCatch (Throwable throwable) throws Throwable
	{
		throw throwable;
	}

	public void doFinally ()
	{
		release();
	}
}
