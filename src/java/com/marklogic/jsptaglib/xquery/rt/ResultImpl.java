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
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.xquery.common.Result;
import com.marklogic.jsptaglib.xquery.common.ResultException;
import com.marklogic.jsptaglib.xquery.common.ResultItem;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Result interface which holds the
 * buffered result of an XQuery execution.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 * @deprecated No longer needed, functionality is in XQRunner library
 */
public class ResultImpl implements Result
{
	ResultItem [] sequence;

	/**
	 * Reads in and buffers all the data items in the given ResultSequence.
	 * @param xdbcResultSequence The object to obtain the data from.
	 * @throws XDBCException If there is a problem reading the data.
	 */
	public ResultImpl (XDBCResultSequence xdbcResultSequence) throws ResultException, XDBCException
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
