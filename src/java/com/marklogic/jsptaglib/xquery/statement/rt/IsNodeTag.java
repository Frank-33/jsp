/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.rt;

import com.marklogic.xdbc.XDBCException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @jsp:tag name="isNode" description="Evaluates body if current result is a Node"
 */
public class IsNodeTag extends TagSupport
{
	private boolean condition = true;

	public void setCondition (boolean condition)
	{
		this.condition = condition;
	}

	public void release ()
	{
		condition = true;
	}

	public int doStartTag () throws JspException
	{
		ResultSequenceTag resultSequence = (ResultSequenceTag)
			findAncestorWithClass (this, ResultSequenceTag.class);

		if (resultSequence == null) {
			throw new JspException ("The xq:isNode/isNotNode tag must be inside xq:resultSequence");
		}

		try {
			if (resultSequence.isCurrentResultNode() == condition) {
				return (EVAL_BODY_INCLUDE);
			}
		} catch (XDBCException e) {
			throw new JspException ("Determining result type", e);
		}

		return (SKIP_BODY);
	}
}
