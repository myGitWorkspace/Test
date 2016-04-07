package ua.deliveryservice.web;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 
	 * Show home page 
	 * 
	 */
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String defaultPage() {
		//logger.debug("User entered home page");
		return "redirect:/orders";
	}
	
	
	/**
	 * 
	 * Show login page 
	 * 
	 */	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout, Model model, Locale locale) {
		
		if (error != null) {
			model.addAttribute("error", messageSource.getMessage("login.error", null, locale));
			model.addAttribute("css", "danger");
		}

		if (logout != null) {
			model.addAttribute("msg", messageSource.getMessage("login.logout.message", null, locale));
			model.addAttribute("css", "success");
		}		

		return "login";
	}
	
	/**
	 * 
	 * Show access denied page 
	 * 
	 */
	@RequestMapping(value = { "/denied" }, method = RequestMethod.GET)
	public String accessDenied() {
		//logger.debug("User entered home page");
		return "denied";
	}
	

}