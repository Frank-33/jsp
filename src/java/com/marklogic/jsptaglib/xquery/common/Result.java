/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

import com.marklogic.xdbc.XDBCException;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: May 13, 2004
 * Time: 12:00:08 PM
 */
public interface Result
{
	boolean isNode();
	Object getObject();
	String getString();
	org.w3c.dom.Document getW3cDom() throws XDBCException;
	org.jdom.Document getJDom() throws XDBCException;

	// FIXME: Need Numbers and dates?  Or are Strings enough?
}
