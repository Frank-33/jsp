<%@include file="../common/CheckConnectionHeader.jsp" %>

<html>
<head>
<title>Run XQuery, Iterate Over Result</title>
</head>
<body style="Font-family:arial,helvetica,san-serif;">
<div align="center">
<xq:execute var="result">
	<xq:query>
		<b>This is an xquery body</b>, <i>And this is another</i>,
		<b>And this is number 3</b>, 4, <b>This is five</b>, <i>This is six</i>,
		123.00, "Bamboozle", <span>Bamboozle</span>, doc("/tmp/test.xml")
	</xq:query>
</xq:execute>
<table border="1" cellpadding="3" cellspacing="0">
	<tr style="color:white;background-color:black;"><th>Index</th><th>Value</th><th>Node?</th></tr>
	<c:forEach var="item" items="${result.items}" varStatus="status" >
		<tr>
			<td><c:out value="${status.index + 1}"/></td>
			<td><c:out value="${item.string}" escapeXml="false"/></td>
			<td>
				<c:if test="${item.node == true}">Yes</c:if>
				<c:if test="${item.node == false}">No</c:if>
			</td>
		</tr>
	</c:forEach>
</table>

<br>
<br>
<form action="/"><input type="submit" value="Go Home"></form>
</div>
</body>
