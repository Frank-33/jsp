/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="query" description="Contains XQuery code to be executed"
 */
public class QueryTag extends com.marklogic.jsptaglib.xquery.rt.QueryTag
{
	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setQuery (String query) throws JspException
	{
		super.setQuery ((String) ExpressionEvaluatorManager.evaluate ("query", query, String.class, this, pageContext));
	}
}
