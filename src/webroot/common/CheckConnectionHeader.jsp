<%@ page language="java" import="com.marklogic.jsptaglib.xquery.rt.SetDataSourceTag" %>
<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>
<%@ taglib uri="http://sun.com/jstl/x" prefix="x" %>
<% pageContext.setAttribute ("DataSourceAttrName", SetDataSourceTag.ML_DEFAULT_DATASOURCE_VAR); %>
<c:if test="${sessionScope[DataSourceAttrName] == null}" >
	<jsp:forward page="/connection/ConnectionForm.jsp" >
		<jsp:param name="redirdestination" value="<%=request.getRequestURI()%>"/>
	</jsp:forward>
</c:if>