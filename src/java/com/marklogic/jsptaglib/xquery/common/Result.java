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
	ResultItem [] getItems() throws ResultException;

	/**
	 * @param index The index of the ResultItem object to return, the first
	 * is zero.
	 * @return The ResultItem object at the requested index.
	 */
	ResultItem getItem (int index) throws ResultException;
}
