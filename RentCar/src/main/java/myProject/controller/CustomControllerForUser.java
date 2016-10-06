package myProject.controller;

import java.security.Principal;

import javax.validation.Valid;

import myProject.form.CustomFormForUser;
import myProject.form.filter.CustomFilterFormForUser;
import myProject.models.Car;
import myProject.models.City;
import myProject.models.ClassOfCar;
import myProject.models.ModelOfCar;
import myProject.service.CarService;
import myProject.service.ClassOfCarService;
import myProject.service.CustomService;
import myProject.service.ModelOfCarService;
import myProject.service.Implementation.editor.CarEditor;
import myProject.service.Implementation.editor.ClassOfCarEditor;
import myProject.service.Implementation.editor.ModelOfCarEditor;
import myProject.service.Implementation.validator.CustomFormForUserValidator;

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

@Controller
public class CustomControllerForUser {
	@Autowired
    CustomService serviceCustom;
    @Autowired
    CarService serviceCar;
    @Autowired
    ModelOfCarService serviceModelOfCar;
    @Autowired
    ClassOfCarService serviceClassOfCar;
    
    
    @InitBinder("custom")
    protected void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(Car.class, new CarEditor(serviceCar));
    	binder.registerCustomEditor(ModelOfCar.class, new ModelOfCarEditor(serviceModelOfCar));
    	binder.registerCustomEditor(ClassOfCar.class, new ClassOfCarEditor(serviceClassOfCar));
    	binder.setValidator(new CustomFormForUserValidator(serviceCustom));
    }
    
    @ModelAttribute("custom")
    public CustomFormForUser getCustomFormForUser(){
    	return new CustomFormForUser();
    }
    
    @ModelAttribute("filter")
    public CustomFilterFormForUser getFilter(){
    	return new CustomFilterFormForUser();
    }
    
    @RequestMapping("/user/custom")
    public String showCustom(Model model, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterFormForUser form){
    	model.addAttribute("cars", serviceCar.findAll());
    	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
    	model.addAttribute("cities", City.values());
        model.addAttribute("page", serviceCustom.findAll(pageable, form));
        return "userCustom";
    }
    
    @RequestMapping(value="/user/custom", method=RequestMethod.POST)
    public String save(@ModelAttribute("custom") @Valid CustomFormForUser custom, 
    		BindingResult br, 
    		Model model, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterFormForUser form, 
    		Principal principal){
    	if(br.hasErrors()){
        	model.addAttribute("cars", serviceCar.findAll());
        	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
        	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
        	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
        	model.addAttribute("cities", City.values());
        	model.addAttribute("page", serviceCustom.findAll(pageable, form));
            return "userCustom";
    	}
    	if(principal == null)
    		return "redirect:/login";
    	serviceCustom.save(custom, principal);
    	return "redirect:/user/custom" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/user/custom/update/{id}")
    public String update(@PathVariable long id, 
    		Model model, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterFormForUser form){
    	model.addAttribute("custom", serviceCustom.findForFormForUser(id));
    	model.addAttribute("cars", serviceCar.findAll());
    	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
    	model.addAttribute("cities", City.values());
        model.addAttribute("page", serviceCustom.findAll(pageable, form));
    	return "userCustom";
    }
    
    @RequestMapping(value="/user/custom/delete/{id}")
    public String delete(@PathVariable long id, 
    		@PageableDefault(4) Pageable pageable, 
    		@ModelAttribute(value="filter") CustomFilterFormForUser form){
    	serviceCustom.delete(id);
    	return "redirect:/user/custom" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/user/custom/deleteAllFilters")
    public String deleteAllFilters(Pageable pageable, @ModelAttribute(value="filter") CustomFilterFormForUser form){
    	return "redirect:/user/custom" +getStandartParams(pageable, form);
    }
    
    private String getParams(Pageable pageable, CustomFilterFormForUser form){
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
		
		buffer.append("&minCost=");
		buffer.append(form.getMinCost());
		buffer.append("&maxCost=");
		buffer.append(form.getMaxCost());
		
		buffer.append("&min=");
		buffer.append(form.getMin());
		buffer.append("&max=");
		buffer.append(form.getMax());
		
		for(String s : form.getTypeClassOfCar()){
			buffer.append("&typeClassOfCarIds=");
			buffer.append(s);
		}
//		for(Integer i : form.getModelOfCar()){
//			buffer.append("&modelOfCarIds=");
//			buffer.append(i.toString());
//		}
		
		return buffer.toString();
    }
    
    private String getStandartParams(Pageable pageable, CustomFilterFormForUser form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=1");
		buffer.append("&size=4");
		buffer.append("&sort=");
		buffer.append("&search=");
		return buffer.toString();
	}
}
