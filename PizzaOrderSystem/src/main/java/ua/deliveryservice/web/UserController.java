package ua.deliveryservice.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ua.deliveryservice.domain.Country;
import ua.deliveryservice.domain.Role;
import ua.deliveryservice.domain.User;
import ua.deliveryservice.service.CountryService;
import ua.deliveryservice.service.RoleService;
import ua.deliveryservice.service.UserService;
import ua.deliveryservice.validator.UserFormValidator;

@Controller
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private CountryService countryService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;	
	@Autowired
	UserFormValidator userFormValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userFormValidator);
	}
	
	/**
	 * 
	 * Show all users
	 * 
	 */
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public String showAllUsers(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		//logger.debug("User entered home page");
		return "user/users";
	}
	
	/**
	 * 
	 * Create new User
	 * 
	 */
	@RequestMapping(value = { "/users/add" }, method = RequestMethod.GET)
	public String createNewUser(Model model) {
		//logger.debug("User entered home page");
		model.addAttribute("userForm", new User());
		populateUserFormModel(model);

		return "user/user";
	}
	
	/**
	 * 
	 * Save User
	 * 
	 */
	@RequestMapping(value = { "/users/add" }, method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("userForm") @Validated User user, BindingResult result, Model model) {
		//logger.debug("User entered home page");
		
		if (result.hasErrors()) {			
			logger.debug("User entered not correct data during redistration. Will get one more try");			
			populateUserFormModel(model);
			return "user/user";
		}
		
		List<Role> roles = new ArrayList<>();
		for(Role role : user.getRoles()) {
			roles.add(roleService.findByRoleName(role.getName()));
		}
		user.setRoles(roles);
		if(user.getId() == null) {
			user.setRegisterDate(new Date());
		}
		if(!user.getPassword().equals("")) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
		} else if(user.getId() != null) {
			user.setPassword(userService.findPassword(user.getId()));
		}
		userService.save(user);
		return "redirect:/users";
	}
	
	/**
	 * 
	 * Update User
	 * 
	 */
	@RequestMapping(value = { "/users/{id}/update" }, method = RequestMethod.GET)
	public String updateUser(@PathVariable Integer id, Model model) {
		//logger.debug("User entered home page");
		User user = userService.find(id);
		model.addAttribute("userForm", user);
		populateUserFormModel(model);
		
		return "user/user";
	}
	
	private void populateUserFormModel(Model model) {
		
		Map<String, String> countries = new HashMap<>();
		List<Country> countryList = countryService.findAll();
		for(Country country : countryList) {
			countries.put(country.getName(), country.getName());			
		}
		model.addAttribute("countries", countries);
		
		Map<String, String> roles = new HashMap<>();
		List<Role> roleList = roleService.findAll();
		for(Role role : roleList) {
			roles.put(role.getName(), role.getName());			
		}
		model.addAttribute("allRoles", roleList);
	}
}
