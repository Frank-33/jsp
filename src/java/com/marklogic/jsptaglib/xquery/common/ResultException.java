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
package com.marklogic.jsptaglib.xquery.common;

/**
 * General purpose exception indicating a problem with
 * a result.  All instances of thi class will be chained
 * to a causing exception (getCause()).
 * @author Ron Hitchens, Mark Logic Corporation
 */
public class ResultException extends Exception
{
	public ResultException()
	{
	}

	public ResultException (String message)
	{
		super (message);
	}

	public ResultException (String message, Throwable cause)
	{
		super (message, cause);
	}

	public ResultException (Throwable cause)
	{
		super (cause);
	}
}
