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
package com.marklogic.jsptaglib.xquery;

import com.marklogic.xdbc.XDBCResultSequence;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCConnection;
import com.marklogic.xdbc.XDBCConnectionFactory;
import com.marklogic.xdmp.util.XDMPAuthenticator;

import org.w3c.dom.Node;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.JDOMException;

import java.io.StringReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.Authenticator;

// FIXME: Delete the class
/**
 * Helper class with methods to assist in XDBC operations.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 * @deprecated Do not use this
 */
public class XdbcHelper
{
	public static XDBCConnection openConnection (String host, int port, String user, String password)
		throws XDBCException
	{
		XDBCConnection connection = null;

		XDBCConnectionFactory connectionFactory = XDBCConnectionFactory.newInstance();

		if (user != null) {
			// This is SOOOO evil
			Authenticator.setDefault (new XDMPAuthenticator (user, password));
		}

		connection = connectionFactory.newXDBCConnection (host, port);

		return (connection);
	}

	/**
	 * Concatenate all the returned data and write it to the provided Writer,
	 * optionally separating the results by the provided separator String.
	 * @param xdbcResultSequence Source of the sequence of results
	 * @param writer Sink for concatenated results
	 * @param separator String written between each result, unless null
	 * @throws XDBCException Thrown if there is a problem fetching results
	 * @throws IOException Thrown if there is trouble writing the output
	 */
	public static void concatResult (XDBCResultSequence xdbcResultSequence,
		Writer writer, String separator)
		throws XDBCException, IOException
	{
		boolean first = true;

		while (xdbcResultSequence.hasNext()) {
			if (separator != null) {
				if (first) {
					first = false;
				} else {
					writer.write (separator);
				}
			}

			Reader reader = xdbcResultSequence.nextReader();

			passThroughChars (reader, writer, 102400);

			reader.close();
		}
	}

	/**
	 * Copy all chars from the Reader to the Writer
	 * @param reader A source of chars
	 * @param writer The sink that accepts chars
	 * @param buffersize The size of the intermediate buffer to use
	 * @throws IOException Thrown if there is an I/O problem
	 */
	public static void passThroughChars (Reader reader, Writer writer,
		int buffersize)
		throws IOException
	{
		int rc;
		char buffer [] = new char [buffersize];

		while ((rc = reader.read (buffer)) > 0) {
			writer.write (buffer, 0, rc);
		}
	}

	// ----------------------------------------------------------

	/**
	 * Encapsulate the current element of an XDBC ResultSequence as an object.
	 * @param xdbcResultSequence An active XDBCResultSequence object.
	 * @return An Object representing the current item.
	 * @throws XDBCException If an XDBC error ocurrs while getting the value.
	 */
	public static Object resultAsObject (XDBCResultSequence xdbcResultSequence)
		throws XDBCException
	{
		switch (xdbcResultSequence.getItemType ()) {
		case XDBCResultSequence.XDBC_Boolean:
			return (xdbcResultSequence.getBoolean().asBoolean());

		case XDBCResultSequence.XDBC_Date:
		case XDBCResultSequence.XDBC_DateTime:
		case XDBCResultSequence.XDBC_Time:
			return (xdbcResultSequence.getDate().asDate());

		case XDBCResultSequence.XDBC_Double:
		case XDBCResultSequence.XDBC_Float:
			return (xdbcResultSequence.getDouble().asDouble());

		case XDBCResultSequence.XDBC_Decimal:
			return (xdbcResultSequence.getDecimal().asBigDecimal());

		case XDBCResultSequence.XDBC_Integer:
			return (xdbcResultSequence.getInteger().asInteger());

		case XDBCResultSequence.XDBC_Node:
			return (xdbcResultSequence.getNode());

		case XDBCResultSequence.XDBC_String:
			return (xdbcResultSequence.get_String());

		default:
			throw new XDBCException ("Unexpected result type: " + xdbcResultSequence.getItemType());
		}
	}

	/**
	 * Get the current ResultSequence element as a W3C Node.
	 * @param xdbcResultSequence An active XDBCResultSequence object.
	 * @return An instance of org.w3c.dom.Node
	 */
	public static Node resultAsW3cDom (XDBCResultSequence xdbcResultSequence)
	{
		return (xdbcResultSequence.getNode().asNode());
	}

	/**
	 * Get the current ResultSequence element as a JDom Document
	 * @param xdbcResultSequence An active XDBCResultSequence object.
	 * @return An instance of ogr.jdom.Document
	 * @throws XDBCException If an XDBC error ocurrs while getting the value.
	 */
	public static Document resultAsJDom (XDBCResultSequence xdbcResultSequence)
		throws XDBCException
	{
		return resultAsJDom (new StringReader (xdbcResultSequence.getNode().asString()));
	}

	// ------------------------------------------------------------------------------

	private static Document resultAsJDom (Reader reader)
		throws XDBCException
	{
		try {
			return (new SAXBuilder().build (reader));
		} catch (JDOMException e) {
			throw new XDBCException ("Problem during JDOM build", e);
		} catch (IOException e) {
			throw new XDBCException ("IO problem during JDOM build", e);
		}
	}
}
