<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>

<c:choose>
<c:when test="${param.posting != 'true'}">
<html>
<head><title>Connection Parameters</title></head>
<body>
<div align="center">
Please provide the following information about where to find the CIS server
<form>
Host: <input type="text" name="host"><br>
Port: <input type="text" name="port"><br>
User: <input type="text" name="user"><br>
Pass: <input type="text" name="pass"><br>
<input type="hidden" name="posting" value="true">
<input type="submit" value="Set Connection">
</form>
</div>
</body>
</html>
</c:when>

<c:otherwise>
<xq:setDataSource host="${param.host}" port="${param.port}"
 user="${param.user}" password="${param.pass}"
 scope="session"/>

<c:if test="${sessionScope.redirdestination != null}">
	<c:redirect url="/"/>
</c:if>
</c:otherwise>

</c:choose>
