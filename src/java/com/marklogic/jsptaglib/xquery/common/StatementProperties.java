/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

import com.marklogic.xdmp.XDMPDataSource;

import javax.servlet.jsp.JspException;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Apr 22, 2004
 * Time: 10:48:11 PM
 */
public interface StatementProperties
{
	void setQuery (String query) throws JspException;

	void setDataSource (XDMPDataSource dataSource);
}
