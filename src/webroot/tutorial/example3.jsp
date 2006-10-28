<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>

<xq:setDataSource host="localhost" port="8003" user="admin" password="admin"/>
<xq:execute var="result">
    <xq:query>
        ("apple", "pear", "orange", "guava")
    </xq:query>
</xq:execute>

<html><head><title>xq:query example 3</title></head>
<body>
My favorite fruits:
<ul>
    <c:forEach var="item" items="${result.items}">
        <li><c:out value="${item.string}"/></li>
    </c:forEach>
</ul>
</body>
</html>