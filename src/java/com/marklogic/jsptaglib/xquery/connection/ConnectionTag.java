/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.connection;

import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.jsptaglib.xquery.common.ConnectionProperties;
import com.marklogic.xdbc.XDBCConnection;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import java.io.IOException;

/**
 * @deprecated Use setDataSource tag
 * @jsp:tag name="connection" display-name="Open XDBC Connection"
 *  body-content="JSP"
 * @jsp:variable name-from-attribute="id" class="com.marklogic.xdbc.XDBCConnection" declare="true"
 *  scope="AT_END"
 */
public class ConnectionTag extends BodyTagSupport
	implements ConnectionProperties
{
	public static final String DEFAULT_XQUERY_CONNECTION_ATTRIBUTE = "com.marklogic.jsptaglib.xquery.connection";

	private String scope = null;
	private String host;
	private int port;
	private String user;
	private String password;
	private String dataSourceName;
	private String jndiName;

	// -------------------------------------------------------------

	/**
	 * Defined so xdoclet will generate the attribute definition.
	 * @jsp:attribute name="id" required="true" rtexprvalue="false"
	 */
	public String getId()
	{
		return super.getId();
	}

	// -------------------------------------------------------------

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setScope (String scope)
	{
		this.scope = scope;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setHost (String host)
	{
		this.host = host;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setPort (int port)
	{
		this.port = port;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setUser (String user)
	{
		this.user = user;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setPassword (String password)
	{
		this.password = password;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setDataSourceName (String dataSourceName)
	{
		this.dataSourceName = dataSourceName;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setJndiName (String jndiName)
	{
		this.jndiName = jndiName;
	}

	public void setDataSource (Object dataSource)
	{
		throw new RuntimeException ("blah");
	}

	// --------------------------------------------------------------------

	public int doStartTag()
	{
	  return EVAL_BODY_INCLUDE;
	}

	// Trim any excess whitespace from connection tag
	public int doAfterBody() throws JspException
	{
		try {
			if (getBodyContent() != null) {
				getPreviousOut().write (getBodyContent().getString().trim());
			}
		} catch (IOException e) {
			throw new JspException ("trimming connection tag body", e);
		}

		return EVAL_PAGE;
	}

	public int doEndTag () throws JspException
	{
		XDBCConnection connection = ConnectionHelper.findConnection (pageContext, scope,
			null, dataSourceName, jndiName, host, port, user, password);

		AttributeHelper.setScopedAttribute (pageContext, getId(), connection, scope);

		return (EVAL_PAGE);
	}
}
