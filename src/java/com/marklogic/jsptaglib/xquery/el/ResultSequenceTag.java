/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="resultSequence" description="XDBC Result Sequence Tag"
 *  body-content="JSP"
 */
public class ResultSequenceTag extends com.marklogic.jsptaglib.xquery.rt.ResultSequenceTag
{
	public void setVar (String var) throws JspException
	{
		super.setVar ((String) ExpressionEvaluatorManager.evaluate ("var", var, String.class, this, pageContext));
	}

	public void setScope (String scope) throws JspException
	{
		super.setScope ((String) ExpressionEvaluatorManager.evaluate ("scope", scope, String.class, this, pageContext));
	}

	public void setName (String name) throws JspException
	{
		super.setName ((String) ExpressionEvaluatorManager.evaluate ("name", name, String.class, this, pageContext));
	}

	public void setLoop (String loop) throws JspException
	{
		super.setLoop (((Boolean) ExpressionEvaluatorManager.evaluate ("loop", loop, Boolean.class, this, pageContext)).booleanValue());
	}
}
