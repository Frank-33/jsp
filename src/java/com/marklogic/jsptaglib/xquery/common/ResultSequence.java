/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib.xquery.common;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: May 13, 2004
 * Time: 11:58:04 AM
 */
public interface ResultSequence
{
	int getSize();
	Result [] getSequence();
	Result getResult (int index);
}
