/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="streamItem" body-content="empty"
 *  description="Send current element to output as a string"
 */
public class StreamItemTag extends com.marklogic.jsptaglib.xquery.rt.StreamItemTag
{
	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setBufferSize (String bufferSize) throws JspException
	{
		super.setBufferSize (((Integer) ExpressionEvaluatorManager.evaluate ("bufferSize", bufferSize, Integer.class, this, pageContext)).intValue());
	}
}
