<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>

<xq:unSetDataSource/>

<jsp:forward page="ConnectionForm.jsp">
	<jsp:param name="redirdestination" value="/"/>
</jsp:forward>
