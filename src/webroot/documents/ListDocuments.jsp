<%@include file="../common/CheckConnectionHeader.jsp" %>

<html>
<head>
<title>Test QuickQuery 2</title>
<link rel="stylesheet" href="/jsp.css" media="screen" />
</head>
<body style="Font-family:arial,helvetica,san-serif;">
<div id="header"><h1>Documents In The Server</h1></div>
<div id="main">
<xq:execute>
	<xq:query>
		for $n in input() return xdmp:node-uri($n)
	</xq:query>

	<table align="center" border="1" cellpadding="3" cellspacing="0">
	<tr><th>Document URI</th><th colspan="3">Display As</th></tr>
	<xq:result var="item">
	<tr>
		<td><c:out value="${item.string}"/></td>
		<td><a href="<c:url value="ShowDocument.jsp"><c:param name="type" value="plain"/><c:param name="doc" value="${item.string}"/></c:url>">Text</a></td>
		<td><a href="<c:url value="ShowDocument.jsp"><c:param name="type" value="xml"/><c:param name="doc" value="${item.string}"/></c:url>">XML</a></td>
		<td><a href="<c:url value="ShowDocument.jsp"><c:param name="type" value="html"/><c:param name="doc" value="${item.string}"/></c:url>">XHTML</a></td>
	</tr>
	</xq:result>
	</table>
</xq:execute>
<form action="/"><input type="submit" value="Go Home"></form>
</div>
</body>
</html>