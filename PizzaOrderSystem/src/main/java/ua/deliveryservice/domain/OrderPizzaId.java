package ua.deliveryservice.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderPizzaId implements Serializable {
	//(cascade=CascadeType.PERSIST)
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pizza pizza;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	@Override
	public String toString() {
		return "OrderPizzaId [order=" + order.getId() + ", pizza=" + pizza.getId() + "]";
	}

	
}
