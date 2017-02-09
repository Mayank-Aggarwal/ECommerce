package com.example.controller;

//import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.service.UserService;

@Controller
public class HelloWorldController {

	
	@Autowired
	UserService userService;
	
	/*@Autowired
	User user;*/
	
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "welcome";
	}
	
	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/Registration", method = RequestMethod.GET)
	public String registrationPage(Model model) {
		
		model.addAttribute("adduser", new User());
		return "Registration";
	}

	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("adduser") User user){
		
		userService.addUser(user);
		return "redirect:/login";
		
	}
	
	@RequestMapping(value="/product", method = RequestMethod.GET)
	public String viewProduct(ModelMap model)
	{
		model.addAttribute("user", getPrincipal());
		return "product";
		
	}
	
	@RequestMapping(value="/users", method = RequestMethod.GET)
	public String viewUsers(ModelMap model)
	{
		model.addAttribute("user", getPrincipal());
		return "users";
		
	}
	
	@RequestMapping(value="/supplier", method = RequestMethod.GET)
	public String viewSupplier(ModelMap model)
	{
		model.addAttribute("user", getPrincipal());
		return "supplier";
		
	}
	
	/*@RequestMapping(value = "/registrationSuccess", method = RequestMethod.GET)

	public String registrationSuccess(Model model,Principal principal) {
		
		String name=principal.getName();
		model.addAttribute("userName", name);
		return "RegistrationSuccess";
	}*/
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	
	
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}