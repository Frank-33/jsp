<%@include file="../common/CheckConnectionHeader.jsp" %>

<c:if test="${param.posting == true}">
	<c:catch var="xqexception">
		<xq:execute var="xqresult" query="${param.xquery}" />
	</c:catch>
</c:if>

<html>
<head><title>Run Arbitrary XQuery Code</title>
<link rel="stylesheet" href="/jsp.css" media="screen" />
</head>
<body>
<div id="header"><h1>Enter your query here</h1></div>
<div id="main">
<form action="" method="POST">
	<input type="hidden" name="posting" value="true">
	<textarea name="xquery" rows="30" cols="80"><c:out value="${param.xquery}" escapeXml="false" /></textarea>
	<br>
	Output Result as:
	<input type="submit" name="textbutton" value="Text">
	<input type="submit" name="xhtmlbutton" value="XHTML">
</form>

<c:if test="${xqresult != null}">
<h1>XQuery Output</h1>
<div id="xqueryresult">
	<c:forEach var="item" items="${xqresult.items}" varStatus="status"><c:out value="${item.string}" escapeXml="${param.xhtmlbutton == null}"/></c:forEach>
</div>
</c:if>

<c:if test="${xqexception != null}">
<h1>XQuery Exception</h1>
<div id="xqueryresulterror">
	<c:choose>
		<c:when test="${xqexception.cause != null}">
			<c:out value="${xqexception.cause.message}"/>
		</c:when>
		<c:otherwise>
			<c:out value="${xqexception.message}"/>
		</c:otherwise>
	</c:choose>
</div>
</c:if>

<form action="/"><input type="submit" value="Go Home"></form>
</div>
</body>
</html>