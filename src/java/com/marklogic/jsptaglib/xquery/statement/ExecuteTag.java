/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement;

import com.marklogic.jsptaglib.xquery.XdbcHelper;
import com.marklogic.jsptaglib.xquery.statement.rt.StatementTag;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import java.io.IOException;

/**
 * @deprecated This tag has been deprecated
 * xx@jsp:tag name="execute" description="Execute XQuery"
 */
public class ExecuteTag extends BodyTagSupport
{
	private boolean ignoreErrors = false;
	private boolean ignoreResult = false;
	private String separator = null;

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setIgnoreErrors (boolean ignoreErrors)
	{
		this.ignoreErrors = ignoreErrors;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setIgnoreResult (boolean ignoreResult)
	{
		this.ignoreResult = ignoreResult;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setSeparator (String separator)
	{
		this.separator = separator;
	}

	public void release(){
		ignoreErrors = false;
		ignoreResult = false;
		separator = null;
	}

	// ----------------------------------------------------------

	public int doEndTag() throws JspException {
		try {
			StatementTag statementTag =
				(StatementTag) findAncestorWithClass (this, StatementTag.class);

			XDBCResultSequence xdbcResultSequence = statementTag.executeQuery();

			if (ignoreResult) {
				xdbcResultSequence.close();
				return EVAL_PAGE;
			}

			XdbcHelper.concatResult (xdbcResultSequence, pageContext.getOut(), separator);

		} catch (XDBCException e) {
			if (ignoreErrors == false) {
				throw new JspException ("Executing XQuery statement", e);
			}

			e.printStackTrace();
		} catch (IOException e) {
			if (ignoreErrors == false) {
				throw new JspException ("Writing result", e);
			}

			e.printStackTrace();
		}

		release();

		return EVAL_PAGE;
	}
}
