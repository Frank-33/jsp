/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.connection;

import com.marklogic.xdbc.XDBCConnection;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.jsptaglib.AttributeHelper;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;

/**
 * @deprecated Use setDataSource tag
 * @jsp:tag name="closeConnection" display-connection="Close XDBC Connection"
 */
public class CloseConnectionTag extends TagSupport
{
	private String connection = ConnectionTag.DEFAULT_XQUERY_CONNECTION_ATTRIBUTE;
	private String scope = null;
	private boolean ignoreErrors = true;

	// -------------------------------------------------------------

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public String getConnection ()
	{
		return connection;
	}

	public void setConnection (String connection)
	{
		this.connection = connection;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public String getScope ()
	{
		return scope;
	}

	public void setScope (String scope)
	{
		this.scope = scope;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setIgnoreErrors (boolean ignoreErrors)
	{
		this.ignoreErrors = ignoreErrors;
	}

	// -------------------------------------------------------------

	public void release ()
	{
		connection = ConnectionTag.DEFAULT_XQUERY_CONNECTION_ATTRIBUTE;
		scope = null;
		ignoreErrors = true;
	}

	// -------------------------------------------------------------

	public int doStartTag () throws JspException
	{
		XDBCConnection xdbcConnection = null;

		if (scope == null) {
			xdbcConnection = (XDBCConnection) pageContext.getAttribute (connection);
		} else {
			xdbcConnection = (XDBCConnection) AttributeHelper.getScopedAttribute (pageContext,
				connection, scope);
		}

		if ((xdbcConnection == null) && (ignoreErrors == false)) {
			throw new JspException ("No such connection attribute: " + connection);
		}

		try {
			xdbcConnection.close ();
		} catch (XDBCException e) {
			if (ignoreErrors == false) {
				throw new JspException ("closing XDBCConnection", e);
			}

			e.printStackTrace ();
		}

		return (EVAL_BODY_INCLUDE);
	}
}
