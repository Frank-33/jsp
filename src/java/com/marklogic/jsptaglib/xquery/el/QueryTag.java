/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import com.marklogic.xdmp.XDMPDataSource;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="query" description="Query tag, Expression Language enabled"
 * @see com.marklogic.jsptaglib.xquery.rt.QueryTag
 * @see com.marklogic.jsptaglib.xquery.common.Result
 * @see com.marklogic.jsptaglib.xquery.common.ResultItem
 * @see com.marklogic.jsptaglib.xquery.statement.rt.StatementTag
 * @see com.marklogic.jsptaglib.xquery.statement.rt.ResultSequenceTag
 */
public class QueryTag extends com.marklogic.jsptaglib.xquery.rt.QueryTag
{
	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setImportModule (String importModule) throws JspException
	{
		super.setImportModule ((String) ExpressionEvaluatorManager.evaluate ("importModule", importModule, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setImportModuleNamespace (String importModuleNamespace) throws JspException
	{
		super.setImportModuleNamespace ((String) ExpressionEvaluatorManager.evaluate ("importModuleNamespace", importModuleNamespace, String.class, this, pageContext));
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
	public void setSeparator (String separator) throws JspException
	{
		super.setSeparator ((String) ExpressionEvaluatorManager.evaluate ("separator", separator, String.class, this, pageContext));
	}

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
	public void setDataSource (String dataSource) throws JspException
	{
		super.setDataSource ((XDMPDataSource) ExpressionEvaluatorManager.evaluate ("dataSource", dataSource, XDMPDataSource.class, this, pageContext));
	}
}
