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
 * @jsp:tag name="escape"
 *  description="Escape any chars that might have special meaning"
 */
public class EscapeTag extends BodyTagSupport
{
	public int doEndTag () throws JspException
	{
		JspWriter writer = pageContext.getOut();
		char [] chars = getBodyChars (getBodyContent());

		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];

			// FIXME: get the rules right here for XQuery
			try {
				if (c == '\'') {
					writer.print ('\'');
				}

				if (c == '{') {
					writer.print ('\\');
				}

				writer.print (c);
			} catch (IOException e) {
				throw new JspException ("I/O Problem escaping string", e);
			}
		}

		return EVAL_PAGE;
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
