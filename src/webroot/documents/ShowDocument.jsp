<%@include file="/common/CheckConnectionHeader.jsp" %>
<%
	response.setContentType ("text/" + request.getParameter ("type"));
	response.resetBuffer();
%><xq:execute>
	<xq:query>
		doc("<%=request.getParameter("doc")%>")
	</xq:query>
</xq:execute>
