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
package com.marklogic.jsptaglib.xquery.el;

import com.marklogic.xdmp.XDMPDataSource;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;

/**
 * @jsp:tag name="execute" description="Execution context for query"
 *  body-content="JSP"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class ExecuteTag extends com.marklogic.jsptaglib.xquery.rt.ExecuteTag
{
	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setVar (String var) throws JspException
	{
		super.setVar ((String) ExpressionEvaluatorManager.evaluate ("var", var, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setScope (String scope) throws JspException
	{
		super.setScope ((String) ExpressionEvaluatorManager.evaluate ("scope", scope, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setQuery (String query) throws JspException
	{
		super.setQuery ((String) ExpressionEvaluatorManager.evaluate ("query", query, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setModule (String module) throws JspException
	{
		super.setModule ((String) ExpressionEvaluatorManager.evaluate ("module", module, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setSeparator (String separator) throws JspException
	{
		super.setSeparator ((String) ExpressionEvaluatorManager.evaluate ("separator", separator, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setDataSource (String dataSource) throws JspException
	{
		super.setDataSource ((XDMPDataSource) ExpressionEvaluatorManager.evaluate ("dataSource", dataSource, XDMPDataSource.class, this, pageContext));
	}
}
