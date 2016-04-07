<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>

<html>

<jsp:include page="../blocks/header.jsp" />

<div class="container" >
		
		<h1><spring:message code="pizza.title"/></h1>
		<br />
		
<spring:url value="/pizzas" var="pizzaActionUrl" />

<form:form name="pizzaForm" modelAttribute="pizzaForm" cssClass="form-horizontal registrationForm" action="${pizzaActionUrl}" method="POST">

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><spring:message code="pizza.name"/></label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" />
			<form:errors path="name" cssStyle="color: red;"/>
		</div>
	</div>

	<div class="form-group">
		<label for="type" class="col-sm-2 control-label"><spring:message code="pizza.type"/></label>
		<div class="col-sm-10">					
			<c:set var="formSelectDefault"><spring:message code="form.select.default"/></c:set>
			<form:select path="type.id" class="form-control">
				<c:if test="${empty pizzaForm.type}">
					<form:option value="NONE" label="${formSelectDefault}" />
				</c:if>				
				<form:options items="${pizzaTypes}" itemValue="id" />
			</form:select>			
			<form:errors path="type" cssStyle="color: red;"/>
		</div>
	</div>

	<div class="form-group">
		<label for="price" class="col-sm-2 control-label"><spring:message code="pizza.price"/></label>
		<div class="col-sm-10">
			<form:input path="price" cssClass="form-control" />
			<form:errors path="price" cssStyle="color: red;"/>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" value="<spring:message code="button.save"/>" class="btn btn-lg btn-primary" />
		</div>
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<form:hidden path="id" />	
</form:form>

 
</div>

<jsp:include page="../blocks/footer.jsp" />

</html>