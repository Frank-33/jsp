<%@ page import = "com.marklogic.xdmp.*" %>
<%@ page import = "com.marklogic.xdbc.*" %>

<html>
<head>
  <title>Executive Pay Check, Powered by Mark Logic</title>
  <link rel="stylesheet" type="text/css" href="style.css" />
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
  XDBCStatement stmt = null;
  XDBCResultSequence result = null;
  try {
    stmt = con.createStatement();
    result = stmt.executeQuery(
      "import module 'http://www.w3.org/2003/05/xpath-functions' " +
      "at 'summarize-comp.xqy' " +
      "summary-table('" + ticker + "')");

    while (result.hasNext()) {
      result.next();
      switch (result.getItemType()) {
        case XDBCResultSequence.XDBC_Node:
          out.println((result.getNode().asString()));
          break;
        case XDBCResultSequence.XDBC_String:
          out.println(result.get_String());
          break;
        default:
          throw new RuntimeException(
            "Got non-string type: " + result.getItemType());
      }
    }
  }
  finally {
    if (stmt != null) {
      try {
        stmt.close();
      }
      catch (Exception e) { }
    }
    if (result != null) {
      try {
        result.close();
      }
      catch (Exception e) { }
    }
  }
%>

</body>
</html>
