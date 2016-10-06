package myProject.controller;

import javax.validation.Valid;

import myProject.form.CustomForm;
import myProject.form.filter.CustomFilterForm;
import myProject.models.Car;
import myProject.models.City;
import myProject.models.User;
import myProject.service.CarService;
import myProject.service.CustomService;
import myProject.service.UserService;
import myProject.service.Implementation.editor.CarEditor;
import myProject.service.Implementation.editor.UserEditor;
import myProject.service.Implementation.validator.CustomFormValidator;

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
public class CustomController {
    @Autowired
    CustomService serviceCustom;
    @Autowired
    UserService serviceUser;
    @Autowired
    CarService serviceCar;
    
    @InitBinder("custom")
    protected void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(Car.class, new CarEditor(serviceCar));
    	binder.registerCustomEditor(User.class, new UserEditor(serviceUser));
    	binder.setValidator(new CustomFormValidator(serviceCustom));
    }
    
    @ModelAttribute("custom")
    public CustomForm getCustomForm(){
    	return new CustomForm();
    }
    
    @ModelAttribute("filter")
    public CustomFilterForm getFilter(){
    	return new CustomFilterForm();
    }

    @RequestMapping("/admin/custom")
    public String showCustom(Model model, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterForm form){
    	model.addAttribute("users", serviceUser.findAll());
    	model.addAttribute("cars", serviceCar.findAll());
    	model.addAttribute("cities", City.values());
        model.addAttribute("page", serviceCustom.findAll(pageable, form));
        return "adminCustom";
    }
    
    @RequestMapping(value="/admin/custom", method=RequestMethod.POST)
    public String save(@ModelAttribute("custom") @Valid CustomForm custom, 
    		BindingResult br, 
    		Model model, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterForm form){
    	if(br.hasErrors()){
    		model.addAttribute("users", serviceUser.findAll());
        	model.addAttribute("cars", serviceCar.findAll());
        	model.addAttribute("cities", City.values());
        	model.addAttribute("page", serviceCustom.findAll(pageable, form));
            return "adminCustom";
    	}
    	serviceCustom.save(custom);
    	return "redirect:/admin/custom" +getParams(pageable, form);
    }
    
//    @RequestMapping(value="/admin/custom", method=RequestMethod.POST)
//    public String save(@RequestParam long userId, @RequestParam long carId, 
//    		@RequestParam String dateTimeStart, @RequestParam City cityStart, 
//    		@RequestParam String dateTimeFinish, @RequestParam City cityFinish, 
//    		@RequestParam int rentalLength, @RequestParam int cost){
//    	serviseCustom.saveNew(userId, carId, dateTimeStart, cityStart, dateTimeFinish, 
//    			cityFinish, rentalLength, cost);
//    	return "redirect:/admin/custom";
//    }
    
//    @RequestMapping(value="/admin/custom", method=RequestMethod.POST)
//    public String save(@RequestParam long userId, @RequestParam long carId, 
//    		@RequestParam LocalDateTime dateTimeStart, @RequestParam City cityStart, 
//    		@RequestParam LocalDateTime dateTimeFinish, @RequestParam City cityFinish, 
//    		@RequestParam int rentalLength, @RequestParam int cost){
//    	serviseCustom.save(userId, carId, dateTimeStart, cityStart, dateTimeFinish, cityFinish, rentalLength, cost);
//    	return "redirect:/admin/custom";
//    }
    
    @RequestMapping(value="/admin/custom/delete/{id}")
    public String delete(@PathVariable long id, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterForm form){
    	serviceCustom.delete(id);
    	return "redirect:/admin/custom" +getParams(pageable, form);
    }
    

    @RequestMapping(value="/admin/custom/update/{id}")
    public String update(@PathVariable long id, 
    		Model model, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterForm form){
    	model.addAttribute("custom", serviceCustom.findForForm(id));
    	model.addAttribute("users", serviceUser.findAll());
    	model.addAttribute("cars", serviceCar.findAll());
    	model.addAttribute("cities", City.values());
        model.addAttribute("page", serviceCustom.findAll(pageable, form));
    	return "adminCustom";
    }
    
    private String getParams(Pageable pageable, CustomFilterForm form){
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
		buffer.append(form.getSearch1());
		return buffer.toString();
    }
}
