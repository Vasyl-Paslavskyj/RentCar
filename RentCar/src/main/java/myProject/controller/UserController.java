package myProject.controller;

import javax.validation.Valid;

import myProject.form.UserForm;
import myProject.form.filter.UserFilterForm;
import myProject.models.User;
import myProject.service.UserService;
import myProject.service.Implementation.validator.UserFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Vasj on 19.08.2016.
 */
@Controller
public class UserController {
    @Autowired
    UserService serviceUser;
    
    @InitBinder("user")
    protected void initBinder(WebDataBinder binder){
    	binder.setValidator(new UserFormValidator(serviceUser));
    }
    
    @ModelAttribute("user")
    public UserForm getUserForm(){
    	return new UserForm();
    }
    
    @ModelAttribute("filter")
    public UserFilterForm getFilter(){
    	return new UserFilterForm();
    }

    @RequestMapping("/registration")
	public String register(){
		return "registration";
    }
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") @Valid UserForm user, 
    		BindingResult br){
    	if(br.hasErrors()){
            return "registration";
    	}
    	serviceUser.save(user);
    	return "redirect:/login";
    }
    @RequestMapping(value="/verification/{pathVerification}", method=RequestMethod.GET)
    public String verificationUser(@PathVariable String pathVerification){
    	User user = serviceUser.findByVerification(pathVerification);
    	user.setConfirmed(true);
    	user.setVerification(null);
    	serviceUser.saveAuthenticationUser(user);
    	return "redirect:/login";
    }
    
    @RequestMapping("/admin/user")
    public String showUser(Model model, 
    		@PageableDefault(3) Pageable pageable, 
    		@ModelAttribute(value="filter") UserFilterForm form){
        model.addAttribute("page", serviceUser.findAll(pageable, form));
        return "adminUser";
    }
    
    @RequestMapping(value="/admin/user", method=RequestMethod.POST)
    public String save(@ModelAttribute("user") @Valid UserForm user, 
    		BindingResult br, 
    		Model model, 
    		@PageableDefault(3) Pageable pageable, 
    		@ModelAttribute(value="filter") UserFilterForm form){
    	if(br.hasErrors()){
    		model.addAttribute("page", serviceUser.findAll(pageable, form));
            return "adminUser";
    	}
    	serviceUser.save(user);
    	return "redirect:/admin/user" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/admin/user/delete/{id}")
	public String delete(@PathVariable long id, 
			@PageableDefault(3) Pageable pageable, 
    		@ModelAttribute(value="filter") UserFilterForm form){
    	serviceUser.delete(id);
    	return "redirect:/admin/user" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/admin/user/update/{id}")
    public String update(@PathVariable long id, 
    		Model model, 
    		@PageableDefault(3) Pageable pageable, 
    		@ModelAttribute(value="filter") UserFilterForm form){
    	model.addAttribute("user", serviceUser.findForForm(id));
    	model.addAttribute("page", serviceUser.findAll(pageable, form));
    	return "adminUser";
    }
    
    private String getParams(Pageable pageable, UserFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		buffer.append(String.valueOf(pageable.getPageNumber()+1));
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if(pageable.getSort()!=null){
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order)->{
				buffer.append(order.getProperty());
				if(order.getDirection()!=Direction.ASC)
				buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(form.getSearch());
		return buffer.toString();
    }
}
