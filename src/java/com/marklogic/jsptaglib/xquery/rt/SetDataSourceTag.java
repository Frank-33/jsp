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

import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.jsptaglib.xquery.common.ConnectionProperties;
import com.marklogic.xqrunner.XQDataSource;
import com.marklogic.xqrunner.XQException;
import com.marklogic.xqrunner.XQFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import java.io.IOException;

/**
 * @jsp:tag name="setDataSource" body-content="JSP"
 * description="Provide information needed to connect to an XQuery engine.
 * Connection information may be provided either as an XDMPDataSource or as
 * host:port user/password.  The dataSource attribute may be set directly
 * with an XDMPDataSource object instance or with a String that represents
 * a JNDI lookup name which references such an object.  If dataSource is
 * not set, then host/port/user/password attributes are used to construct
 * a new XDMPDataSource object.  The XDMPDataSource object will be set as
 * the value of the attribute named by 'var'.  If var is not provided, then
 * the value "com.marklogic.jsptaglib.datasource" will be used as a default.
 * The scope attribute is one of page, request, session or application."
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
 public class SetDataSourceTag extends BodyTagSupport
	implements ConnectionProperties
{
	public static String ML_DEFAULT_DATASOURCE_VAR = "com.marklogic.jsptaglib.xquery.datasource";

	private String scope = null;
	private String var = ML_DEFAULT_DATASOURCE_VAR;
	private Object dataSource = null;
	private String host = null;
	private int port = -1;
	private String user = null;
	private String password = null;

	// ------------------------------------------------------------------

	public void release()
	{
		scope = null;
		var = ML_DEFAULT_DATASOURCE_VAR;
		dataSource = null;
		host = null;
		port = -1;
		user = null;
		password = null;
	}

	// ------------------------------------------------------------------

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setVar (String var) throws JspException
	{
		this.var = var;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setScope (String scope) throws JspException
	{
		this.scope = scope;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setDataSource (Object dataSource)
	{
		this.dataSource = dataSource;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setHost (String host) throws JspException
	{
		this.host = host;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setPort (int port)
	{
		this.port = port;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setUser (String user) throws JspException
	{
		this.user = user;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setPassword (String password) throws JspException
	{
		this.password = password;
	}

	// ------------------------------------------------------------------

	// Trim any excess whitespace from tag body
	public int doAfterBody() throws JspException
	{
		try {
			if (getBodyContent() != null) {
				getPreviousOut().write (getBodyContent().getString().trim());
			}
		} catch (IOException e) {
			throw new JspException ("trimming setDataSource tag body", e);
		}

		return EVAL_PAGE;
	}

	public int doEndTag () throws JspException
	{
		XQDataSource xqDataSource = null;

		if (dataSource != null) {
			if (dataSource instanceof XQDataSource) {
				xqDataSource = (XQDataSource) dataSource;
			}

			if (dataSource instanceof String) {
				xqDataSource = getJndiDataSource ((String) dataSource);
			}
		}

		if (xqDataSource == null) {
			try {
				XQFactory factory = new XQFactory();

				xqDataSource = factory.newDataSource (host, port, user, password);
			} catch (XQException e) {
				throw new JspTagException ("Could not create DataSource for " + host
					+ ":" + port + "/" + user);
			}
		}

		AttributeHelper.setScopedAttribute (pageContext, var, xqDataSource, scope);

		release();

		return (EVAL_PAGE);
	}

	// ------------------------------------------------------------------


	private XQDataSource getJndiDataSource (String jndiName)
		throws JspException
	{
		InitialContext jndiContext = null;

		try {
			jndiContext = new InitialContext();
		} catch (NamingException e) {
			throw new JspException ("JNDI service unavaliable", e);
		}

		try {
			return ((XQDataSource) jndiContext.lookup (jndiName));
		} catch (NamingException e) {
			// nothing
		}

		try {
			return ((XQDataSource) jndiContext.lookup ("java:comp/env/" + jndiName));
		} catch (NamingException ex) {
			// nothing
		}

		throw new JspTagException ("Cannot locate JNDI DataSource: " + jndiName);
	}
}
