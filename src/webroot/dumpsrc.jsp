<%@ page contentType="text/plain" %>
<%
	String file = request.getParameter ("file");
	String reqpath = request.getRequestURI();
	String root = reqpath.substring (0, reqpath.lastIndexOf ("/") + 1);
	String path = root + file;



//	String path = request.getRequestURI() + ", " + request.getParameter ("file");
%>
File is '<%=path%>', context path=<%=config.getServletContext ().getServerInfo ()%>