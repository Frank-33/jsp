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
package com.marklogic.jsptaglib.xquery;

import com.marklogic.xdmp.XDMPDataSource;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCConnection;

/**
 * Wrapper for the XDMPDataSource class which associates default user/password
 * values with connections requests that don't specify them.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class XdmpDataSourceWrapper extends XDMPDataSource
{
	private String user = null;
	private String password = null;

	public XdmpDataSourceWrapper ()
		throws XDBCException
	{
	}

	public XdmpDataSourceWrapper (String s, int i)
		throws XDBCException
	{
		super (s, i);
	}

	public XdmpDataSourceWrapper (String s, int i, String user, String password)
		throws XDBCException
	{
		super (s, i);

		this.user = user;
		this.password = password;
	}

	public XDBCConnection getConnection () throws XDBCException
	{
		if (user == null) {
			return super.getConnection ();
		}

		return (getConnection (user, password));
	}
}
