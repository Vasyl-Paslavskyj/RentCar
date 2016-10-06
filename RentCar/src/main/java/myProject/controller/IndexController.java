package myProject.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vasj on 18.08.2016.
 */
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String showIndex(Principal principal){
		System.out.println(principal);
		System.out.println(System.getProperty("catalina.home"));
		return "index";
	}
	
	@RequestMapping("/admin")
	public String showAdmin() {
		return "adminPanel";
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/user")
	public String showUser(){
		return "userPanel";
	}
}
