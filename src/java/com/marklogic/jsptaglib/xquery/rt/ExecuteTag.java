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
import com.marklogic.xqrunner.XQDataSource;
import com.marklogic.xqrunner.XQException;
import com.marklogic.xqrunner.XQResult;
import com.marklogic.xqrunner.XQRunner;
import com.marklogic.xqrunner.XQuery;
import com.marklogic.xqrunner.XQVariable;
import com.marklogic.xqrunner.XQVariableType;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * @jsp:tag name="execute" description="Execution context for query"
 *  body-content="JSP"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class ExecuteTag extends BodyTagSupport
	implements TryCatchFinally
{
	private String var = null;
	private String scope = null;
	private String query = null;
	private String module = null;
	private String separator = null;
	private XQDataSource dataSource = null;

	private boolean queryExecuted = false;
	private List vars = new ArrayList();

	// -------------------------------------------------------------

	public void release ()
	{
		var = null;
		scope = null;
		query = null;
		module = null;
		separator = null;
		dataSource = null;
		queryExecuted = false;
		vars.clear();
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setVar (String var) throws JspException
	{
		this.var = var;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setScope (String scope) throws JspException
	{
		this.scope = scope;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setQuery (String query) throws JspException
	{
		this.query = query;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setModule (String module) throws JspException
	{
		this.module = module;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setSeparator (String separator) throws JspException
	{
		this.separator = separator;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setDataSource (XQDataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	// ------------------------------------------------------------------------

	// must be public, accessed by reflection
	public void setQueryExecuted (boolean queryExecuted)
	{
		this.queryExecuted = queryExecuted;
	}

	protected void addParam (String namespace, String localname, String type, String value)
		throws JspException
	{
		vars.add (new Var (namespace, localname, type, value));
	}

	// ------------------------------------------------------------------------

	public XQResult executeQuery ()
		throws XQException
	{
		// TODO: Handle "module" attribute being set instead of "query"

		XQuery xquery = dataSource.newQuery (query);

		for (Iterator it = vars.iterator (); it.hasNext ();) {
			Var var = (Var) it.next();

			xquery.setVariable (var.asXQVariable (dataSource));
		}

		XQRunner runner = dataSource.newSyncRunner();

		return (runner.runQuery (xquery));
	}

	// ------------------------------------------------------------------------

	public int doStartTag() throws JspException
	{
		if ((dataSource != null) && ( ! (dataSource instanceof XQDataSource))) {
			throw new JspTagException ("DataSource must be an instance of XQDataSource");
		}

		dataSource = getDataSource (dataSource);

		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody()
		throws JspException
	{
		if (getBodyContent() != null) {
			try {
				getPreviousOut().write (getBodyContent().getString().trim());
			} catch (IOException e) {
				throw new JspException("I/O Exception writing body: " + e, e);
			}
		}

		return EVAL_PAGE;
	}

	public int doEndTag()
		throws JspException
	{
		if (queryExecuted) {
			if (var == null) {
				return (EVAL_PAGE);
			}

			throw new JspException ("Cannot use both var attribute and nested result tag");
		}

		if ((query != null) && (module != null)) {
			throw new JspException ("Cannot specify both query and module name");
		}

		if (module != null) {
			throw new JspException ("Module invocation not yet implemented");
		}

		try {

			XQResult result = executeQuery();

			if (var == null) {
				outputResult (result, separator);
			} else {
				setResult (result, var);
			}
		} catch (XQException e) {
			throw new JspException ("executing query: " + e, e);
		}

		release();

		return EVAL_PAGE;
	}

	// ------------------------------------------------------------------------

	private void setResult (XQResult result, String var)
	{
		AttributeHelper.setScopedAttribute (pageContext, var,
			new ResultAdapter (result), scope);
	}

	private void outputResult (XQResult result, String separator)
		throws JspException
	{
		try {
			// FIXME: make this streaming: result.writeTo (Writer writer)
			pageContext.getOut().write (result.asString (separator));
		} catch (IOException e) {
			throw new JspException ("writing result: " + e, e);
		}
	}

	// ------------------------------------------------------------------------

	public void doCatch (Throwable throwable) throws Throwable
	{
		throw throwable;
	}

	public void doFinally ()
	{
		release();
	}

	// ------------------------------------------------------------------------

	public XQDataSource getDataSource (XQDataSource dataSourceParam)
		throws JspException
	{
		XQDataSource dataSource = dataSourceParam;

		if (dataSource == null) {
			dataSource = (XQDataSource) pageContext.findAttribute (SetDataSourceTag.ML_DEFAULT_DATASOURCE_VAR);
		}

		if (dataSource == null) {
			throw new JspTagException ("Cannot find a DataSource in scope");
		}

		return (dataSource);
	}

	// ------------------------------------------------------------------------

	private class Var
	{
		private String namespace;
		private String localname;
		private String value;
		private XQVariableType type;

		public Var (String namespace, String localname, String type, String value)
			throws JspException
		{
			this.namespace = namespace;
			this.localname = localname;
			this.value = value;
			this.type = (type == null) ? XQVariableType.XS_UNTYPED_ATOMIC : XQVariableType.forType (type);

			if (this.type == null) {
				throw new JspException ("Invalid schema type given for variable: " +
					"namespace=" + namespace +
					", localname=" + localname +
					", type=" + type);
			}
		}

		public String getNamespace ()
		{
			return namespace;
		}

		public String getLocalname ()
		{
			return localname;
		}

		public XQVariableType getType()
		{
			return type;
		}

		public String getValue ()
		{
			return value;
		}

		public XQVariable asXQVariable (XQDataSource dataSource) throws XQException
		{
			if (type == XQVariableType.XS_STRING) {
				return (dataSource.newVariable (namespace,
					localname, type, value));
			}

			if (type == XQVariableType.XS_INTEGER) {
				return (dataSource.newVariable (namespace,
					localname, type, Integer.parseInt (value)));
			}

			throw new XQException ("FIXME: not fully implemented");
		}
	}
}
