<%@include file="../common/CheckConnectionHeader.jsp" %>

<html>
<head>
<title>Test QuickQuery 2</title>
</head>
<body style="Font-family:arial,helvetica,san-serif;">
<div align="center">
<xq:execute>
	<xq:query>
		<b>This is the result of a remote xquery</b>,
		<br/>, "Congratulations, your connection works"
	</xq:query>
</xq:execute>
<br>
<br>
<form action="/"><input type="submit" value="Go Home"></form>
</div>
</body>
