/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.jsptaglib.xquery.common.ConnectionProperties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @jsp:tag name="port"
 *  description="Set parent attribute to the value of the body of this tag"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class PortTag extends BodyTagSupport
{
	private int port = -1;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setInitParameter (String parameterName) throws JspException
	{
		port = assignFromString (pageContext.getServletContext().getInitParameter (parameterName));
	}

	private int assignFromString (String stringValue)
	{
		if (stringValue == null) {
			return (-1);
		}

		try {
			return (Integer.parseInt (stringValue));
		} catch (NumberFormatException e) {
			return (-1);
		}
	}

	// -----------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (port == -1) {
			port = assignFromString (getBodyContent().getString().trim());
		}

		TagPropertyHelper.assignAncestorProperty (this, ConnectionProperties.class, "port", port);

		return EVAL_PAGE;
	}

	public void release ()
	{
		port = -1;
	}
}
