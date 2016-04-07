package ua.deliveryservice.infrastructure.orderevent;

import org.springframework.context.ApplicationEvent;

import ua.deliveryservice.domain.Order;

public class OrderCreatedEvent extends ApplicationEvent {

	private Order order;
	
	public OrderCreatedEvent(Object source, Order order) {
		super(source);
		this.order = order;
	}
	
	public Order getOrder() {
		return this.order;
	}
}
