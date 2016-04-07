package ua.deliveryservice;

import java.util.ArrayList;
import java.util.List;

import ua.deliveryservice.domain.User;
import ua.deliveryservice.domain.CustomerCard;
import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.Address;
import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.infrastructure.ApplicationContext;
import ua.deliveryservice.infrastructure.JavaConfig;
import ua.deliveryservice.infrastructure.JavaConfigApplicationContext;
import ua.deliveryservice.repository.PizzaRepository;
import ua.deliveryservice.service.OrderService;

public class PizzaApp {

	public static void main(String[] args) throws Throwable {
		
		ApplicationContext context = new JavaConfigApplicationContext(new JavaConfig());
        
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        System.out.println(pizzaRepository.find(1));
        System.out.println(pizzaRepository.find(2));
        
        OrderService orderService = (OrderService) context.getBean("orderService");
        /*
        Customer customer = new Customer(1, "customer1", new Address("city1","street1","house1"));
        CustomerCard card = new CustomerCard();
        card.addNewOrderPrice(40.);
        customer.setCustomerCard(card);
        Order order = orderService.placeNewOrder(customer, 1, 2, 3, 4, 5);
        order.process();
        order.process();
        System.out.println("total price="+order.getFinalPrice());        
        */
        
		User c = new User(1,"",null);
		CustomerCard card = new CustomerCard();
		card.addNewOrderPrice(10.);
		c.setCustomerCard(card);
		List<Pizza> array = new ArrayList<>();
		array.add(new Pizza("", null, 3.));
		array.add(new Pizza("", null, 4.));
		array.add(new Pizza("", null, 5.));
		array.add(new Pizza("", null, 6.));
		array.add(new Pizza("", null, 7.));
		Order order = new Order(c, array);
		System.out.println("Price="+order.getPrice());
		//order.process();
		//order.process();

	}
}



