/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.xquery.rt.SetDataSourceTag;
import com.marklogic.jsptaglib.xquery.common.StatementProperties;
import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.xdbc.XDBCConnection;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;
import com.marklogic.xdbc.XDBCStatement;
import com.marklogic.xdmp.XDMPDataSource;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;

import java.io.IOException;

/**
 * @jsp:tag name="statement" description="Mark Logic XDBC Statement Tag"
 *  body-content="JSP"
 */
public class StatementTag extends BodyTagSupport
	implements StatementProperties, TryCatchFinally
{
	private String var = null;
	private String query = null;
	private String scope = null;
	private XDMPDataSource dataSource = null;

	private XDBCStatement xdbcStatement = null;
	private XDBCConnection xdbcConnection = null;

	// -------------------------------------------------------------

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
	public void setDataSource (XDMPDataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	public void release ()
	{
		dataSource = null;
		query = null;
		xdbcStatement = null;
		xdbcConnection = null;
		var = null;
		scope = null;
	}

	// ------------------------------------------------------------------------

	public XDBCResultSequence executeQuery ()
		throws XDBCException
	{
		return (xdbcStatement.executeQuery (query));
	}

	// ------------------------------------------------------------------------

	public int doStartTag() throws JspException
	{
		if ((dataSource != null) && ( ! (dataSource instanceof XDMPDataSource))) {
			throw new JspTagException ("DataSource must instance of XDMPDataSource");
		}

		try {
			xdbcConnection = SetDataSourceTag.getConnection (pageContext, (XDMPDataSource) dataSource, true);

			xdbcStatement = xdbcConnection.createStatement();

			if (var != null) {
				AttributeHelper.setScopedAttribute (pageContext, var, xdbcStatement, scope);
			}
		} catch (XDBCException e) {
			throw new JspException ("Cannot create XDBCStatement", e);
		}

		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody()
		throws JspException
	{
		if (getBodyContent() != null) {
			try {
				getPreviousOut().write (getBodyContent().getString().trim());
			} catch (IOException e) {
				throw new JspException("I/O Exception writing body", e);
			}
		}

		return EVAL_PAGE;
	}

	public int doEndTag()
		throws JspException
	{
		if (var != null) {
			AttributeHelper.removeScopedAttribute (pageContext, var, scope);
		}

		try {
			xdbcStatement.close();
		} catch (XDBCException e) {
			throw new JspException ("Closing statement", e);
		}

		return EVAL_PAGE;
	}

	// ------------------------------------------------------------------------

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
}
