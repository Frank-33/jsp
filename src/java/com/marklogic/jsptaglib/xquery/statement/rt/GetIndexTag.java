/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.rt;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspException;

import java.io.IOException;

/**
 * @jsp:tag name="getIndex" body-content="empty"
 *  description="Output current index value as a string"
 */
public class GetIndexTag extends BodyTagSupport
{
	private boolean zeroBased = false;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setZeroBased (boolean zeroBased)
	{
		this.zeroBased = zeroBased;
	}

	public int doEndTag () throws JspException
	{
		ResultSequenceTag resultSequence = (ResultSequenceTag)
			findAncestorWithClass (this, ResultSequenceTag.class);

		if (resultSequence == null) {
			throw new JspException ("The xq:getIndex tag must be inside xq:resultSequence");
		}

		int index = resultSequence.getIndex();

		if (zeroBased) {
			index--;
		}

		try {
			pageContext.getOut().print ("" + index);
		} catch (IOException e) {
			throw new JspException ("outputting index", e);
		}

		return (SKIP_BODY);
	}
}
