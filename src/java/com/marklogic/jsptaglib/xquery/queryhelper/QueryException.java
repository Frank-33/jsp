package com.marklogic.jsptaglib.xquery.queryhelper;

/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */

/**
 * @author Jason Hunter (jason.hunter@marklogic.com)
 */
public class QueryException extends RuntimeException {

  public QueryException() { }

  public QueryException(String msg) { super(msg); }
}
