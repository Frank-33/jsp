<%@ page language="java" %>
<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>

<!-- This page is now defunct, nested execute tag is no longer availale -->

<html>
<head>
<title>Test Statement</title>
</head>
<body style="Font-family:arial,helvetica,san-serif;">
<div align="center">
<xq:setDataSource>
	<xq:host>ronsoft.net</xq:host>
	<xq:port>8003</xq:port>
</xq:setDataSource>
<h2>Connection opened</h2>

<xq:statement var="xqstmt">
	<xq:query>
		<b>This is an xquery body</b>, <i>And this is another</i>,
		<b>And this is number 3</b>, 4, <b>This is five</b>, <i>This is six</i>,
		123.00, "Bamboozle", <span>Bamboozle</span>, doc("/tmp/test.xml")
	</xq:query>
	ResultSequence for Query:<br/><br/>
	<xq:execute separator="|" />
</xq:statement>

<xq:unSetDataSource/>
<h2>Connection closed</h2>
</div>
</body>
