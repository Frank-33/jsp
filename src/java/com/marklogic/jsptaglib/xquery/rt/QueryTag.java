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

import com.marklogic.jsptaglib.TagPropertyHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @jsp:tag name="query" description="Contains XQuery code to be executed"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class QueryTag extends BodyTagSupport
{
	private String query = null;

	// ------------------------------------------------------------

	public void release ()
	{
		query = null;
	}

	// ------------------------------------------------------------

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setQuery (String query) throws JspException
	{
		this.query = query;
	}

	// ------------------------------------------------------------

	public int doEndTag () throws JspException
	{
		if (TagSupport.findAncestorWithClass (this, ExecuteTag.class) == null) {
			throw new JspException ("The query tag must be nested in an execute tag");
		}

		TagPropertyHelper.assignAncestorProperty (this, ExecuteTag.class, "query", getQueryString());

		release();

		return (EVAL_PAGE);
	}

	// ------------------------------------------------------------

	protected String getQueryString()
	{
		if (query != null) {
			return query;
		}

		return (getBodyContent().getString());
	}
}
