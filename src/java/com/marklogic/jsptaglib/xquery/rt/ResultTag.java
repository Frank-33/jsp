/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.jsptaglib.xquery.XdbcHelper;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;

import org.jdom.Document;
import org.w3c.dom.Node;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.io.Reader;

/**
 * @jsp:tag name="result" description="XDBC Result Sequence Tag"
 *  body-content="JSP"
 */
public class ResultTag extends BodyTagSupport
{
	private XDBCResultSequence xdbcResultSequence;
	private String var = null;
//	private boolean loop = true;
//	private String name = null;
	private String scope = null;

	private int index = 0;
	private boolean currentResultFetched = false;
	private Object currentResultObject = null;
	private Reader currentResultReader = null;

	// -----------------------------------------------------------

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
//	public void setLoop (boolean loop)
//	{
//		this.loop = loop;
//	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
//	public void setName (String name) throws JspException
//	{
//		this.name = name;
//	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setScope (String scope) throws JspException
	{
		this.scope = scope;
	}

	public void release ()
	{
		xdbcResultSequence = null;
//		loop = true;
		var = null;
//		name = null;
		scope = null;
		index = 0;
	}

	// -----------------------------------------------------------

	/**
	 * This private method assures that the cursor has been properly advanced.
	 * It should be called by all the getCurrentResult* methods,
	 * except getCurrentResultReader, before doing anything else.  The
	 * opaque object returned by next() is only used as a marker.
	 * @throws XDBCException Passed through from next().
	 */
	private void getCurrentResultObject ()
		throws XDBCException
	{
		if (currentResultReader != null) {
			throw new IllegalStateException ("getCurrentResultReader() previously called");
		}

		if (currentResultObject == null) {
			currentResultObject = xdbcResultSequence.next();
			currentResultFetched = true;
		}
	}

	protected Reader getCurrentResultReader()
		throws XDBCException
	{
		if (currentResultObject != null) {
			throw new IllegalStateException ("getCurrentResultAs*() previously called");
		}

		if (currentResultReader == null) {
			currentResultReader = xdbcResultSequence.nextReader();
			currentResultFetched = true;
		}

		return currentResultReader;
	}

	protected Object getCurrentResultAsObject()
		throws XDBCException
	{
		getCurrentResultObject();

		return (XdbcHelper.resultAsObject (xdbcResultSequence));
	}

	protected String getCurrentResultAsString()
		throws XDBCException
	{
		if (isCurrentResultNode()) {
			return (xdbcResultSequence.getNode().asString());
		}

		return (getCurrentResultAsObject().toString());
	}

	protected Node getCurrentResultAsDom()
		throws XDBCException
	{
		getCurrentResultObject();
		validateResultIsNode ();

		return (XdbcHelper.resultAsW3cDom (xdbcResultSequence));
	}

	protected Document getCurrentResultAsJDom()
		throws XDBCException
	{
		getCurrentResultObject();
		validateResultIsNode ();

		return (XdbcHelper.resultAsJDom (xdbcResultSequence));
	}

	protected boolean isCurrentResultNode()
		throws XDBCException
	{
		if (currentResultFetched == false) {
			getCurrentResultObject();
		}

		return (xdbcResultSequence.getItemType() == XDBCResultSequence.XDBC_Node);
	}

	protected int getIndex ()
	{
		return index;
	}

	// -----------------------------------------------------------

	public int doStartTag() throws JspException
	{
		if (TagSupport.findAncestorWithClass (this, ExecuteTag.class) != null) {
			throw new JspException ("result tag must be nested in an execute tag");
		}

		// reset body's content, otherwise it could result in an error if container is pooling
		// tag handlers (see bug 26863 for more details)
		super.bodyContent = null;

		try {
			ExecuteTag statementTag = (ExecuteTag) findAncestorWithClass (this, ExecuteTag.class);
			xdbcResultSequence = statementTag.executeQuery();

			if (xdbcResultSequence.hasNext() == false) {
				return SKIP_BODY;
			}

			index = 0;
			currentResultFetched = true;
			stepToNextElement();
		} catch (XDBCException e) {
			throw new JspException ("Executing query", e);
		}

		return EVAL_BODY_BUFFERED;
	}

	public int doAfterBody() throws JspException
	{
		try {
			if (xdbcResultSequence.hasNext()) {
				stepToNextElement();
				return EVAL_BODY_BUFFERED;
			}
		} catch (XDBCException e) {
			throw new JspException ("Iterating ResultSequenceTag, index=" + index, e);
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
			throw new JspException ("Writing end-tag result", e);
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

	private void validateResultIsNode ()
		throws XDBCException
	{
		if (xdbcResultSequence.getItemType() != XDBCResultSequence.XDBC_Node) {
			throw new XDBCException ("Result is not a Node (result type: "
				+ getCurrentResultAsObject().getClass ().getName () + ")");
		}
	}

	// TODO: Create Item object, set var if non-null
	private void stepToNextElement ()
		throws XDBCException
	{
		if (currentResultFetched == false) {
			xdbcResultSequence.next();
		}

		if (currentResultReader != null) {
			try {
				currentResultReader.close();
			} catch (IOException e) {
				// ignore, may have already been closed
			}
		}

		currentResultFetched = false;
		currentResultObject = null;
		currentResultReader = null;
		index++;
	}
}
