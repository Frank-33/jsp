/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.jsptaglib.xquery.common.ConnectionProperties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @jsp:tag name="param"
 *  description="Set named parameter for the query or module invocation"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class ParamTag extends BodyTagSupport
{
	private String namespace = null;
	private String localname = null;
	private String type = null;
	private String value = null;

	// -----------------------------------------------------------

	public void release ()
	{
		namespace = null;
		localname = null;
		type = null;
		value = null;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setNamespace (String namespace) throws JspException
	{
		this.namespace = namespace;
	}

	/**
	 * @jsp:attribute required="true" rtexprvalue="true"
	 */
	public void setLocalname (String localname) throws JspException
	{
		this.localname = localname;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setType (String type) throws JspException
	{
		this.type = type;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setValue (String value) throws JspException
	{
		this.value = value;
	}

	// -----------------------------------------------------------

	public int doEndTag () throws JspException
	{
		ExecuteTag executeTag = (ExecuteTag) findAncestorWithClass (this, ExecuteTag.class);

		if (executeTag == null) {
			throw new JspException ("param tag must be nested in an execute tag");
		}

		if (value == null) {
			value = getBodyContent().getString();
		}

		executeTag.addParam (namespace, localname, type, value);

		return EVAL_PAGE;
	}

}
