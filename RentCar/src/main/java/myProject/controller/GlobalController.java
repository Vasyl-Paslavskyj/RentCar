package myProject.controller;

import myProject.models.User;
import myProject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("authUser")
	public User getUser(){
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!"anonymousUser".equals(id)){
			return userService.findById(Long.valueOf(id));
		}
		return null;
	}
}
