/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.connection;

import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.jsptaglib.xquery.XdbcHelper;
import com.marklogic.xdbc.XDBCConnection;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdmp.XDMPDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;

// FIXME: The XDBC API needs to be fixed to handle authentication properly

/**
 * Helper class with methods for obtaining XDBC connections.
 */
public class ConnectionHelper
{
	private ConnectionHelper()
	{
		// Helper class, cannot be instantiated
	}

	public static XDBCConnection getDataSourceConnection (PageContext pageContext, String scope,
		String dataSourceAttrName, String jndiAttrName, String user, String password)
		throws JspException
	{
		XDMPDataSource dataSource = (XDMPDataSource) AttributeHelper.getScopedAttribute (pageContext,
			dataSourceAttrName, scope);

		if ((dataSource == null) && (jndiAttrName != null)) {
			try {
				dataSource = (XDMPDataSource) new InitialContext().lookup (jndiAttrName);
			} catch (NamingException e) {
				// nothing, leave dataSource == null
			}
		}

		if (dataSource == null) {
			return (null);
		}

		try {
			if (user == null) {
				return (dataSource.getConnection());
			} else {
				return (dataSource.getConnection (user, password));
			}
		} catch (XDBCException e) {
			throw new JspException ("Obtaining XDMPDataSource connection: dsattr="
				+ dataSourceAttrName + ", jndi=" + jndiAttrName, e);
		}
	}

	public static XDBCConnection openDirectConnection (String host, int port, String user, String password)
		throws JspException
	{
		try {
			return (XdbcHelper.openConnection (host, port, user, password));
		} catch (XDBCException e) {
			throw new JspException ("Opening connection in Connection tag", e);
		}
	}

	/**
	 * Return an XDBCConnection object, from one of these sources (in priority order):<ul>
	 *  <li>An XDBCConnection object stored in the attribute named by connectionAttrName (in the given scope)</li>
	 *  <li>From the XDMPDataSource object stored in the attribute named by dataSourceAttrName</li>
	 *  <li>From an XDMPDataSource retrieved from a JNDI looking of the name in the jndiName attribute</li>
	 *  <li>A new XDBCConnection object from the default XDBCConnectionFactory for the given host and port.</li>
	 * </ul>
	 * If user and password are supplied with any of the first three options, they will be
	 * pllied to the connection requested.  Otherwise defaults will be assumed.
	 * @param pageContext The current PageContext to look up attributes.
	 * @param scope The scope (page, request, session, application) in which to look for
	 *  attributes.  Default is page scope.
	 * @param connectionAttrName The name of the attribute that contains an XDBCConnection object
	 *  as its value.
	 * @param dataSourceAttrName The name of the attribute that contains an XDMPDataSource object
	 *  as its value.
	 * @param jndiAttrName The name of the attribute that contains a String which is the JNDI
	 *  key with which to lookup a bound XMDPDataSource (for use in J2EE containers).
	 * @param host The host (or dotted quad IP address) to which a new connection should be opened.
	 * @param port The port on the target host to connect to.
	 * @param user The user name credential to present to the server.
	 * @param password The password credential to present to the server
	 * @return An open XDBCConnection instance.
	 * @throws JspException If there is any problem obtaining the connection.  This exception
	 *  will contain a nested root cause Exception.
	 */
	public static XDBCConnection findConnection (PageContext pageContext, String scope,
		String connectionAttrName, String dataSourceAttrName, String jndiAttrName, String host, int port,
		String user, String password)
		throws JspException
	{
		XDBCConnection connection = null;

		if (connectionAttrName != null) {
			connection = (XDBCConnection) AttributeHelper.getScopedAttribute (pageContext, connectionAttrName, scope);
		}

		if (connection == null) {
			connection = getDataSourceConnection (pageContext, scope,
				dataSourceAttrName, jndiAttrName, user, password);
		}

		if (connection == null) {
			connection = openDirectConnection (host, port, user, password);
		}

		return connection;
	}
}
