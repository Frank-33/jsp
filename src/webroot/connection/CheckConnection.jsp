<!-- defunct -->
<!-- taglib uri="http://sun.com/jstl/c" prefix="c" %> -->

<c:if test="${sessionScope['com.marklogic.jsptaglib.xquery.datasource'] == null}" >
	<jsp:forward page="ConnectionForm.jsp" />
</c:if>
