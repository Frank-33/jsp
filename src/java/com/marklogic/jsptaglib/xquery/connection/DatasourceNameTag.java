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
 * @jsp:tag name="dataSourceName" display-name="XDBC Connection JNDI Name Tag"
 */
public class DatasourceNameTag extends BodyTagSupport
{
	private String dataSourceName = null;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setDataSourceName (String dataSourceName)
	{
		this.dataSourceName = dataSourceName;
	}

	// -----------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (dataSourceName == null) {
			dataSourceName = getBodyContent().getString().trim();
		}

		TagPropertyHelper.assignAncestorProperty (this, ConnectionProperties.class, "dataSourceName", dataSourceName);

		return EVAL_PAGE;
	}

	public void release ()
	{
		dataSourceName = null;
	}
}
