/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="host"
 *  description="Set parent attribute to the value of the body of this tag"
 */
public class HostTag extends com.marklogic.jsptaglib.xquery.rt.HostTag
{
	public void setInitParameter (String parameterName) throws JspException
	{
		super.setInitParameter ((String) ExpressionEvaluatorManager.evaluate ("initParameter", parameterName, String.class, this, pageContext));
	}
}
