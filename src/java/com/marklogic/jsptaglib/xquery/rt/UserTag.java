/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.jsptaglib.xquery.common.ConnectionProperties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @jsp:tag name="user"
 *  description="Set parent attribute to the value of the body of this tag"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class UserTag extends BodyTagSupport
{
	private String user = null;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setInitParameter (String parameterName) throws JspException
	{
		user = pageContext.getServletContext().getInitParameter (parameterName);
	}

	// -----------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (user == null) {
			user = getBodyContent().getString().trim();
		}

		TagPropertyHelper.assignAncestorProperty (this, ConnectionProperties.class, "user", user);

		return EVAL_PAGE;
	}

	public void release ()
	{
		user = null;
	}
}
