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
package com.marklogic.jsptaglib;

import javax.servlet.jsp.PageContext;

/**
 * Helper class for getting, setting and removing attributes.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class AttributeHelper
{
	private AttributeHelper ()
	{
		// Helper class, can't be instantiated
	}

	/**
	 * Get attribute from page context of the given name in the given scope.
	 * @param pageContext Page context reference.
	 * @param name The name of the attribute.
	 * @param scope The scope as defined in the PageContext class.
	 * @return The value of the attribute, or null.
	 */
	public static Object getScopedAttribute (PageContext pageContext,
	        String name, int scope)
	{
		return ((name == null) ? null : pageContext.getAttribute (name, scope));
	}

	/**
	 * Get attribute from page context of the given name in the given scope.
	 * @param pageContext Page context reference.
	 * @param name The name of the attribute.
	 * @param scope The scope name as a string: page, request, session or application.
	 * @return The value of the attribute, or null.
	 */
	public static Object getScopedAttribute (PageContext pageContext,
	        String name, String scope)
	{
		return (getScopedAttribute (pageContext, name, attributeScopeFromName (scope)));
	}

	/**
	 * Set or replace the value of a named attribute.
	 * @param pageContext Page context reference.
	 * @param name The name of the attribute.
	 * @param value The value to which the attribute should be set.
	 * @param scope The scope as defined in the PageContext class.
	 */
	public static void setScopedAttribute (PageContext pageContext,
	        String name, Object value, int scope)
	{
		pageContext.setAttribute (name, value, scope);
	}

	/**
	 * Set or replace the value of a named attribute.
	 * @param pageContext Page context reference.
	 * @param name The name of the attribute.
	 * @param value The value to which the attribute should be set.
	 * @param scope The scope name as a string: page, request, session or application.
	 */
	public static void setScopedAttribute (PageContext pageContext,
	        String name, Object value, String scope)
	{
		setScopedAttribute (pageContext, name, value, attributeScopeFromName (scope));
	}

	/**
	 * Remove a named attribute.
	 * @param pageContext Page context reference.
	 * @param name The name of the attribute.
	 * @param scope The scope as defined in the PageContext class.
	 */
	public static void removeScopedAttribute (PageContext pageContext,
	        String name, int scope)
	{
		pageContext.removeAttribute (name, scope);
	}

	/**
	 * Remove a named attribute.
	 * @param pageContext Page context reference.
	 * @param name The name of the attribute.
	 * @param scope The scope name as a string: page, request, session or application.
	 */
	public static void removeScopedAttribute (PageContext pageContext,
	        String name, String scope)
	{
		removeScopedAttribute (pageContext, name, attributeScopeFromName (scope));
	}

	/**
	 * Given the name of a scope as a string (page, request, session or application),
	 * return the corresponding numeric code as defined in the PageContext class.
	 * @param scope The scope name as a string: page, request, session or application.
	 * @return The numeric scope code as defined in the PageContext class.
	 */
	public static int attributeScopeFromName (String scope)
	{
		int scopeValue = PageContext.PAGE_SCOPE;

		if (scope != null) {
			if (scope.equalsIgnoreCase ("page")) {
				scopeValue = PageContext.PAGE_SCOPE;
			}
			if (scope.equalsIgnoreCase ("request")) {
				scopeValue = PageContext.REQUEST_SCOPE;
			}
			if (scope.equalsIgnoreCase ("session")) {
				scopeValue = PageContext.SESSION_SCOPE;
			}
			if (scope.equalsIgnoreCase ("application")) {
				scopeValue = PageContext.APPLICATION_SCOPE;
			}
		}

		return scopeValue;
	}
}
