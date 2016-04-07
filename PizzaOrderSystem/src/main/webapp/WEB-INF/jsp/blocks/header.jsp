<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online pizza order service</title>

<spring:url value="/resources/css/hello.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</head>

<body>

<nav class="navbar navbar-inverse ">
	<div class="container">
		<ul class="nav navbar-nav navbar-left">

			<sec:authorize access="isAnonymous()">
        	<li class="active"><a href="<spring:url value="/login" />"><spring:message code="menu.login"/></a></li>
        	</sec:authorize>
			<sec:authorize access="isAnonymous()">
        	<li class="active"><a href="<spring:url value="/users/add" />"><spring:message code="menu.register"/></a></li>
        	</sec:authorize>
  	
       		<sec:authorize access="isAuthenticated()">
        	<li class="active"><a href="<spring:url value="/orders" />"><spring:message code="menu.orders"/></a></li>
        	</sec:authorize>			
        	<sec:authorize access="hasRole('ROLE_ADMIN')">
        	<li class="active"><a href="<spring:url value="/pizzas" />"><spring:message code="menu.pizzas"/></a></li>
        	</sec:authorize>
        	<sec:authorize access="hasRole('ROLE_ADMIN')">
        	<li class="active"><a href="<spring:url value="/pizzatypes" />"><spring:message code="menu.pizza.type"/></a></li>
        	</sec:authorize>
        	<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="active"><a href="<spring:url value="/users" />"><spring:message code="menu.users"/></a></li>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
			<c:set var="userId" >
				<sec:authentication property="principal.id" />
			</c:set>		
			<li class="active"><a href='<spring:url value="/users/${userId}/update" />'><spring:message code="menu.user.details"/></a></li>
			</sec:authorize>			
		</ul>

		<div class="navbar-header" style="padding-left:200px;">
			<a class="navbar-brand" href="#" onclick="document.languageForm.locale.value='en';document.languageForm.submit();"><spring:message code="language.english"/></a>			 
			<a class="navbar-brand" href="#"  onclick="document.languageForm.locale.value='ru';document.languageForm.submit();"><spring:message code="language.russian"/></a>			
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active">

					<c:if test="${pageContext.request.userPrincipal.name != null}">
					
					<c:url value="/logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					<a href="javascript:formSubmit()"><spring:message code="logout.button"/></a>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
</nav>

<form name="languageForm" method='GET'>
<input type="hidden" name="locale" value=""/>				
</form>