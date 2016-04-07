package ua.deliveryservice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.deliveryservice.domain.Address;
import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.domain.User;
import ua.deliveryservice.repository.OrderRepository;
import ua.deliveryservice.repository.PizzaRepository;
import ua.deliveryservice.repository.UserRepository;
import ua.deliveryservice.service.UserService;

public class SpringJPADeliveryApp {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringJPADeliveryApp.class);

    public static void main(String[] args) {    	
    	
        ConfigurableApplicationContext repositoryContext
                = new ClassPathXmlApplicationContext(
                        "repositoryContext.xml", "repositoryInMemDBContext.xml"
                );
        repositoryContext.getEnvironment().setActiveProfiles("prod");
        repositoryContext.refresh();

        ConfigurableApplicationContext appContext
                = new ClassPathXmlApplicationContext(
                        new String[]{"appContext.xml"},
                        repositoryContext);

        /*
        Pizza pizza = new Pizza("Name", Pizza.PizzaType.CHEESE, 1.);
        PizzaRepository pr = repositoryContext.getBean("JPAPizzaRepository", PizzaRepository.class);        
        pizza = pr.save(pizza);
        pr.find(pizza.getId());
        pr.findAll();
        pr.delete(pizza);
        //System.out.println(pizza);        
        */
        
        User user = new User();
        user.setName("UserName");
        Address addr = new Address();
        addr.setCity("City");
        addr.setStreet("Street");
        addr.setHouse("House");
        user.setAddress(addr);
        
 
        
        UserService us = appContext.getBean(UserService.class);
        us.save(user);
        
        /*
        UserRepository ur = repositoryContext.getBean(UserRepository.class);        
        user = ur.save(user);
        //ur.find(user.getId());        
        //ur.delete(user);
         */
        /*
        System.out.println(user);
        pr.delete(user);
        */
        /*
        Pizza pizza = new Pizza("Name", Pizza.PizzaType.CHEESE, 1.);
        PizzaRepository pr = repositoryContext.getBean("JPAPizzaRepository", PizzaRepository.class);        
        pizza = pr.save(pizza);
        List<Pizza> list = new ArrayList<>();
        list.add(pizza);
        Order order = new Order(user, list);
        OrderRepository or = repositoryContext.getBean("JPAOrderRepository", OrderRepository.class);        
        order = or.save(order);
        User usr = ur.find(5);
        order.setUser(usr);
        or.save(order);
        logger.debug("aaaaa="+order);
        logger.debug("bbbb="+or.find(order.getId()));
        logger.debug("cccc="+or.findAll());
        or.delete(order);
        //System.out.println("aaaaa="+order);
        //System.out.println(or.find(order.getId()));
        //System.out.println(or.findAll());
        //System.out.println(or.contains(order));
        //or.delete(order);
        */
        
        /*
        OrderPizzaRepository opr = repositoryContext.getBean(OrderPizzaRepository.class);        
        System.out.println(opr.find());
        */
        
        repositoryContext.close();
        appContext.close();

    } 
}
