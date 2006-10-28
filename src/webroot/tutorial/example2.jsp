<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>

<xq:setDataSource host="localhost" port="8003" user="admin" password="admin"/>

<html><head><title>xq:query example 2</title></head>
<body>
My favorite fruits:
<ul>
<xq:execute>
    <xq:query>
        ("apple", "pear", "orange", "guava")
    </xq:query>
    <xq:result>
       <li><xq:streamItem/></li>
    </xq:result>
</xq:execute>
</ul>
</body>
</html>