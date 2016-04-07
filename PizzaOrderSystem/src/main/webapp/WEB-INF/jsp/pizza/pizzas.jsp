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

		<h1><spring:message code="pizzas.title"/></h1>
		<br />
		
	<form action="<c:url value='/pizzas/add' />" method='GET'>
		<button class="btn-lg btn-primary pull-right" ><spring:message code='pizzas.button.createpizza'/></button>
	</form>
		

<table class="table table-bordered table-hover table-striped">
<tr>
	<th><spring:message code="pizza.name"/></th>
	<th><spring:message code="pizza.type"/></th>
	<th><spring:message code="pizza.price"/></th>
	<th></th>
</tr>

<c:forEach items="${pizzas}" var="pizza">

<tr>
	<td>
		<spring:url value="/pizzas/${pizza.id}/update" var="pizzaUpdateActionUrl" />
		<a href="${pizzaUpdateActionUrl}">
			${pizza.name}
		</a>
	</td>
	<td>${pizza.type}</td>
	<td>${pizza.price}</td>
	<td>
		<spring:url value="/pizzas/${pizza.id}/delete" var="pizzaDeleteActionUrl" />
		<form:form action="${pizzaDeleteActionUrl}" method="POST">
			<button class="btn btn-info cell"><spring:message code="pizzas.button.delete"/></button>
		</form:form>
	</td>
</tr>

</c:forEach>

</table>


</div>

<jsp:include page="../blocks/footer.jsp" />

</html>

