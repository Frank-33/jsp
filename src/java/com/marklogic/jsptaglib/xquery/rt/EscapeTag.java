/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import java.io.IOException;

/**
 * @deprecated Don not use
 * @jsp:tag name="escape"
 *  description="Escape any chars that might have special meaning"
 */
public class EscapeTag extends BodyTagSupport
{
	protected static String STD_CHARS_TO_ESCAPE = "\\{}<>'\"";

	private String escapeChars = STD_CHARS_TO_ESCAPE;

	// --------------------------------------------------------------

	public void release ()
	{
		escapeChars = STD_CHARS_TO_ESCAPE;
	}

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
		this.escapeChars = escapeChars;
	}

	// --------------------------------------------------------------

	public int doEndTag () throws JspException
	{
		char [] chars = getBodyChars (getBodyContent());
		String escaped = escapeTheseChars (chars, escapeChars);

		JspWriter writer = pageContext.getOut();

		try {
			writer.print (escaped);
		} catch (IOException e) {
			throw new JspException ("I/O Problem escaping string", e);
		}

		return EVAL_PAGE;
	}

	protected String escapeTheseChars (char [] chars, String escapeChars)
	{
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			char c = chars [i];

			if (escapeChars.indexOf (c) != -1) {
				sb.append ('\\');
			}

			sb.append (c);
		}

		return (sb.toString());
	}

	private char[] getBodyChars (BodyContent bodyContent)
	{
		String body = bodyContent.getString();

		if (body == null) {
			return (new char [0]);
		}

		return (body.trim().toCharArray());
	}
}
