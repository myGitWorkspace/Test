package ua.deliveryservice.domain;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiscountWithCardAndSetOfPizzas implements Discount {
	
	private final int PIZZA_NUMBER_FOR_DISCOUNT = 4;	
	private final Double DISCOUNT_FOR_PIZZA = 0.3;	
	private final Double CUSTOMER_CARD_DISCOUNT = 0.1;	
	private final Double CUSTOMER_CARD_DISCOUNT_LIMIT = 0.3;
	
	private User user;
	private List<Pizza> pizzas;
	
	public DiscountWithCardAndSetOfPizzas(User user, List<Pizza> pizzas) {
		this.user = user;
		this.pizzas = pizzas;
	}
	
	@Override
	public Double getDiscount() {
		
		Double discount = 0.;
		
		if(pizzas.size() > PIZZA_NUMBER_FOR_DISCOUNT) {
			Collections.sort(pizzas, new Comparator<Pizza>() {
				@Override
				public int compare(Pizza pizza1, Pizza pizza2) {
					return (int)(pizza2.getPrice() - pizza1.getPrice());
				}
			});
			Pizza expensivePizza = pizzas.get(0);
			discount += expensivePizza.getPrice()*DISCOUNT_FOR_PIZZA;
		}
		
		if(user.getCustomerCard() != null) {
			CustomerCard card = user.getCustomerCard();			
			Double cardDiscount = card.getTotalPriceOfOrders()*CUSTOMER_CARD_DISCOUNT;
			Double pizzasPrice = getOrderPriceWithoutDiscount();
			Double limit = pizzasPrice*CUSTOMER_CARD_DISCOUNT_LIMIT;
			//card.addNewOrderPrice(pizzasPrice);
			discount += (cardDiscount <= limit) ? cardDiscount : limit;			
		}		
		
		return discount;
	}
	
	@Override
	public Double getOrderPriceWithoutDiscount() {
		Double totalPrice = 0.;
		for(Pizza pizza : pizzas) {
			totalPrice += pizza.getPrice();
		}
		return totalPrice;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
}
