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

import com.marklogic.jsptaglib.xquery.common.ResultItem;
import com.marklogic.jsptaglib.xquery.common.ResultException;
import com.marklogic.xqrunner.XQResultItem;
import com.marklogic.xqrunner.XQException;

import org.w3c.dom.Document;

import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Nov 11, 2004
 * Time: 4:55:33 PM
 */
class ResultItemAdapter implements ResultItem
{
	private XQResultItem item;

	public ResultItemAdapter (XQResultItem item)
	{
		this.item = item;
	}

	public int getIndex()
	{
		return (item.getIndex());
	}

	public boolean isNode()
	{
		return (item.isNode());
	}

	public Object getObject()
	{
		return (item.asObject());
	}

	public String getString()
	{
		return (item.asString());
	}

	public Document getW3cDom() throws ResultException
	{
		try {
			return (item.asW3cDom());
		} catch (XQException e) {
			throw new ResultException (e.getMessage(), e);
		}
	}

	public org.jdom.Document getJDom() throws ResultException
	{
		try {
			return (item.asJDom());
		} catch (XQException e) {
			throw new ResultException (e.getMessage(), e);
		}
	}

	public Reader getReader() throws XQException
	{
		return (item.asReader());
	}
}
