/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.connection;

import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.jsptaglib.xquery.common.ConnectionProperties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @deprecated Use setDataSource tag
 * @jsp:tag name="jndiName" display-name="XDBC Connection JNDI Name Tag"
 */
public class JndiNameTag extends BodyTagSupport
{
	private String jndiName = null;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setJndiName (String jndiName)
	{
		this.jndiName = jndiName;
	}

	// -----------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (jndiName == null) {
			jndiName = getBodyContent().getString().trim();
		}

		TagPropertyHelper.assignAncestorProperty (this, ConnectionProperties.class, "jndiName", jndiName);

		return EVAL_PAGE;
	}

	public void release ()
	{
		jndiName = null;
	}
}
