/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;

import java.util.ArrayList;
import java.util.List;


public class ResultImpl implements Result
{
	ResultItem [] sequence;

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

	public int getSize ()
	{
		return (sequence.length);
	}

	public ResultItem [] getItem ()
	{
		return (sequence);
	}

	public ResultItem getResult (int index)
	{
		return (sequence [index]);
	}

	// ------------------------------------------------------------

}
