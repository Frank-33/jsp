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
import com.marklogic.xqrunner.XQException;
import com.marklogic.xqrunner.XQResult;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Nov 3, 2004
 * Time: 3:35:45 PM
 */
public class ResultAdapter implements Result
{
	private XQResult result;

	public ResultAdapter (XQResult result)
	{
		this.result = result;
	}

	public int getSize ()
	{
		return (result.getSize());
	}

	public ResultItem[] getItems () throws ResultException
	{
		ResultItem [] items = new ResultItem [result.getSize()];

		for (int i = 0; i < items.length; i++) {
			try {
				items [i] = new ResultItemAdapter (result.getItem (i));
			} catch (XQException e) {
				throw new ResultException (e.getMessage(), e);
			}
		}

		return (items);
	}

	public ResultItem getItem (int index) throws ResultException
	{
		try {
			return (new ResultItemAdapter (result.getItem (index)));
		} catch (XQException e) {
			throw new ResultException (e.getMessage(), e);
		}
	}
}
