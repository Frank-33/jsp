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

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import javax.servlet.jsp.JspException;


/**
 * @jsp:tag name="param"
 *  description="Set named parameter for the query or module invocation"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class ParamTag extends com.marklogic.jsptaglib.xquery.rt.ParamTag
{
	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setNamespace (String namespace) throws JspException
	{
		super.setNamespace ((String) ExpressionEvaluatorManager.evaluate ("namespace", namespace, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="true" rtexprvalue="false"
	 */
	public void setLocalname (String localname) throws JspException
	{
		super.setLocalname ((String) ExpressionEvaluatorManager.evaluate ("localname", localname, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setType (String type) throws JspException
	{
		super.setType ((String) ExpressionEvaluatorManager.evaluate ("type", type, String.class, this, pageContext));
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="false"
	 */
	public void setValue (String value) throws JspException
	{
		super.setValue ((String) ExpressionEvaluatorManager.evaluate ("value", value, String.class, this, pageContext));
	}
}
