package ua.deliveryservice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.deliveryservice.domain.PizzaType;
import ua.deliveryservice.service.PizzaTypeService;

@Controller
public class PizzaTypeController {

	@Autowired
	private PizzaTypeService pizzaTypeService;
	
	@RequestMapping(value="/pizzatypes", method=RequestMethod.GET)
	public String list(Model model) {
		List<PizzaType> pizzaTypes = pizzaTypeService.findAll();
		model.addAttribute("pizzaTypes", pizzaTypes);
		return "/pizzatype/pizzatypes";
	}
	
	@RequestMapping(value="/pizzatypes", method=RequestMethod.POST)
	public String save(@ModelAttribute("pizzaTypeForm") PizzaType pizzaTypeForm) {
		pizzaTypeService.save(pizzaTypeForm);
		return "redirect:/pizzatypes";
	}
	
	@RequestMapping(value="/pizzatypes/{id}/update", method=RequestMethod.GET)
	public String update(@PathVariable("id") Integer id, Model model) {
		PizzaType pizzaType = pizzaTypeService.find(id);
		model.addAttribute("pizzaTypeForm", pizzaType);
		return "/pizzatype/pizzatype";
	}
	
	@RequestMapping(value="/pizzatypes/add", method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("pizzaTypeForm", new PizzaType());
		return "/pizzatype/pizzatype";
	}
	
	@RequestMapping(value="/pizzatypes/{id}/delete", method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		pizzaTypeService.delete(id);
		return "redirect:/pizzatypes";
	}
	
}
