<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<jsp:include page="../blocks/header.jsp" />

<div class="container">

	<h1><spring:message code="users.title"/></h1>
	
	<br><br>
		
	<table class="table table-bordered table-hover table-striped">
	<tr>
		<th><spring:message code="users.name"/></th>
		<th><spring:message code="users.login"/></th>
		<th><spring:message code="users.enabled"/></th>
		<th><spring:message code="users.registerDate"/></th>
		<th><spring:message code="users.lastVisitDate"/></th>		
	</tr>
	
	<c:forEach var="user" items="${users}">
		<tr>
			<td>
				<c:url var="userUrl" value="/users/${user.id}/update"/>
				<a href="${userUrl}">
					<c:out value="${user.name}"/>
				</a>
			</td>
			<td><c:out value="${user.login}"/></td>
			<td><c:out value="${user.enabled}"/></td>
			<td><fmt:formatDate type="date" dateStyle="long" value="${user.registerDate}" /></td>
			<td><fmt:formatDate type="date" dateStyle="long" value="${user.lastVisitDate}" /></td>
		</tr>
	</c:forEach>	
	
	</table>	

</div>

<jsp:include page="../blocks/footer.jsp" />

</body>
</html>