/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;


/**
 * @jsp:tag name="getIndex" body-content="empty"
 *  description="Output current index value as a string"
 */
public class GetIndexTag extends com.marklogic.jsptaglib.xquery.statement.rt.GetIndexTag
{
	public void setZeroBased (String zeroBased) throws JspException
	{
		super.setZeroBased (((Boolean) ExpressionEvaluatorManager.evaluate ("zeroBased", zeroBased, Boolean.class, this, pageContext)).booleanValue());
	}
}
