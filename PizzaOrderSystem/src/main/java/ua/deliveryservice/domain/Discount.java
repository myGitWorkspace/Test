package ua.deliveryservice.domain;

public interface Discount {
	
	Double getDiscount();
	
	Double getOrderPriceWithoutDiscount();
}
