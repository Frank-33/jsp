/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;
import com.marklogic.jsptaglib.xquery.common.Result;
import com.marklogic.jsptaglib.xquery.common.ResultItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Result interface which holds the
 * buffered result of an XQuery execution.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class ResultImpl implements Result
{
	ResultItem [] sequence;

	/**
	 * Reads in and buffers all the data items in the given ResultSequence.
	 * @param xdbcResultSequence The object to obtain the data from.
	 * @throws XDBCException If there is a problem reading the data.
	 */
	public ResultImpl (XDBCResultSequence xdbcResultSequence) throws XDBCException
	{
		List list = new ArrayList();
		int index = 0;

		while (xdbcResultSequence.hasNext()) {
			xdbcResultSequence.next();

			list.add (new ResultItemImpl (xdbcResultSequence, index));

			index++;
		}

		sequence = new ResultItem [list.size()];

		list.toArray (sequence);
 	}

	/**
	 * @return The number of items in the result sequence.
	 */
	public int getSize()
	{
		return (sequence.length);
	}

	/**
	 * @return The result item and the given index (zero-based).
	 */
	public ResultItem [] getItems()
	{
		return (sequence);
	}

	/**
	 * @param index The index of the item the fetch.
	 * @return The item at the given zero-based index.
	 */
	public ResultItem getItem (int index)
	{
		return (sequence [index]);
	}
}
