/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.jsptaglib.xquery.common.ConnectionProperties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @jsp:tag name="host"
 *  description="Set parent attribute to the value of the body of this tag"
 */
public class HostTag extends BodyTagSupport
{
	private String host = null;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setInitParameter (String parameterName) throws JspException
	{
		host = pageContext.getServletContext().getInitParameter (parameterName);
	}

	// -----------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (host == null) {
			host = getBodyContent().getString().trim();
		}

		TagPropertyHelper.assignAncestorProperty (this, ConnectionProperties.class, "host", host);

		return EVAL_PAGE;
	}

	public void release ()
	{
		host = null;
	}
}