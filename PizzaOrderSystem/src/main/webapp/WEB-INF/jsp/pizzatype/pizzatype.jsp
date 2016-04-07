<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>

<html>

<jsp:include page="../blocks/header.jsp" />

<div class="container" >
		
		<h1><spring:message code="pizzatype.title"/></h1>
		<br />
		
<spring:url value="/pizzatypes" var="pizzatypeActionUrl" />

<form:form name="pizzaTypeForm" modelAttribute="pizzaTypeForm" cssClass="form-horizontal registrationForm" action="${pizzatypeActionUrl}" method="POST">

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><spring:message code="pizzatype.name"/></label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" />
			<form:errors path="name" cssStyle="color: red;"/>
		</div>
	</div>

	<div class="form-group">
		<label for="description" class="col-sm-2 control-label"><spring:message code="pizzatype.description"/></label>
		<div class="col-sm-10">
			<form:input path="description" cssClass="form-control" />
			<form:errors path="description" cssStyle="color: red;"/>
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