/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="isNotNode" description="Evaluates body if current result is not a Node"
 */
public class IsNotNodeTag extends com.marklogic.jsptaglib.xquery.statement.rt.IsNotNodeTag
{
	public void setCondition (String condition) throws JspException
	{
		super.setCondition (((Boolean) ExpressionEvaluatorManager.evaluate ("condition", condition, Boolean.class, this, pageContext)).booleanValue());
	}
}
