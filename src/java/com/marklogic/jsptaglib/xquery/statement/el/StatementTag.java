/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.el;

import com.marklogic.xdmp.XDMPDataSource;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="statement" description="Statement Tag, Expression Language enabled"
 *  body-content="JSP"
 */
public class StatementTag extends com.marklogic.jsptaglib.xquery.statement.rt.StatementTag
{
	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setVar (String var) throws JspException
	{
		super.setVar ((String) ExpressionEvaluatorManager.evaluate ("var", var, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setScope (String scope) throws JspException
	{
		super.setScope ((String) ExpressionEvaluatorManager.evaluate ("scope", scope, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setQuery (String query) throws JspException
	{
		super.setQuery ((String) ExpressionEvaluatorManager.evaluate ("query", query, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setDataSource (String dataSource) throws JspException
	{
		super.setDataSource ((XDMPDataSource) ExpressionEvaluatorManager.evaluate ("dataSource", dataSource, XDMPDataSource.class, this, pageContext));
	}
}
