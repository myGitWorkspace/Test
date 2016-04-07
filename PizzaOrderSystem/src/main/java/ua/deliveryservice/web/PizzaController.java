package ua.deliveryservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.domain.PizzaType;
import ua.deliveryservice.service.PizzaService;
import ua.deliveryservice.service.PizzaTypeService;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private PizzaTypeService pizzaTypeService;
	
	@RequestMapping(value="/pizzas", method=RequestMethod.GET)
	public String pizzas(Model model) {
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		return "pizza/pizzas";
	}
	
	@RequestMapping(value="/pizzas", method=RequestMethod.POST)
	public String save(@ModelAttribute("pizzaForm") Pizza pizzaForm) {
		pizzaForm.setType( pizzaTypeService.find(Integer.valueOf(pizzaForm.getType().getId())) );		
		pizzaService.save(pizzaForm);
		return "redirect:/pizzas";
	}
	
	@RequestMapping(value="pizzas/{id}/update", method=RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = pizzaService.find(id);
		model.addAttribute("pizzaForm", pizza);
		List<PizzaType> pizzaTypes = pizzaTypeService.findAll();
		model.addAttribute("pizzaTypes", pizzaTypes);
		return "pizza/pizza";
	}
	
	@RequestMapping(value="pizzas/add", method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("pizzaForm", new Pizza());
		List<PizzaType> pizzaTypes = pizzaTypeService.findAll();
		model.addAttribute("pizzaTypes", pizzaTypes);
		return "pizza/pizza";
	}
	
	@RequestMapping(value="pizzas/{id}/delete", method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		pizzaService.delete(id);
		return "redirect:/pizzas";
	}
}
