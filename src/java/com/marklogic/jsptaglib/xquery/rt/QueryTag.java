/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.TagPropertyHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @jsp:tag name="query" description="Contains XQuery code to be executed"
 */
public class QueryTag extends BodyTagSupport
{
	private String query = null;

	// ------------------------------------------------------------

	public void release ()
	{
		query = null;
	}

	// ------------------------------------------------------------

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setQuery (String query) throws JspException
	{
		this.query = query;
	}

	// ------------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (TagSupport.findAncestorWithClass (this, ExecuteTag.class) == null) {
			throw new JspException ("The query tag must be nested in an execute tag");
		}

		TagPropertyHelper.assignAncestorProperty (this, ExecuteTag.class, "query", getQueryString());

		release();

		return (EVAL_PAGE);
	}

	// ------------------------------------------------------------

	protected String getQueryString()
	{
		if (query != null) {
			return query;
		}

		return (getBodyContent().getString());
	}
}
