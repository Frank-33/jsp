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

import java.io.Reader;

/**
 * One of the result items in a sequence returned by
 * the execution of an XQuery script.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
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
	 * @throws ResultException If there is a problem converting this
	 * result to a DOM.  If this result is not a node (isNode() == false)
	 * then this exception will always be thrown.
	 */
	org.w3c.dom.Document getW3cDom() throws ResultException;

	/**
	 * @return This result as a JDOM DOM (org.jdom.Document) object.
	 * @throws ResultException If there is a problem converting this
	 * result to a DOM.  If this result is not a node (isNode() == false)
	 * then this exception will always be thrown.
	 */
	org.jdom.Document getJDom() throws ResultException;

	/**
	 * @return A Reader from which the String representation of this
	 *  result item can be read.  This a StringReader instance over
	 *  the return value of getString().
	 */
	Reader getReader();
}
