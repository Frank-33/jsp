/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.statement.rt;


/**
 * @jsp:tag name="isNotNode" description="Evaluates body if current result is not a Node"
 */
public class IsNotNodeTag extends IsNodeTag
{
	public IsNotNodeTag ()
	{
		setCondition (false);
	}
}
