<html>
<head><title>JSP - XQuery Index Page</title>
<link rel="stylesheet" href="jsp.css" media="screen" />
<head>
<body>
<div id="header"><h1>Mark Logic XQuery JSP Tags Demo App</h1></div>
<div id="main">
<p>&nbsp;</p>
	<table align="center" border="0" cellpadding="0">
		<tr><th colspan="2">Connection</th><tr>
		<tr><td colspan="2">
<p>
These JSPs (except the tutorial examples)
look for a datasource in session scope.  Use the links in
this section to setup information about where your
MarkLogic Server instance is.
</p>
		</td></tr>
		<tr><td><a href="connection/SetConnection.jsp">Setup Connection</a></td><td width="1%"><a href="source/connection/SetConnection.jsp.txt">Source</a></td></tr>
		<tr><td><a href="connection/ClearConnection.jsp">Clear Connection</a></td><td width="1%"><a href="source/connection/ClearConnection.jsp.txt">Source</a></td></tr>
		<tr><td><a href="connection/TestConnection.jsp">Test Connection</a></td><td width="1%"><a href="source/connection/TestConnection.jsp.txt">Source</a></td></tr>
	</table>

	<table align="center" border="0" cellpadding="0">
		<tr><th colspan="2">Run Queries</th><tr>
		<tr><td><a href="queries/TestQuery.jsp">Tabular Query Example</a></td><td width="1%"><a href="source/queries/TestQuery.jsp.txt">Source</a></td></tr>
		<tr><td><a href="queries/Variables.jsp">Parameterized Example</a></td><td width="1%"><a href="source/queries/Variables.jsp.txt">Source</a></td></tr>
		<tr><td><a href="queries/RunXQuery.jsp">Submit Ad-Hoc Queries to Mark Logic</a></td><td width="1%"><a href="source/queries/RunXQuery.jsp.txt">Source</a></td></tr>
	</table>

	<table align="center" border="0" cellpadding="0">
		<tr><th colspan="2">Documents</th><tr>
		<tr><td><a href="documents/ListDocuments.jsp">Browse Documents in Mark Logic</a></td><td width="1%"><a href="source/documents/ListDocuments.jsp.txt">Source</a></td></tr>
	</table>

	<table align="center" border="0" cellpadding="0">
		<tr><th colspan="2">Tutorial Examples (localhost:8003)</th><tr>
		<tr><td colspan="2">
<p>
The tutorial examples are the exact code from the JSP tutorial on
<a href="http://developer.marklogic.com/howto/tutorials/2004-07-jsptags.xqy">developer.marklogic.com</a>
and so are hard-coded for localhost:8003.  If you have a MarkLogic Server instance running somewhere
else (say on port 8010), you can use ssh to tunnel the connection:<br>
ssh -L 8003:localhost:8010 otherhost
</p>
<p>
All these examples produce identical output (except for the page title) but execute
their queries in different ways.
		</td></tr>
		<tr><td><a href="tutorial/example1.jsp">Example 1</a></td><td width="1%"><a href="source/tutorial/example1.jsp.txt">Source</a></td></tr>
		<tr><td><a href="tutorial/example2.jsp">Example 2</a></td><td width="1%"><a href="source/tutorial/example2.jsp.txt">Source</a></td></tr>
		<tr><td><a href="tutorial/example3.jsp">Example 3</a></td><td width="1%"><a href="source/tutorial/example3.jsp.txt">Source</a></td></tr>
		<tr><td><a href="tutorial/example4.jsp">Example 4</a></td><td width="1%"><a href="source/tutorial/example4.jsp.txt">Source</a></td></tr>
	</table>

<p></p>
<p>
Check <a href="http://developer.marklogic.com">developer.marklogic.com</a> for updates.
</p>
</div>
</body>
</html>