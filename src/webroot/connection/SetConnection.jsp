<%@ taglib uri="http://marklogic.com/jsp/taglib" prefix="xq" %>
<%@ taglib uri="http://sun.com/jstl/c" prefix="c" %>

<xq:unSetDataSource/>

<c:set scope="page" var="redirdestination" value="/" />
<jsp:forward page="ConnectionForm.jsp" />
