/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.rt;

import com.marklogic.jsptaglib.xquery.XdbcHelper;
import com.marklogic.xdbc.XDBCException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.io.Reader;

/**
 * @jsp:tag name="streamItem" body-content="empty"
 *  description="Send current element to output as a string"
 */
public class StreamItemTag extends BodyTagSupport
{
	private static final int DEFAULT_BUFFER_SIZE = 102400;
	private static final int MIN_BUFFER_SIZE = 1024;

	private int bufferSize = DEFAULT_BUFFER_SIZE;

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setBufferSize (int bufferSize)
	{
		this.bufferSize = bufferSize;

		if (bufferSize < MIN_BUFFER_SIZE) {
			this.bufferSize = MIN_BUFFER_SIZE;
		}
	}

	public void release ()
	{
		bufferSize = DEFAULT_BUFFER_SIZE;
	}

	public int doEndTag () throws JspException
	{
		ResultTag resultTag = (ResultTag) TagSupport.findAncestorWithClass (this, ResultTag.class);

		if (resultTag == null) {
			throw new JspException ("streamItem tag must be nested in a result tag");
		}

		try {
			Reader reader = resultTag.getCurrentReader();

			XdbcHelper.passThroughChars (reader, pageContext.getOut(), bufferSize);

			reader.close();
		} catch (XDBCException e) {
			throw new JspException ("Getting stream reader: " + e, e);
		} catch (IOException e) {
			throw new JspException ("Writing output: " + e, e);
		}

		return (EVAL_PAGE);
	}
}
