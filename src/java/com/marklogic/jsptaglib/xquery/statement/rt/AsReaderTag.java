/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.rt;

import com.marklogic.jsptaglib.AttributeHelper;
import com.marklogic.xdbc.XDBCException;

import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="asReader" description="Assign current result to scripting variable, as Reader"
 *  body-content="empty"
 */
public class AsReaderTag extends TagSupport
{
	private String var = null;
	private String scope = null;

	/**
	 * @jsp:attribute required="true" rtexprvalue="true"
	 */
	public void setScope (String scope) throws JspException
	{
		this.scope = scope;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setVar (String var) throws JspException
	{
		this.var = var;
	}

	// ---------------------------------------------------------

	public int doEndTag () throws JspException
	{
		ResultSequenceTag resultSequence = (ResultSequenceTag)
			findAncestorWithClass (this, ResultSequenceTag.class);

		if (resultSequence == null) {
			throw new JspException ("The xq:asReader tag must be inside xq:resultSequence");
		}

		try {
			AttributeHelper.setScopedAttribute (pageContext, var,
				resultSequence.getCurrentResultReader(), scope);
		} catch (XDBCException e) {
			throw new JspException ("Fetching result as Reader", e);
		}

		return (SKIP_BODY);
	}
}
