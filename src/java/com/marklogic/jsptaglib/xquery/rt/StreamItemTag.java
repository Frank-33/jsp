/*
 * Copyright (c)2004 Mark Logic Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * The use of the Apache License does not indicate that this project is
 * affiliated with the Apache Software Foundation.
 */
package com.marklogic.jsptaglib.xquery.rt;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * @jsp:tag name="streamItem" body-content="empty"
 *  description="Send current element to output as a string"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class StreamItemTag extends BodyTagSupport
{
	private static final int DEFAULT_BUFFER_SIZE = 102400;
	private static final int MIN_BUFFER_SIZE = 1024;

	private int bufferSize = DEFAULT_BUFFER_SIZE;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
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

			passThroughChars (reader, pageContext.getOut(), bufferSize);

			reader.close();
		} catch (IOException e) {
			throw new JspException ("Writing output: " + e, e);
		}

		return (EVAL_PAGE);
	}

	/**
	 * Copy all chars from the Reader to the Writer
	 * @param reader A source of chars
	 * @param writer The sink that accepts chars
	 * @param buffersize The size of the intermediate buffer to use
	 * @throws IOException Thrown if there is an I/O problem
	 */
	private void passThroughChars (Reader reader, Writer writer,
		int buffersize)
		throws IOException
	{
		int rc;
		char buffer [] = new char [buffersize];

		while ((rc = reader.read (buffer)) > 0) {
			writer.write (buffer, 0, rc);
		}
	}
}
