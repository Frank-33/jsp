/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

import com.marklogic.xdbc.XDBCException;

import java.io.Reader;
import java.io.IOException;

/**
 * One of the result elements in a sequence returned by
 * the execution of an XQuery script.
 */
public interface ResultItem
{
	/**
	 * @return Index (zero-based) of this item in the result sequence.
	 */
	int getIndex();

	/**
	 * @return True if this result is an XML DOM node, otherwise false.
	 */
	boolean isNode();

	/**
	 * @return This result as an object.  The actual object type
	 * of object instance is determined by the type os the result.
	 * If this is a node (isNode() == true), the type will be
	 * XDBCSchemaTypes.Node.  Date types will be java.util.Date,
	 * booleans will be Boolean and numeric types will be the
	 * appropriate java.lang numeric object type.  Strings will
	 * be java.lang.String.
	 */
	Object getObject();

	/**
	 * @return This result as String.  This is equivalent to
	 * getObject().toString() but is more efficient.
	 */
	String getString();

	/**
	 * @return This result as a W3C DOM (org.w3c.dom.Document) object.
	 * @throws XDBCException If there is a problem converting this
	 * result to a DOM.  If this result is not a node (isNode() == false)
	 * then this exception will always be thrown.
	 */
	org.w3c.dom.Document getW3cDom() throws XDBCException;

	/**
	 * @return This result as a JDOM DOM (org.jdom.Document) object.
	 * @throws XDBCException If there is a problem converting this
	 * result to a DOM.  If this result is not a node (isNode() == false)
	 * then this exception will always be thrown.
	 */
	org.jdom.Document getJDom() throws XDBCException;

	/**
	 * @return A Reader from which the String representation of this
	 *  result item can be read.
	 * @throws IOException If there is a problem setting up the reader.
	 */
	Reader getReader() throws IOException;

	// FIXME: Need Numbers and dates?  Or are Strings enough?
}
