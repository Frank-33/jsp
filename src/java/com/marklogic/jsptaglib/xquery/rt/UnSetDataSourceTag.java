/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.AttributeHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @jsp:tag name="unSetDataSource" body-content="empty"
 *  description="Clear a datasource attribute.  If a variable
 *  name is provided, that variable will be cleared.  If
 *  no name is given, the default name used by setDataSource
 *  will be cleared.  If a scope is provided, the attribute
 *  in that scope will be cleared.  Otherwise the default
 *  PageContext search will be applied.  This tag is generally
 *  only useful for session and application scope."
 */
public class UnSetDataSourceTag extends TagSupport
{
	private String scope = null;
	private String var = SetDataSourceTag.ML_DEFAULT_DATASOURCE_VAR;

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setScope (String scope) throws JspException
	{
		this.scope = scope;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setVar (String var) throws JspException
	{
		this.var = var;
	}

	public int doStartTag () throws JspException
	{
		AttributeHelper.removeScopedAttribute (pageContext, var, scope);

		return (EVAL_PAGE);
	}
}
