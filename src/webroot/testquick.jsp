<%@ page language="java" %>
<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>

<html>
<head>
<title>Test Statement</title>
</head>
<body style="Font-family:arial,helvetica,san-serif;">
<div align="center">
<xq:setDataSource host="ronsoft.net" port="8003"/>
<xq:execute separator="|">
	<xq:query>
		<b>This is an xquery body</b>, <i>And this is another</i>,
		<b>And this is number 3</b>, 4, <b>This is five</b>, <i>This is six</i>,
		123.00, "Bamboozle", <span>Bamboozle</span>, doc("/tmp/test.xml")
	</xq:query>
</xq:execute>
<br>
<br>
<xq:execute separator="|" query="1,2,3" />
</div>
</body>
