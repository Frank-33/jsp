/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.el;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="escape"
 *  description="Escape any chars that might have special meaning"
 */
public class EscapeTag extends com.marklogic.jsptaglib.xquery.rt.EscapeTag
{
	/**
	 * Set the characters that should be escaped by preceding them
	 * with a backslash.  These characters are specified as a String.
	 * If a character appears in the input that is contained in this
	 * string, it will be preceded with a backslash character in the
	 * output.<br>
	 * The default set of characters to escape is backslash,
	 * left curly brace, right curly brace,
	 * left angle bracket, right angle bracket, single and double
	 * quote characters.
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setEscapeChars (String escapeChars) throws JspException
	{
		super.setEscapeChars ((String) ExpressionEvaluatorManager.evaluate ("escapeChars", escapeChars, String.class, this, pageContext));
	}
}
