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
