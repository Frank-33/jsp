/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.jsptaglib.TagPropertyHelper;
import com.marklogic.jsptaglib.xquery.XdbcHelper;
import com.marklogic.jsptaglib.xquery.common.ResultSequenceImpl;
import com.marklogic.jsptaglib.xquery.common.StatementProperties;
import com.marklogic.xdbc.XDBCConnection;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;
import com.marklogic.xdbc.XDBCStatement;
import com.marklogic.xdmp.XDMPDataSource;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import java.io.IOException;

/**
 * @jsp:tag name="query" description="If nested in a statement tag,
 * defines the query value to execute by the following resultSequence
 * tag.  If used as a top-level tag, the query will be implictly executed
 * using the in-scope data source. If 'var' is provided, that attribute
 * will be set to and instance of ResultSequence which will contain the
 * result of the query.  Each ResultSequence contains zero or more Result
 * objects.  If var is not provided, the result sequence is concatenated
 * to form a string and replaces the query tag in the JSP page result.  If
 * the optional 'separator' attribute is specified, its string value is
 * written between each result."
 * @see com.marklogic.jsptaglib.xquery.common.ResultSequence
 * @see com.marklogic.jsptaglib.xquery.common.Result
 * @see com.marklogic.jsptaglib.xquery.statement.rt.StatementTag
 * @see com.marklogic.jsptaglib.xquery.statement.rt.ResultSequenceTag
 */
public class QueryTag extends BodyTagSupport
	implements StatementProperties, TryCatchFinally
{
	private String query = null;
	private String importModule;
	private String importModuleNamespace;
	private String separator = null;
	private XDMPDataSource dataSource = null;
	private String var = null;
	private String scope = null;

	private XDBCConnection xdbcConnection = null;

	// ------------------------------------------------------------

	public void release ()
	{
		super.release();

		query = null;
		importModule = null;
		importModuleNamespace = null;
		separator = null;
		dataSource = null;
		var = null;
		scope = null;
		xdbcConnection = null;
	}

	// ------------------------------------------------------------

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setImportModule (String importModule) throws JspException
	{
		this.importModule = importModule;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setImportModuleNamespace (String importModuleNamespace) throws JspException
	{
		this.importModuleNamespace = importModuleNamespace;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setQuery (String query) throws JspException
	{
		this.query = query;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setSeparator (String separator) throws JspException
	{
		this.separator = separator;
	}

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
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setDataSource (XDMPDataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	// ------------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (TagSupport.findAncestorWithClass (this, StatementProperties.class) != null) {
			// this tag is nested, set query property on ancestor and leave
			TagPropertyHelper.assignAncestorProperty (this, StatementProperties.class, "query", getQueryString());

			return (EVAL_PAGE);
		}

		xdbcConnection = SetDataSourceTag.getConnection (pageContext, (XDMPDataSource) dataSource, true);

		try {
			XDBCStatement xdbcStatement = xdbcConnection.createStatement();
			XDBCResultSequence xdbcResultSequence = xdbcStatement.executeQuery (getQueryString());

			if (var == null) {
				XdbcHelper.concatResult (xdbcResultSequence, pageContext.getOut(), separator);
			} else {
				AttributeHelper.setScopedAttribute (pageContext, var,
					new ResultSequenceImpl (xdbcResultSequence), scope);
			}

		} catch (XDBCException e) {
			throw new JspException ("XDBC error", e);
		} catch (IOException e) {
			throw new JspException ("I/O problem sending result", e);
		}

		return (EVAL_PAGE);
	}

	// ------------------------------------------------------------

	public void doCatch (Throwable throwable) throws Throwable
	{
		throw throwable;
	}

	public void doFinally ()
	{
		try {
			if (xdbcConnection != null) {
				xdbcConnection.close();
				xdbcConnection = null;
			}
		} catch (XDBCException e) {
			// nothing, ignore it
		}

		release();
	}
	// ------------------------------------------------------------

	protected String getQueryString()
	{
		if (query == null) {
			query = getBodyContent().getString().trim();
		}

		if ((importModuleNamespace != null) && (importModule != null)) {
			StringBuffer sb = new StringBuffer();

			sb.append ("import module '").append (importModuleNamespace);
			sb.append ("' at '").append (importModule).append ("' ");
			sb.append (query);
		}
		return query;
	}
}
