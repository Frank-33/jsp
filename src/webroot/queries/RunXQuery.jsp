<%@include file="/common/CheckConnectionHeader.jsp" %>

<c:if test="${param.posting == true}">
	<c:catch var="xqexception">
		<xq:execute var="xqresult" query="${param.xquery}" />
	</c:catch>
</c:if>

<html>
<head><title>Run Arbitrary XQuery Code</title></head>
<body>
<div>Enter your query here</div>
<br>
<form method="POST">
	<input type="hidden" name="posting" value="true">
	<textarea name="xquery" rows="30" cols="80"><c:out value="${param.xquery}" escapeXml="false" /></textarea>
	<br>
	Output Result as:
	<input type="submit" name="textbutton" value="Text">
	<input type="submit" name="xmlbutton" value="XML">
	<input type="submit" name="xhtmlbutton" value="XHTML">
</form>
<br>

<c:if test="${xqresult != null}">
XQuery Output<br>
<div style="border: 1px solid black; padding: 3px">
	<c:forEach var="item" items="${xqresult.items}" varStatus="status"><c:out value="${item.string}" escapeXml="${param.xhtmlbutton == null}"/></c:forEach>
</div>
</c:if>

<c:if test="${xqexception != null}">
XQuery Exception<br>
<div style="border: 4px solid red; padding: 3px">
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

<br>
<form action="/"><input type="submit" value="Go Home"></form>

</body>