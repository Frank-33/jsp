<%@include file="../common/CheckConnectionHeader.jsp" %>

<c:if test="${param.posting == true}">
	<c:catch var="xqexception">
		<xq:execute var="xqresult">
			<xq:query>
				declare namespace foo="foons"
                                define variable $ext-string external
                                define variable $ext-int external
                                define variable $foo:ext-ns-string external

				(fn:concat ("String: ", $ext-string),
				 fn:concat ("int: ", $ext-int),
				 fn:concat ("ns: ", $foo:ext-ns-string))
			</xq:query>
			<xq:variable localname="ext-string" type="xs:string" value="${param.extstring}"/>
			<xq:variable localname="ext-int" type="xs:integer" value="${param.extint}"/>
			<xq:variable namespace="foons" localname="ext-ns-string" type="xs:string"><%=request.getParameter ("extnsstring")%></xq:variable>
		</xq:execute>
	</c:catch>
</c:if>

<html>
<head><title>Pass Variables</title>
<link rel="stylesheet" href="/jsp.css" media="screen" />
</head>
<body>
<div id="header"><h1>Enter some variable values</h1></div>
<div id="main">
<form method="POST">
	<input type="hidden" name="posting" value="true">

	Enter a String: <input type="text" name="extstring" value="<%=request.getParameter ("extstring")%>"></br>
	Enter an integer: <input type="text" name="extint" value="<%=request.getParameter ("extint")%>"></br>
	Enter another String: <input type="text" name="extnsstring" value="<%=request.getParameter ("extnsstring")%>"></br>
	<br/>

	<input type="submit" value="Run">
</form>

<c:if test="${xqresult != null}">
<h1>XQuery Output</h1>
<div id="xqueryresult">
	<c:forEach var="item" items="${xqresult.items}" varStatus="status">
		<c:out value="${item.string}" escapeXml="true"/><br/>
	</c:forEach>
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
