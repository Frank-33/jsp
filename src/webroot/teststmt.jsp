<%@ page language="java" %>
<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>

<html>
<head>
<title>Test Statement</title>
</head>
<body style="Font-family:arial,helvetica,san-serif;">
<div align="center">

<xq:setDataSource host="ronsoft.net" port="8003"/>

<xq:execute>
	<xq:query>
		<b>This is an xquery body</b>, <i>And this is another</i>,
		<b>And this is number 3</b>, 4, <b>This is five</b>, <i>This is six</i>,
		123.00, "Bamboozle", <span>Bamboozle</span>, doc("/tmp/test.xml")
	</xq:query>
	ResultSequence for Query<br/><br/>
	<table border="1" cellpadding="3" cellspacing="0">
	<tr style="color:white;background-color:black;"><th>Position</th><th>Value</th><th>Node?</th></tr>
	<xq:result var="item">
		<tr>
			<td align="right"><c:out value="${item.index + 1}"/></td>
<!--
			<td>(xq:streamItem bufferSize="237" /></td>
-->
			<td><c:out value="${item.string}" escapeXml="false" /></td>
			<td align="center">
				<c:if test="${item.node == true}">Yes</c:if>
				<c:if test="${item.node == false}">No</c:if>
			</td>
		</tr>
	</xq:result>
	</table>
</xq:execute>

</div>
</body>
