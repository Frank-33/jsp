/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="isNode" description="Evaluates body if current result is a Node"
 */
public class IsNodeTag extends com.marklogic.jsptaglib.xquery.statement.rt.IsNodeTag
{
	public void setCondition (String condition) throws JspException
	{
		super.setCondition (((Boolean) ExpressionEvaluatorManager.evaluate ("condition", condition, Boolean.class, this, pageContext)).booleanValue());
	}
}
