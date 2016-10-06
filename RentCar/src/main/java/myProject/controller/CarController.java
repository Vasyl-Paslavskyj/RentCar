package myProject.controller;

import javax.validation.Valid;

import myProject.form.CarForm;
import myProject.form.filter.CarFilterForm;
import myProject.models.ClassOfCar;
import myProject.models.ModelOfCar;
import myProject.models.StatusCar;
import myProject.service.CarService;
import myProject.service.ClassOfCarService;
import myProject.service.ModelOfCarService;
import myProject.service.StatusCarService;
import myProject.service.Implementation.editor.ClassOfCarEditor;
import myProject.service.Implementation.editor.ModelOfCarEditor;
import myProject.service.Implementation.editor.StatusCarEditor;
import myProject.service.Implementation.validator.CarFormValidator;

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
public class CarController {
    @Autowired
    CarService serviceCar;

    @Autowired
    ModelOfCarService serviceModelOfCar;
    @Autowired
    StatusCarService serviceStatusCar;
    @Autowired
    ClassOfCarService serviceClassOfCar;
        
    @InitBinder("car")
    protected void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(ModelOfCar.class, new ModelOfCarEditor(serviceModelOfCar));
    	binder.registerCustomEditor(StatusCar.class, new StatusCarEditor(serviceStatusCar));
    	binder.registerCustomEditor(ClassOfCar.class, new ClassOfCarEditor(serviceClassOfCar));
    	binder.setValidator(new CarFormValidator(serviceCar));
    }
    
    @ModelAttribute("car")
    public CarForm getCarForm(){
    	return new CarForm();
    }
    
    @ModelAttribute("filter")
    public CarFilterForm getFilter(){
    	return new CarFilterForm();
    }

    @RequestMapping("/admin/car")
    public String showCar(Model model, 
    		@PageableDefault(5) Pageable pageable, 
    		@ModelAttribute(value="filter") CarFilterForm form){
    	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    	model.addAttribute("typeModelCars", serviceModelOfCar.findAllTypeModelCar());
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
    	model.addAttribute("statusCars", serviceStatusCar.findAll());
    	model.addAttribute("page", serviceCar.findAll(pageable, form));
        return "adminCar";
    }

    @RequestMapping(value="/admin/car", method=RequestMethod.POST)
    public String save(@ModelAttribute("car") @Valid CarForm car, 
    		BindingResult br, 
    		Model model, 
    		@PageableDefault(5) Pageable pageable, 
    		@ModelAttribute(value="filter") CarFilterForm form){
    	if(br.hasErrors()){
    		model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    		model.addAttribute("typeModelCars", serviceModelOfCar.findAllTypeModelCar());
    		model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    		model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
        	model.addAttribute("statusCars", serviceStatusCar.findAll());
        	model.addAttribute("page", serviceCar.findAll(pageable, form));
            return "adminCar";
    	}
    	serviceCar.save(car);
		return "redirect:/admin/car" +getParams(pageable, form);
    }
//    @RequestMapping(value="/admin/car", method=RequestMethod.POST)
//    public String save(@ModelAttribute("car") Car car, Model model){
//    	serviceCar.saveCar(car);
//		return "redirect:/admin/car";
//    }
    
    @RequestMapping(value="/admin/car/update/{id}")
    public String update(@PathVariable long id, 
    		Model model, 
    		@PageableDefault(5) Pageable pageable, 
    		@ModelAttribute(value="filter") CarFilterForm form){
    	model.addAttribute("car", serviceCar.findForForm(id));
    	model.addAttribute("modelOfCars", serviceModelOfCar.findAll());
    	model.addAttribute("typeModelCars", serviceModelOfCar.findAllTypeModelCar());
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("typeClassOfCars", serviceClassOfCar.findAllTypeClassOfCar());
    	model.addAttribute("statusCars", serviceStatusCar.findAll());
    	model.addAttribute("page", serviceCar.findAll(pageable, form));
    	return "adminCar";
    }
    @RequestMapping(value="/admin/car/delete/{id}")
    public String delete(@PathVariable long id, 
    		@PageableDefault(5) Pageable pageable, 
    		@ModelAttribute(value="filter") CarFilterForm form){
    	serviceCar.delete(id);
    	return "redirect:/admin/car" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/admin/car/deleteAllFilters")
    public String deleteAllFilters(Pageable pageable, @ModelAttribute(value="filter") CarFilterForm form){
    	return "redirect:/admin/car" +getStandartParams(pageable, form);
    }
    
    private String getParams(Pageable pageable, CarFilterForm form){
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
		
		for(String s : form.getTypeModelCar()){
			buffer.append("&typeModelCarIds=");
			buffer.append(s);
		}
		
		for(String s : form.getTypeClassOfCar()){
			buffer.append("&typeClassOfCarIds=");
			buffer.append(s);
		}
//		for(Integer i : form.getModelOfCar()){
//			buffer.append("&modelOfCarIds=");
//			buffer.append(i.toString());
//		}
		for(Integer i : form.getStatusCar()){
			buffer.append("&statusCarIds=");
			buffer.append(i.toString());
		}
		return buffer.toString();
    }
    
    private String getStandartParams(Pageable pageable, CarFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=1");
		buffer.append("&size=5");
		buffer.append("&sort=");
		buffer.append("&search=");
		return buffer.toString();
	}
}
