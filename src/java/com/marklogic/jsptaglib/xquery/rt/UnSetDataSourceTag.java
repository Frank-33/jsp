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

import com.marklogic.jsptaglib.AttributeHelper;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @jsp:tag name="unSetDataSource" body-content="empty"
 *  description="Clear a datasource attribute.  If a variable
 *  name is provided, that variable will be cleared.  If
 *  no name is given, the default name used by setDataSource
 *  will be cleared.  If a scope is provided, the attribute
 *  in that scope will be cleared.  Otherwise the default
 *  PageContext search will be applied.  This tag is generally
 *  only useful for session and application scope."
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class UnSetDataSourceTag extends TagSupport
{
	private String scope = null;
	private String var = SetDataSourceTag.ML_DEFAULT_DATASOURCE_VAR;

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setScope (String scope) throws JspException
	{
		this.scope = scope;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setVar (String var) throws JspException
	{
		this.var = var;
	}

	public int doStartTag () throws JspException
	{
		AttributeHelper.removeScopedAttribute (pageContext, var, scope);

		return (EVAL_PAGE);
	}
}
