/*
 * Copyright 2004 Mark Logic Corporation. All Rights Reserved.
 */
package com.marklogic.jsptaglib;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;

import java.lang.reflect.Method;

/**
 * Helper class containing methods to aid in setting properties of
 * enclosing parent tags.
 * @author Ron Hitchens (ron.hitchens@marklogic.com)
 */
public class TagPropertyHelper extends BodyTagSupport
{
	private TagPropertyHelper()
	{
		// Helper class, can't be instantiated
	}

	/**
	 * Assign a property with the value of the given Object.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value A Object, which will be set as the value of the named property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, Object value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, value.getClass());

		invokeSetter (setter, ancestor, value, propertyName);
	}

	/**
	 * Assign a property with the given primitive value.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value The int value to be assigned to the property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, int value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, java.lang.Integer.TYPE);

		invokeSetter (setter, ancestor, new Integer (value), propertyName);
	}

	/**
	 * Assign a property with the given primitive value.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value The long value to be assigned to the property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, long value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, java.lang.Long.TYPE);

		invokeSetter (setter, ancestor, new Long (value), propertyName);
	}

	/**
	 * Assign a property with the given primitive value.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value The float value to be assigned to the property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, float value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, java.lang.Float.TYPE);

		invokeSetter (setter, ancestor, new Float (value), propertyName);
	}

	/**
	 * Assign a property with the given primitive value.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value The double value to be assigned to the property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, double value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, java.lang.Double.TYPE);

		invokeSetter (setter, ancestor, new Double (value), propertyName);
	}

	/**
	 * Assign a property with the given primitive value.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value The byte value to be assigned to the property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, byte value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, java.lang.Byte.TYPE);

		invokeSetter (setter, ancestor, new Byte (value), propertyName);
	}

	/**
	 * Assign a property with the given primitive value.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value The char value to be assigned to the property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, char value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, java.lang.Character.TYPE);

		invokeSetter (setter, ancestor, new Character (value), propertyName);
	}

	/**
	 * Assign a property with the given primitive value.
	 * @param subTag A reference to the assigning Tag instance.
	 * @param ancestorClass The Class of the enclosing Tag on which
	 *  the property is to be assigned.
	 * @param propertyName The name (as a String) of the property to be assigned.
	 * @param value The boolean value to be assigned to the property.
	 * @throws JspException Thrown if there is a problem making the assignment.
	 */
	public static void assignAncestorProperty (Tag subTag, Class ancestorClass,
		String propertyName, boolean value)
		throws JspException
	{
		Tag ancestor = getAncestor (subTag, ancestorClass);
		Method setter = getSetter (ancestor, propertyName, java.lang.Boolean.TYPE);

		invokeSetter (setter, ancestor, new Boolean (value), propertyName);
	}

	// ---------------------------------------------------------------------

	private static Tag getAncestor (Tag subTag, Class ancestorClass)
		throws JspException
	{
		Tag ancestor = TagSupport.findAncestorWithClass (subTag, ancestorClass);

		if (ancestor == null) {
			throw new JspException ("Parent tag not found, this tag must be nested");
		}

		return ancestor;
	}

	private static Method getSetter (Object object, String propertyName, Class propertyType)
		throws JspException
	{
		String methodName = toSetterName (propertyName);
		Class [] params = { propertyType };

		try {
			return (object.getClass ().getMethod (methodName, params));
		} catch (NoSuchMethodException e) {
			throw new JspException ("Setter for property '" + propertyName
				+ "' (" + propertyType.getName() + ") not found on class "
				+ object.getClass().getName());
		}
	}

	private static void invokeSetter (Method setter, Object instance, Object value, String propertyName)
		throws JspException
	{
		Object [] params = { value };

		try {
			setter.invoke (instance, params);
		} catch (Exception e) {
			throw new JspException ("Setter for property '" + propertyName
				+ "' on class " + instance.getClass().getName(), e);
		}
	}

	private static String toSetterName (String propertyName)
	{
		StringBuffer sb = new StringBuffer ("set");

		sb.append (propertyName);
		sb.setCharAt (3, Character.toUpperCase (propertyName.charAt (0)));

		return (sb.toString ());
	}
}
