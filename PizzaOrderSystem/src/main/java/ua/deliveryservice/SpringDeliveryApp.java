package ua.deliveryservice;

import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.deliveryservice.domain.User;
import ua.deliveryservice.domain.Order;
import ua.deliveryservice.infrastructure.orderevent.OrderCreatedEvent;
import ua.deliveryservice.infrastructure.orderevent.OrderEventManager;
import ua.deliveryservice.repository.PizzaRepository;
import ua.deliveryservice.service.OrderService;

public class SpringDeliveryApp {

	public static void main(String[] args) {		
		
		ConfigurableApplicationContext repositoryContext = 
				new ClassPathXmlApplicationContext(
						new String[] {
								"repositoryContext.xml"
								});
		
		ConfigurableApplicationContext appContext = 
				new ClassPathXmlApplicationContext(
						new String[] {
								"appContext.xml",
								}, repositoryContext);
		
		
		/*
		PizzaRepository pizzaRepository = (PizzaRepository)appContext.getBean(PizzaRepository.class);
		pizzaRepository.find(1);
		*/
		
		
		appContext.addApplicationListener(new ApplicationListener<OrderCreatedEvent>() {
			@Override
			public void onApplicationEvent(OrderCreatedEvent event) {
				System.out.println("New order was created with customer = " + event.getOrder().getUser().getName());
			}
		});
        OrderService orderService = (OrderService)appContext.getBean(OrderService.class);
        User customer = (User)appContext.getBean(User.class);
        customer.setName("NameCustomer");        
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        
        
        //OrderEventManager manager = (OrderEventManager)appContext.getBean(OrderEventManager.class);
        //manager.publishOrderEvent(order);  
		
		
		/*
		for(String s : appContext.getBeanDefinitionNames()){
			System.out.println("bean=" + s);
		}
		*/

		
		/*
		Pizza pizza = (Pizza)appContext.getBean("pizzaFactoryBean");
		System.out.println(pizza);
		
        Customer customer1 = (Customer)appContext.getBean("newCustomer");
        System.out.println("customer = " + customer1);
        */
        
        
		/*
        OrderService orderService = (OrderService)appContext.getBean(OrderService.class);
        Customer customer = (Customer)appContext.getBean(Customer.class);
        Order order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);
        
        Order o1 = orderService.createNewOrder();
        Order o2 = orderService.createNewOrder();
        System.out.println("order1 = " + o1.hashCode());
        System.out.println("order2 = " + o2.hashCode());
        */
        //Customer customer1 = (Customer)appContext.getBean("newCustomer");
        //System.out.println("customer = " + customer1.getAddress());
		
		
        repositoryContext.close();
		appContext.close();
	}
	
}
