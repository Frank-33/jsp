<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>

<c:choose>
<c:when test="${param.posting != 'true'}">
<html>
<head><title>Connection Parameters</title>
<link rel="stylesheet" href="/jsp.css" media="screen" />
</head>
<body>
<div id="header"><h1>Please provide connection information for the CIS server</h1></div>
<div id="main">
	<form>
	Host: <input type="text" name="host"><br>
	Port: <input type="text" name="port"><br>
	User: <input type="text" name="user"><br>
	Pass: <input type="text" name="pass"><br>
	<input type="hidden" name="posting" value="true">
	<br>
	<input type="submit" value="Set Connection DataSource">
	</form>
</div>
</body>
</html>
</c:when>

<c:otherwise>
<xq:setDataSource host="${param.host}" port="${param.port}"
 user="${param.user}" password="${param.pass}"
 scope="session"/>

<c:if test="${param.redirdestination != null}">
	<c:redirect url="${param.redirdestination}"/>
</c:if>
</c:otherwise>

</c:choose>
