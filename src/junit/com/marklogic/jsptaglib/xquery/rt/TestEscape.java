package com.marklogic.jsptaglib.xquery.rt;

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Jun 14, 2004
 * Time: 3:08:47 PM
 */
public class TestEscape extends TestCase
{
	public void testStd()
	{
		EscapeTag tag = new EscapeTag();

		assertEquals ("xyz", tag.escapeTheseChars ("xyz".toCharArray(), EscapeTag.STD_CHARS_TO_ESCAPE));
		assertEquals ("x\\{y\\}z", tag.escapeTheseChars ("x{y}z".toCharArray(), EscapeTag.STD_CHARS_TO_ESCAPE));
		assertEquals ("x\\<y\\>z", tag.escapeTheseChars ("x<y>z".toCharArray(), EscapeTag.STD_CHARS_TO_ESCAPE));
		assertEquals ("x\\\"y\\'z", tag.escapeTheseChars ("x\"y'z".toCharArray(), EscapeTag.STD_CHARS_TO_ESCAPE));
		assertEquals ("x\\\\y\\\\z", tag.escapeTheseChars ("x\\y\\z".toCharArray(), EscapeTag.STD_CHARS_TO_ESCAPE));
	}

	public void testNonStd()
	{
		String esc = "*$#";
		EscapeTag tag = new EscapeTag();

		assertEquals ("xyz", tag.escapeTheseChars ("xyz".toCharArray(), esc));
		assertEquals ("x{y}z", tag.escapeTheseChars ("x{y}z".toCharArray(), esc));
		assertEquals ("x<y>z", tag.escapeTheseChars ("x<y>z".toCharArray(), esc));
		assertEquals ("x\"y'z", tag.escapeTheseChars ("x\"y'z".toCharArray(), esc));
		assertEquals ("x\\y\\z", tag.escapeTheseChars ("x\\y\\z".toCharArray(), esc));

		assertEquals ("x\\*yz", tag.escapeTheseChars ("x*yz".toCharArray(), esc));
		assertEquals ("x\\$yz", tag.escapeTheseChars ("x$yz".toCharArray(), esc));
		assertEquals ("x\\#yz", tag.escapeTheseChars ("x#yz".toCharArray(), esc));
	}
}
