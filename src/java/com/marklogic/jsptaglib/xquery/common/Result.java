/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

/**
 * An instance of this class is set as the result of
 * an XQuery call by the xq:execute tag.  This object is a
 * simple container that holds the returned result sequence.
 * The result sequence held here is fully memory resident
 * and no longer bound to a connection.  The result items
 * may be accessed randomly and/or repeatedly as needed.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public interface Result
{
	/**
	 * @return The number of ResultItem elements the sequence.
	 */
	int getSize();

	/**
	 * @return The full sequence of results as an array.
	 */
	ResultItem [] getItems();

	/**
	 * @param index The index of the ResultItem object to return, the first
	 * is zero.
	 * @return The ResultItem object at the requested index.
	 */
	ResultItem getItem (int index);
}
