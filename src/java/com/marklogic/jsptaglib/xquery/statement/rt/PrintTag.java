/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.rt;

import com.marklogic.xdbc.XDBCException;
import com.marklogic.jsptaglib.xquery.XdbcHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import java.io.Reader;
import java.io.Writer;
import java.io.IOException;

/**
 * @jsp:tag name="print" body-content="empty"
 *  description="Send current element to output as a string"
 */
public class PrintTag extends BodyTagSupport
{
	public int doEndTag () throws JspException
	{
		ResultSequenceTag resultSequence = (ResultSequenceTag)
			findAncestorWithClass (this, ResultSequenceTag.class);

		if (resultSequence == null) {
			throw new JspException ("The xq:print tag must be inside xq:resultSequence");
		}

		Writer writer = pageContext.getOut();

		try {
//			pageContext.getOut().print (resultSequence.getCurrentResultAsString());

			Reader reader = resultSequence.getCurrentResultReader();

			XdbcHelper.passThroughChars (resultSequence.getCurrentResultReader(), writer, 102400);

			reader.close();
		} catch (IOException e) {
			throw new JspException ("Writing output", e);
		} catch (XDBCException e) {
			throw new JspException ("reading XDBC input", e);
		}

		return (EVAL_PAGE);
	}
}