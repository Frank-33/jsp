/*
 * Copyright (c)2004 Mark Logic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The use of the Apache License does not indicate that this project is
 * affiliated with the Apache Software Foundation.
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
