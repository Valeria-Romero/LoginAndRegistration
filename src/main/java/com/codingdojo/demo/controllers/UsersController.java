package com.codingdojo.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.demo.models.User;
import com.codingdojo.demo.services.UsersService;


@Controller
public class UsersController {
	
	private final UsersService usersService;
	
	public UsersController( UsersService usersService ) {
		this.usersService = usersService;
	}
	
	@RequestMapping( value = "/login", method = RequestMethod.GET )
	public String index() {
		return "index.jsp";
	}
	
	
	@RequestMapping( value = "/registerUser", method = RequestMethod.POST )
	public String registerUser( @RequestParam(value="email") String email,
								@RequestParam(value="firstname") String firstname,
								@RequestParam(value="lastname") String lastname,
								@RequestParam(value="password") String password,
								@RequestParam(value="passwordConfirmation") String passwordConfirmation,
								RedirectAttributes redirectAttributes,
								HttpSession session) {
		
		List<User> user = usersService.getUserByEmail(email);
		
		if( user.size() > 0 ) {
			redirectAttributes.addFlashAttribute("errorMessage", "That user email already exists!");
			return "redirect:/login";
		}
		else {
			if( ! password.equals( passwordConfirmation ) ) {
				redirectAttributes.addFlashAttribute("errorMessage", "Password and password confirmation do not match");
				return "redirect:/login";
			}
			else {
				usersService.registerUser(email, firstname, lastname, password );
				session.setAttribute( "firstName", firstname );
				session.setAttribute( "lastName", lastname );
				return "redirect:/home";
			}
		}
	}
	
	@RequestMapping( value = "/validateUser", method = RequestMethod.POST )
	public String login( @RequestParam(value="userEmail") String email,
						 @RequestParam(value="userPassword") String password,
						 RedirectAttributes redirectAttributes,
						 HttpSession session) {
		
		List<User> user = usersService.getUserByEmail(email);
		if( user.size() == 0 ) {
			redirectAttributes.addFlashAttribute("loginErrorMessage", "Wrong credentials");
			return "redirect:/login";
		}
		else {
			User currentUser = user.get(0);
			
			if( usersService.validateUser(currentUser, password) ) {
				session.setAttribute( "firstName", currentUser.getFirstname() );
				session.setAttribute( "lastName", currentUser.getLastname() );
				return "redirect:/home";
			}
			else {
				redirectAttributes.addFlashAttribute("loginErrorMessage", "Wrong password");
				return "redirect:/login";
			}
		}
	}
	
	@RequestMapping( value = "/home", method = RequestMethod.GET )
	public String home( HttpSession session, Model model ) {
		String firstName = (String) session.getAttribute("firstName");
		String lastName = (String) session.getAttribute("lastName");
		
		if( firstName != null && lastName != null ) {
			model.addAttribute("firstName", firstName );
			model.addAttribute("lastName", lastName );
			return "home.jsp";
		}
		else {
			return "redirect:/login";
		}
		
	}
	
	@RequestMapping( value = "/logout", method = RequestMethod.GET )
	public String logout( HttpSession session ) {
		session.removeAttribute("firstName");
		session.removeAttribute("lastName");
		return "redirect:/login";
	}
}