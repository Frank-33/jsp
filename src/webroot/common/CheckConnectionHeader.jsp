<%@ page language="java" %>
<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>
<%@ taglib uri="http://sun.com/jstl/x" prefix="x" %>

<c:if test="${sessionScope['com.marklogic.jsptaglib.xquery.datasource'] == null}" >
	<jsp:forward page="/connection/ConnectionForm.jsp" >
		<jsp:param name="redirdestination" value="<%=request.getRequestURI()%>"/>
	</jsp:forward>
</c:if>