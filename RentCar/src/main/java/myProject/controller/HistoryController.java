package myProject.controller;

import java.security.Principal;

import myProject.form.filter.HistoryFilterFormForUser;
import myProject.models.Accident;
import myProject.models.Car;
import myProject.models.City;
import myProject.models.ClassOfCar;
import myProject.models.ModelOfCar;
import myProject.service.AccidentService;
import myProject.service.CarService;
import myProject.service.ClassOfCarService;
import myProject.service.CustomService;
import myProject.service.ModelOfCarService;
import myProject.service.Implementation.editor.AccidentEditor;
import myProject.service.Implementation.editor.CarEditor;
import myProject.service.Implementation.editor.ClassOfCarEditor;
import myProject.service.Implementation.editor.ModelOfCarEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HistoryController {
	@Autowired
    CustomService serviceCustom;
	@Autowired
    CarService serviceCar;
    @Autowired
    ModelOfCarService serviceModelOfCar;
    @Autowired
    ClassOfCarService serviceClassOfCar;
    @Autowired
    AccidentService serviceAccident;
    
    @InitBinder("history")
    protected void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(Car.class, new CarEditor(serviceCar));
    	binder.registerCustomEditor(ModelOfCar.class, new ModelOfCarEditor(serviceModelOfCar));
    	binder.registerCustomEditor(ClassOfCar.class, new ClassOfCarEditor(serviceClassOfCar));
    	binder.registerCustomEditor(Accident.class, new AccidentEditor(serviceAccident));
    }
    
    @ModelAttribute("filter")
    public HistoryFilterFormForUser getFilter(){
    	return new HistoryFilterFormForUser();
    }
    
    @RequestMapping("/user/history")
    public String showHistory(Model model, 
    		@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") HistoryFilterFormForUser form, 
    		Principal principal){
    	model.addAttribute("cars", serviceCar.findAll());
    	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
    	model.addAttribute("cities", City.values());
    	model.addAttribute("accidentSetIds", serviceAccident.findAllByPrincipal(principal));
        model.addAttribute("page", serviceCustom.findAll(pageable, form));
    	return "userHistory";
    }
    
    @RequestMapping(value="/user/history/deleteAllFilters")
    public String deleteAllFilters(Pageable pageable, @ModelAttribute(value="filter") HistoryFilterFormForUser form){
    	return "redirect:/user/history" +getStandartParams(pageable, form);
    }
    
    private String getParams(Pageable pageable, HistoryFilterFormForUser form){
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
				
		buffer.append("&min=");
		buffer.append(form.getMin());
		buffer.append("&max=");
		buffer.append(form.getMax());
		
		for(String s : form.getTypeClassOfCar()){
			buffer.append("&typeClassOfCarIds=");
			buffer.append(s);
		}
		for(Long i : form.getId()){
			buffer.append("&accidentIds=");
			buffer.append(i.toString());
		}
		
		return buffer.toString();
    }
    
    private String getStandartParams(Pageable pageable, HistoryFilterFormForUser form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=1");
		buffer.append("&size=2");
		buffer.append("&sort=");
		buffer.append("&search=");
		return buffer.toString();
	}
}
