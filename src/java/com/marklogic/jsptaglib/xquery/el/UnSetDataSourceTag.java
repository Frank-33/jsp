/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;


/**
 * @jsp:tag name="unSetDataSource" body-content="empty"
 *  description="Clear a datasource attribute.  If a variable
 *  name is provided, that variable will be cleared.  If
 *  no name is given, the default name used by setDataSource
 *  will be cleared.  If a scope is provided, the attribute
 *  in that scope will be cleared.  Otherwise the default
 *  PageContext search will be applied.  This tag is generally
 *  only useful for session and application scope."
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
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
