<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>

<xq:setDataSource host="localhost" port="8003" user="test" password="test"/>

<html><head><title>xq:query example 1</title></head>
<body>
My favorite fruits:
<ul>
<xq:execute>
    <xq:query>
        for $i in ("apple", "pear", "orange", "guava")
          return <li>{ $i }</li>
    </xq:query>
</xq:execute>
</ul>
</body>
</html>