/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="user"
 *  description="Set parent attribute to the value of the body of this tag"
 */
public class UserTag extends com.marklogic.jsptaglib.xquery.rt.UserTag
{
	public void setInitParameter (String parameterName) throws JspException
	{
		super.setInitParameter ((String) ExpressionEvaluatorManager.evaluate ("initParameter", parameterName, String.class, this, pageContext));
	}
}
