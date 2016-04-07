package ua.deliveryservice.infrastructure.orderevent;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import ua.deliveryservice.domain.Order;


public class OrderEventManager implements ApplicationEventPublisherAware {
	
	private ApplicationEventPublisher publisher;
	
	@Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;        
    }
	
	public void publishOrderEvent(Order order) {		
		publisher.publishEvent(new OrderCreatedEvent(this, order));
	}
}
