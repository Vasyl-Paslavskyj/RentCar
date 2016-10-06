package myProject.controller;

import java.security.Principal;

import javax.validation.Valid;

import myProject.form.AccidentForm;
import myProject.form.filter.AccidentFilterForm;
import myProject.form.filter.AccidentFilterFormForUser;
import myProject.form.filter.CustomFilterFormForUser;
import myProject.models.Car;
import myProject.models.ClassOfCar;
import myProject.models.Custom;
import myProject.models.ModelOfCar;
import myProject.service.AccidentService;
import myProject.service.CarService;
import myProject.service.ClassOfCarService;
import myProject.service.CustomService;
import myProject.service.ModelOfCarService;
import myProject.service.Implementation.editor.CarEditor;
import myProject.service.Implementation.editor.ClassOfCarEditor;
import myProject.service.Implementation.editor.CustomEditor;
import myProject.service.Implementation.editor.ModelOfCarEditor;
import myProject.service.Implementation.validator.AccidentFormValidator;

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
public class AccidentControllerForUser {
	@Autowired
    AccidentService serviceAccident;
    @Autowired
    CustomService serviceCustom;
    @Autowired
    CarService serviceCar;
    @Autowired
    ModelOfCarService serviceModelOfCar;
    @Autowired
    ClassOfCarService serviceClassOfCar;
    
    @InitBinder("accident")
    protected void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(Custom.class, new CustomEditor(serviceCustom));
    	binder.registerCustomEditor(Car.class, new CarEditor(serviceCar));
    	binder.registerCustomEditor(ModelOfCar.class, new ModelOfCarEditor(serviceModelOfCar));
    	binder.registerCustomEditor(ClassOfCar.class, new ClassOfCarEditor(serviceClassOfCar));
    	binder.setValidator(new AccidentFormValidator(serviceAccident));
    }
    
    @ModelAttribute("accident")
    public AccidentForm getAccident(){
    	return new AccidentForm();
    }
    
    @ModelAttribute("filter")
    public AccidentFilterFormForUser getFilter(){
    	return new AccidentFilterFormForUser();
    }
    
    @RequestMapping("/user/accident")
    public String showAccident(Model model, 
    		@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterFormForUser form, 
    		Principal principal){
    	model.addAttribute("customs", serviceCustom.findAllByPrincipal(principal));
    	model.addAttribute("cars", serviceCar.findAll());
    	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
    	model.addAttribute("page", serviceAccident.findAll(pageable, form));
    	return "userAccident";
    }
    
    @RequestMapping(value="/user/accident", method=RequestMethod.POST)
    public String save(@ModelAttribute("accident") @Valid AccidentForm accident, 
    		BindingResult br, 
    		Model model, 
    		@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterFormForUser form, 
    		Principal principal){
    	if(br.hasErrors()){
    		model.addAttribute("customs", serviceCustom.findAllByPrincipal(principal));
        	model.addAttribute("cars", serviceCar.findAll());
        	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
        	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
        	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
        	model.addAttribute("page", serviceAccident.findAll(pageable, form));
    	}
    	if(principal == null)
    		return "redirect:/login";
    	serviceAccident.saveIfOneCar(accident);
    	return "redirect:/user/accident" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/user/accident/update/{id}")
	public String update(Model model, 
			@PathVariable long id, 
			@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterFormForUser form, 
    		Principal principal){
    	model.addAttribute("accident", serviceAccident.findForForm(id));
    	model.addAttribute("customs", serviceCustom.findAllByPrincipal(principal));
    	model.addAttribute("cars", serviceCar.findAll());
    	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
    	model.addAttribute("page", serviceAccident.findAll(pageable, form));
    	return "userAccident";
    }
    
    @RequestMapping(value="/user/accident/delete/{id}")
	public String delete(@PathVariable long id, 
			@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterFormForUser form){
    	serviceAccident.deleteById(id);
    	return "redirect:/user/accident" +getParams(pageable, form);
    }
    
    
    @RequestMapping(value="/user/accident/deleteAllFilters")
    public String deleteAllFilters(Pageable pageable, @ModelAttribute(value="filter") AccidentFilterFormForUser form){
    	return "redirect:/user/accident" +getStandartParams(pageable, form);
    }
    
    private String getParams(Pageable pageable, AccidentFilterFormForUser form){
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
		
		buffer.append("&min=");
		buffer.append(form.getMin());
		buffer.append("&max=");
		buffer.append(form.getMax());
		
		for(String s : form.getTypeClassOfCar()){
			buffer.append("&typeClassOfCarIds=");
			buffer.append(s);
		}
		
		return buffer.toString();
    }
    
    private String getStandartParams(Pageable pageable, AccidentFilterFormForUser form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=1");
		buffer.append("&size=2");
		buffer.append("&sort=");
		buffer.append("&search=");
		return buffer.toString();
	}
}
