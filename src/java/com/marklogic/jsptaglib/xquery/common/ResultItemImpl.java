package com.marklogic.jsptaglib.xquery.common;

import com.marklogic.jsptaglib.xquery.XdbcHelper;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;
import com.marklogic.xdbc.XDBCSchemaTypes;

import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Jun 16, 2004
 * Time: 5:20:04 PM
 */
public class ResultItemImpl implements ResultItem
{
	private XDBCSchemaTypes.Node node;
	private Object object;
	private String string = null;
	private org.jdom.Document jdom = null;
	private org.w3c.dom.Document w3cDom = null;
	private int index;

	// ------------------------------------------------------------

	public ResultItemImpl (XDBCResultSequence xdbcResultSequence, int index)
		throws XDBCException
	{
		this.index = index;

		if (xdbcResultSequence.getItemType() == XDBCResultSequence.XDBC_Node) {
			node = xdbcResultSequence.getNode();
			object = node;
			string = node.asString();
		} else {
			object = XdbcHelper.resultAsObject (xdbcResultSequence);
		}
	}

	// ------------------------------------------------------------

	public int getIndex ()
	{
		return (index);
	}

	public boolean isNode()
	{
		return (node != null);
	}

	public Object getObject()
	{
		return (object);
	}

	public String getString()
	{
		return ((string != null) ? string : getObject().toString());
	}

	public org.w3c.dom.Document getW3cDom() throws XDBCException
	{
		if (w3cDom == null) {
			w3cDom = asW3cDom (getString());
		}

		return (w3cDom);
	}

	public org.jdom.Document getJDom()
		throws XDBCException
	{
		if (jdom == null) {
			jdom = asJDom (getString());
		}

		return (jdom);
	}

	public Reader getReader()
	{
		return (new StringReader (getString()));
	}

	// -------------------------------------------------------

	private org.jdom.Document asJDom (String string)
		throws XDBCException
	{
		if (isNode() == false) {
			throw new XDBCException ("Result is not a node");
		}

		try {
			return (new SAXBuilder().build (new StringReader (string)));
		} catch (JDOMException e) {
			throw new XDBCException ("Problem during JDOM build", e);
		} catch (IOException e) {
			throw new XDBCException ("IO problem during JDOM build", e);
		}
	}

	private org.w3c.dom.Document asW3cDom (String string)
		throws XDBCException
	{
		if (isNode() == false) {
			throw new XDBCException ("Result is not a node");
		}

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ();

		try {
			return (factory.newDocumentBuilder().parse (new InputSource (new StringReader (string))));
		} catch (SAXException e) {
			throw new XDBCException ("SAX Exception parsing result", e);
		} catch (Exception e) {
			throw new XDBCException ("Exception building W3C DOM", e);
		}
	}
}
