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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @jsp:tag name="variable"
 *  description="Set named parameter for the query or module invocation"
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class VariableTag extends BodyTagSupport
{
	private String namespace = null;
	private String localname = null;
	private String type = null;
	private String value = null;

	// -----------------------------------------------------------

	public void release ()
	{
		namespace = null;
		localname = null;
		type = null;
		value = null;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setNamespace (String namespace) throws JspException
	{
		this.namespace = namespace;
	}

	/**
	 * @jsp:attribute required="true" rtexprvalue="true"
	 */
	public void setLocalname (String localname) throws JspException
	{
		this.localname = localname;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setType (String type) throws JspException
	{
		this.type = type;
	}

	/**
	 * @jsp:attribute required="false" rtexprvalue="true"
	 */
	public void setValue (String value) throws JspException
	{
		this.value = value;
	}

	// -----------------------------------------------------------

	public int doEndTag () throws JspException
	{
		ExecuteTag executeTag = (ExecuteTag) findAncestorWithClass (this, ExecuteTag.class);

		if (executeTag == null) {
			throw new JspException ("param tag must be nested in an execute tag");
		}

		if (value == null) {
			value = getBodyContent().getString();
		}

		executeTag.addParam (namespace, localname, type, value);

		return EVAL_PAGE;
	}

}
