/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

/**
 * An instance of this class is set in as the result of
 * an XQuery call by the xq:query tag.  This object is a
 * simple container that holds the returned result sequence.
 * The result sequence held here is fully memory resident
 * and no longer bound to a connection.  This implies that
 * streaming results are not possible with the xq:query tag.
 * If you need streaming, see the xq:statement and
 * xq:resultSequence tags.
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
	ResultItem [] getSequence();

	/**
	 * @param index The index of the ResultItem object to return, the first
	 * is zero.
	 * @return The ResultItem object at the requested index.
	 */
	ResultItem getResult (int index);
}
