<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>

<xq:setDataSource host="localhost" port="8003" user="admin" password="admin"/>

<html><head><title>xq:query example 4</title></head>
<body>
My favorite fruits:
<ul>
<xq:execute>
    <xq:query>
        ("apple", "pear", "orange", "guava")
    </xq:query>
    <xq:result var="item">
        <li><c:out value="${item.string}"/></li>
    </xq:result>
</xq:execute>
</ul>
</body>
</html>