/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;


/**
 * @jsp:tag name="unSetDataSource" body-content="empty"
 *  description="Unset DataSource, Expression Language capable."
 * @see com.marklogic.jsptaglib.xquery.rt.UnSetDataSourceTag
 */
public class UnSetDataSourceTag extends com.marklogic.jsptaglib.xquery.rt.UnSetDataSourceTag
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
}
