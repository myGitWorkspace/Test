<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>

<html>
<style>
.cell {
float:left;
margin-left:10px;

}
.brk {
clear:all;
}
#total_price_label {
text-align:right;
font-weight: bold;
}
#total_price {
font-weight: bold;
}
</style>
<c:set var="errorTooManyPizzas"><spring:message code="order.error.toomanypizzas"/></c:set>
<script>
var number_pizzas=${numberPizzas};
function minus(elem_id) {
	
	var elem_full_id = 'text_'+elem_id;
	var elem = document.getElementById(elem_full_id);

	if(elem.value == "" || Number(elem.value) <= 0) {
		elem.value = 0;
		return;
	}
		
	number_pizzas--;

	elem.value = Number(elem.value) - 1;
	if(elem.value==0){
		var btn_min_id = 'minus_'+elem_id;
		var btn_min = document.getElementById(btn_min_id);
		btn_min.disabled = true;
	}
	set_total_price(elem_id, -1);
}
function plus(elem_id) {
	var elem_full_id = 'text_'+elem_id;
	var elem = document.getElementById(elem_full_id);

	if(elem.value == "")
		elem.value = 0;
	if(number_pizzas + 1 > 10){
		alert("${errorTooManyPizzas}");
		return;
	}
	if(elem.value==0){
		var btn_min_id = 'minus_'+elem_id;
		var btn_min = document.getElementById(btn_min_id);
		btn_min.disabled = false;
	}
	number_pizzas++;
	
	elem.value = Number(elem.value) + 1;
	set_total_price(elem_id, 1);
}
function set_total_price(elem_id, plus_or_minus) {
	
	var price_elem_full_id = 'price_'+elem_id;
	var price_elem = document.getElementById(price_elem_full_id);	
	var total_price_elem_full_id = 'total_price';	
	var total_price_elem = document.getElementById(total_price_elem_full_id);	
	var old_value = total_price_elem.innerHTML;	
	if(old_value == "") {
		old_value = 0;
	}	
	total_price_elem.innerHTML = (parseFloat(total_price_elem.innerHTML) + parseFloat(plus_or_minus) * parseFloat(price_elem.innerHTML)).toFixed(2);
}
</script>

<jsp:include page="../blocks/header.jsp" />

<div class="container" >
		
		<h1><spring:message code="order.title"/></h1>
		<br />
		
<spring:url value="/orders" var="orderActionUrl" />
<form action="${orderActionUrl}" name="order_form" method="POST">
<table class="table table-bordered table-hover table-striped">
<tr>
	<th><spring:message code="order.pizza.name"/></th>
	<th><spring:message code="order.pizza.type"/></th>
	<th><spring:message code="order.pizza.price"/></th>
	<th><spring:message code="order.pizza.quantity"/></th>
</tr>

<c:forEach items="${pizzas}" var="pizza" varStatus="elem">
<tr>
	<td>${pizza.name}</td>
	<td>${pizza.type}</td>
	<td><div id="price_${elem.index}">${pizza.price}</div></td>
	<td>

		<div><button type="button" class="btn btn-info cell" id="minus_${elem.index}" onclick="minus('${elem.index}')">-</button></div>				
		<form:input  path="orderDTO.pizzaIdAndQuantity['${pizza.id}']" cssClass="form-control cell" id="text_${elem.index}" cssStyle="width:46px;" maxlength="2" readonly="true"/>		
		<div><button type="button" class="btn btn-info cell" id="plus_${elem.index}" onclick="plus('${elem.index}')">+</button></div>
		<div class="brk"></div>
	</td>
</tr>
</c:forEach>
<tr>
	<td colspan="2"><div id="total_price_label"><spring:message code="order.total.price"/></div></td>
	<td colspan="2"><div id="total_price">0</div></td>
</tr>
</table>
<button class="btn btn-info" >Save Order</button>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

 
</div>

<jsp:include page="../blocks/footer.jsp" />

</html>