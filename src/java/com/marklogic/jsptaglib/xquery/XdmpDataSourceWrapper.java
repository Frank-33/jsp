/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery;

import com.marklogic.xdmp.XDMPDataSource;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCConnection;

/**
 * Wrapper for the XDMPDataSource class which associates default user/password
 * values with connections requests that don't specify them.
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
