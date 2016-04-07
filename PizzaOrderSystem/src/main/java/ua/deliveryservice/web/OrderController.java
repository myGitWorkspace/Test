package ua.deliveryservice.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.OrderDTO;
import ua.deliveryservice.domain.OrderPizza;
import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.domain.User;
import ua.deliveryservice.service.OrderService;
import ua.deliveryservice.service.PizzaService;
import ua.deliveryservice.service.UserService;

@Controller
public class OrderController {
	
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/orders"}, method=RequestMethod.GET)
	public String list(Model model) {
		List<Order> orders = new ArrayList<>();
		if( SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")) ) {
			orders = orderService.findAll();
		} else {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			orders = orderService.findByUser(user);
		}
		model.addAttribute("orders", orders);
		model.addAttribute("orderStateForm", new Order());		
		return "order/orders";
	}
	
	@RequestMapping(value={"/orders"}, method=RequestMethod.POST)
	public String save(@ModelAttribute("orderDTO") OrderDTO orderDTO, Model model, WebRequest webRequest) {
		
		Map<Integer,Integer> pizzaIdMap = orderDTO.getPizzaIdAndQuantity();
		List<Integer> pizzasIdList = new ArrayList<>();		
		for(Integer id : pizzaIdMap.keySet()) {
			for(int i=0; i<pizzaIdMap.get(id); i++)
				pizzasIdList.add(id);
		}
		
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//logger.debug("USER id to save order aaaa={}",user.getId());
		orderService.placeNewOrder(user, pizzasIdList.toArray(new Integer[0]));
		
		return "redirect:/orders";
	}
	
	
	@RequestMapping(value={"/orders/add"}, method=RequestMethod.GET)
	public String add(Model model) {
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		OrderDTO orderDTO = new OrderDTO();
		Map<Integer,Integer> mapPizzaId = orderDTO.getPizzaIdAndQuantity();
		for(Pizza pizza : pizzas) {
			mapPizzaId.put(pizza.getId(), 0);
		}			
		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("numberPizzas", 0);
		return "order/order";
	}
	
	@RequestMapping(value={"/orders/{id}/update"}, method=RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		OrderDTO orderDTO = new OrderDTO();
		Map<Integer,Integer> mapPizzaId = orderDTO.getPizzaIdAndQuantity();
		for(Pizza pizza : pizzas) {
			mapPizzaId.put(pizza.getId(), 0);
		}
		Order order = orderService.find(id);
		for(OrderPizza orderPizza : order.getOrderPizzas()) {
			mapPizzaId.put(orderPizza.getPizza().getId(), orderPizza.getItems());
		}
		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("numberPizzas", order.getOrderPizzas().size());

		return "order/order";
	}
	
	@RequestMapping(value={"/orders/status/update"}, method=RequestMethod.POST)
	public String updateStatus(@ModelAttribute("orderStateForm") Order orderStateForm) {
		orderService.updateState(orderStateForm.getId(), orderStateForm.getState());
		if(orderStateForm.getState() == Order.State.DONE){
			orderService.updateReleaseDate(orderStateForm.getId(), new Date());
		}
		return "redirect:/orders";
	}
	
}
