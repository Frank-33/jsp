/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement;

import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.jsptaglib.xquery.XdbcHelper;
import com.marklogic.jsptaglib.xquery.statement.rt.StatementTag;
import com.marklogic.xdbc.XDBCException;
import com.marklogic.xdbc.XDBCResultSequence;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated This tag has been deprecated
 * xx@jsp:tag name="setResult" description="Execute XQuery, store result in attribute"
 * xx@jsp:variable name-from-attribute="id" class="java.lang.Object"
 *  declare="true" scope="AT_END"
 */
public class SetResultTag extends BodyTagSupport
{
	private boolean firstOnly = false;
	private boolean ignoreErrors = false;
	private String scope = null;

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public String getId ()
	{
		return super.getId ();
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setFirstOnly (boolean firstOnly)
	{
		this.firstOnly = firstOnly;
	}

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
	public void setScope (String scope)
	{
		this.scope = scope;
	}

	public void release(){
		firstOnly = false;
		ignoreErrors = false;
		scope = null;
	}

	// ----------------------------------------------------------

	public int doEndTag()
		throws JspException
	{
		XDBCResultSequence xdbcResultSequence = null;

		try {
			StatementTag statementTag =
				(StatementTag) findAncestorWithClass (this, StatementTag.class);

			if (statementTag == null) {
				throw new JspException ("the xq:setResult tag must be with xs:statement");
			}

			xdbcResultSequence = statementTag.executeQuery();

			if (xdbcResultSequence.hasNext() == false) {
				AttributeHelper.removeScopedAttribute (pageContext, getId (), scope);
				return (EVAL_PAGE);
			}

			if (firstOnly) {
				xdbcResultSequence.next();
				AttributeHelper.setScopedAttribute (pageContext, getId (),
					XdbcHelper.resultAsObject (xdbcResultSequence), scope);

				return (EVAL_PAGE);
			}

			List list = new ArrayList();

			while (xdbcResultSequence.hasNext ()) {
				xdbcResultSequence.next ();

				list.add (XdbcHelper.resultAsObject (xdbcResultSequence));
			}

			Object [] array = new Object [list.size ()];

			list.toArray (array);

			AttributeHelper.setScopedAttribute (pageContext, getId (), array, scope);

		} catch (XDBCException e) {
			if (ignoreErrors == false) {
				throw new JspException ("Executing XQuery statement", e);
			}

			e.printStackTrace();

		} finally {
			if (xdbcResultSequence != null) {
				try {
					xdbcResultSequence.close ();
				} catch (XDBCException e) {
					// nothing, don't care
				}
			}
		}

		release();

		return EVAL_PAGE;
	}
}
