/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="asDom" description="Assign current result to scripting variable, as W3C DOM"
 *  body-content="empty"
 */
public class AsDomTag extends com.marklogic.jsptaglib.xquery.statement.rt.AsDomTag
{
	public void setVar (String var) throws JspException
	{
		super.setVar ((String) ExpressionEvaluatorManager.evaluate ("var", var, String.class, this, pageContext));
	}

	public void setScope (String scope) throws JspException
	{
		super.setScope ((String) ExpressionEvaluatorManager.evaluate ("scope", scope, String.class, this, pageContext));
	}
}
