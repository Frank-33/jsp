/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

import javax.servlet.jsp.JspException;

/**
 * Property setters associated with a SetDataSource tag.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public interface ConnectionProperties
{
	/**
	 * @param host The name (or IP number as dotted quad string) of the
	 *  host to connect to.
	 */
	void setHost (String host) throws JspException;

	/**
	 * @param port The port number to connect to on the host.
	 */
	void setPort (int port);

	/**
	 * @param user The user name credential to use for the connection.
	 */
	void setUser (String user) throws JspException;

	/**
	 * @param password The password credential to use for the connection.
	 */
	void setPassword (String password) throws JspException;

	/**
	 * @param dataSource An XDMPDataSource instance, or a String to use
	 *  in a JNDI lookup to find the DataSource.
	 */
	void setDataSource (Object dataSource);
}
