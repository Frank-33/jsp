<%@ page import = "com.marklogic.xdmp.*,
				   com.marklogic.jsptaglib.xquery.queryhelper.QueryExecuter,
				   com.marklogic.jsptaglib.xquery.queryhelper.Query" %>
<%@ page import = "com.marklogic.xdbc.*" %>

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

<%
  XDMPConnection con = new XDMPConnection("paycheck.demo.marklogic.com", 8004);
  QueryExecuter exec = new QueryExecuter(con);

  Query q = new Query("summary-table('" + ticker + "')",
                      "summarize-comp.xqy",
                      "http://www.w3.org/2003/05/xpath-functions");
  out.println(exec.executeString(q));
%>

</body>
</html>
