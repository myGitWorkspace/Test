<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

<jsp:include page="../blocks/header.jsp" />

<div class="container" >

		<h1><spring:message code="pizzatypes.title"/></h1>
		<br />
		
	<form action="<c:url value='/pizzatypes/add' />" method='GET'>
		<button class="btn-lg btn-primary pull-right" ><spring:message code='pizzatypes.button.createpizzatype'/></button>
	</form>
		

<table class="table table-bordered table-hover table-striped">
<tr>
	<th><spring:message code="pizzatype.name"/></th>
	<th><spring:message code="pizzatype.description"/></th>	
	<th></th>
</tr>

<c:forEach items="${pizzaTypes}" var="pizzatype">

<tr>
	<td>
		<spring:url value="/pizzatypes/${pizzatype.id}/update" var="pizzatypeUpdateActionUrl" />
		<a href="${pizzatypeUpdateActionUrl}">
			${pizzatype.name}
		</a>
	</td>
	<td>${pizzatype.description}</td>
	<td>
		<spring:url value="/pizzatypes/${pizzatype.id}/delete" var="pizzatypeDeleteActionUrl" />
		<form:form action="${pizzatypeDeleteActionUrl}" method="POST">
			<button class="btn btn-info cell"><spring:message code="pizzatypes.button.delete"/></button>
		</form:form>
	</td>
</tr>

</c:forEach>

</table>


</div>

<jsp:include page="../blocks/footer.jsp" />

</html>

