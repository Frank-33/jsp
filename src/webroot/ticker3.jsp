<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>

<html>
<head>
  <title>Executive Pay Check, Powered by Mark Logic</title>
  <link rel="stylesheet" type="text/css" href="style.css">
  <base href="http://paycheck.demo.marklogic.com">
</head>
<body style="margin-left: 20px; margin-top: 20px">

<%
  String ticker = request.getParameter("ticker");
  if (ticker == null) ticker = "";
  ticker = ticker.toUpperCase();
%>

<form action="<%="http://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI()%>">
Ticker: <input name="ticker" value="<%= ticker %>">
</form>

<%
  if (ticker.equals("")) {
	  return;
  }
%>

<xq:setDataSource host="paycheck.demo.marklogic.com" port="8004" user="demo" password="demo" />
<xq:execute>
	<xq:query>
	    import module 'http://www.w3.org/2003/05/xpath-functions' at 'summarize-comp.xqy'
		summary-table('<%=ticker%>')
	</xq:query>
</xq:execute>
</body>
</html>
