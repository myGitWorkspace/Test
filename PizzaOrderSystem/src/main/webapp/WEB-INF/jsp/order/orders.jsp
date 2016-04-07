<%@ page session="false"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>

<script>
function change_order_state(order_id) {
	
	var select_elem_id = 'select_state_' + order_id;
	var select_elem = document.getElementById(select_elem_id);
	var select_elem_value = select_elem.options[select_elem.selectedIndex].value;
	var form = document.getElementById("orderStateForm");	
	form.state.value = select_elem_value;
	form.id.value = order_id;	
	form.submit();
}
</script>

<jsp:include page="../blocks/header.jsp" />

<div class="container" >

		<h1><spring:message code="orders.title"/></h1>
		<br />
		
	<sec:authorize access="hasRole('ROLE_USER')">
	
	<form name='testForm' action="<c:url value='/orders/add' />" method='GET'>
		<button class="btn-lg btn-primary pull-right" ><spring:message code='button.createorder'/></button>
	</form>
	
	</sec:authorize>
		

<table class="table table-bordered table-hover table-striped">
<tr>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<th><spring:message code="orders.customer.name"/></th>
	</sec:authorize>
	<th><spring:message code="orders.pizzas.list"/></th>
	<th><spring:message code="orders.price"/></th>
	<th><spring:message code="orders.discount"/></th>
	<th><spring:message code="orders.register.date"/></th>
	<th><spring:message code="orders.release.date"/></th>
	<th><spring:message code="orders.state"/></th>
</tr>

<c:forEach items="${orders}" var="order">

<c:set var="orderStates">
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<c:if test="${order.state == 'NEW'}" >
			NEW,INPROGRESS,CANCELED
	</c:if>
	<c:if test="${order.state == 'INPROGRESS'}" >
			INPROGRESS,CANCELED,DONE
	</c:if>
	<c:if test="${ (order.state == 'CANCELED') || (order.state == 'DONE') }" >
		${order.state}
	</c:if>	
</sec:authorize>

<sec:authorize access="hasRole('ROLE_USER')">
	<c:if test="${order.state == 'NEW'}" >		
			NEW,CANCELED		
	</c:if>
	<c:if test="${ (order.state == 'INPROGRESS') || (order.state == 'CANCELED') || (order.state == 'DONE') }" >
		${order.state}
	</c:if>
</sec:authorize>
</c:set>


<tr>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<c:url var="userUrl" value="/users/${order.user.id}/update"/>
	<td><a href="${userUrl}">${order.user.name}</a></td>
	</sec:authorize>
	
	<td>
		<c:forEach items="${order.orderPizzas}" var="orderPizza" varStatus="status">
			<c:url var="pizzaUrl" value="/pizzas/${orderPizza.pizza.id}/update" />
			<a href='${pizzaUrl}'>
				<c:out value="${orderPizza.pizza.name}" />
			</a>
			<c:out value="(${orderPizza.items})${!status.last ? ',' : ''} " />
		</c:forEach>
	</td>

	<td>${order.price}</td>
	<td>${order.discount}</td>
	<td><fmt:formatDate type="date" dateStyle="long" value="${order.creationDate}" /></td>
	<td><fmt:formatDate type="date" dateStyle="long" value="${order.releaseDate}" /></td>
	<td>
		<select class="form-control" id="select_state_${order.id}" onchange="change_order_state(${order.id})">
			<c:forEach items="${orderStates}" var="state">
					<option <c:if test="${order.state == state}">selected</c:if> >${state}</option>
			</c:forEach>			 
		</select>
		
	</td>
</tr>

</c:forEach>

</table>

<spring:url value="/orders/status/update" var="orderStatusActionUrl" />
<form:form modelAttribute="orderStateForm" action="${orderStatusActionUrl}" method="POST">
		<form:hidden path="state" />
		<form:hidden path="id" />
</form:form>

</div>

<jsp:include page="../blocks/footer.jsp" />

</html>

