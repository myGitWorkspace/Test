<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="../blocks/header.jsp" />

<div class="container" >
		
		<h1><spring:message code="registration.title"/></h1>
		<br />	
		
		<spring:url value="/users/add" var="registerActionUrl" />
		
<form:form name="userForm" modelAttribute="userForm" cssClass="form-horizontal registrationForm" action="${registerActionUrl}" method="POST">

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><spring:message code="registration.name"/></label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" />
			<form:errors path="name" cssStyle="color: red;"/>
		</div>
	</div>

	<div class="form-group">
		<label for="login" class="col-sm-2 control-label"><spring:message code="registration.login"/></label>
		<div class="col-sm-10">
			<form:input path="login" cssClass="form-control" />
			<form:errors path="login" cssStyle="color: red;"/>
		</div>
	</div>

	<div class="form-group">
		<label for="password" class="col-sm-2 control-label"><spring:message code="registration.password"/></label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
			<form:errors path="password" cssStyle="color: red;"/>
		</div>
	</div>
	<div class="form-group">
		<label for="password2" class="col-sm-2 control-label"><spring:message code="registration.password.again"/></label>
		<div class="col-sm-10">
			<form:password path="password2" cssClass="form-control" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="roles" class="col-sm-2 control-label"><spring:message code="registration.roles"/></label>
		<div class="col-sm-10">
			<form:select path="roles" items="${allRoles}" multiple="true" size="${allRoles.size()}" cssClass="form-control" />
			<form:errors path="roles" cssStyle="color: red;"/>
		</div>
	</div>
	
	<div class="form-group">
		<label for="address.country" class="col-sm-2 control-label"><spring:message code="registration.address.country"/></label>
		<div class="col-sm-10">
			<c:set var="formSelectDefault"><spring:message code="form.select.default"/></c:set>
			<form:select path="address.country" class="form-control">
				<form:option value="NONE" label="${formSelectDefault}" />
				<form:options items="${countries}" />
			</form:select>			
			<form:errors path="address.country" cssStyle="color: red;"/>
		</div>
	</div>
	<div class="form-group">
		<label for="address.city" class="col-sm-2 control-label"><spring:message code="registration.address.city"/></label>
		<div class="col-sm-10">
			<form:input path="address.city" cssClass="form-control" />
			<form:errors path="address.city" cssStyle="color: red;"/>
		</div>
	</div>
	<div class="form-group">
		<label for="address.street" class="col-sm-2 control-label"><spring:message code="registration.address.street"/></label>
		<div class="col-sm-10">
			<form:input path="address.street" cssClass="form-control" />
			<form:errors path="address.street" cssStyle="color: red;"/>
		</div>
	</div>
	<div class="form-group">
		<label for="address.house" class="col-sm-2 control-label"><spring:message code="registration.address.house"/></label>
		<div class="col-sm-10">
			<form:input path="address.house" cssClass="form-control" />
			<form:errors path="address.house" cssStyle="color: red;"/>
		</div>
	</div>
	<div class="form-group">
		<label for="address.room" class="col-sm-2 control-label"><spring:message code="registration.address.room"/></label>
		<div class="col-sm-10">
			<form:input path="address.room" cssClass="form-control" />
			<form:errors path="address.room" cssStyle="color: red;"/>
		</div>
	</div>	
	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" value="<spring:message code="button.save"/>" class="btn btn-lg btn-primary" />
		</div>
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<form:hidden path="id" />
	<form:hidden path="address.id" />
</form:form>

</div>

<jsp:include page="../blocks/footer.jsp" />

</html>