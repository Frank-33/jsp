<%@include file="/common/CheckConnectionHeader.jsp" %>
<%response.setContentType ("text/" + request.getParameter ("type"));%>
<xq:execute><xq:query>doc("<%=request.getParameter("doc")%>")</xq:query></xq:execute>
