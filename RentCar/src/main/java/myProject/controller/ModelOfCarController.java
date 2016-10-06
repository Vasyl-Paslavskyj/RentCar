package myProject.controller;

import javax.validation.Valid;

import myProject.form.ModelOfCarForm;
import myProject.form.filter.ClassOfCarFilterForm;
import myProject.form.filter.ModelOfCarFilterForm;
import myProject.models.ClassOfCar;
import myProject.service.ClassOfCarService;
import myProject.service.ModelOfCarService;
import myProject.service.Implementation.editor.ClassOfCarEditor;
import myProject.service.Implementation.validator.ModelOfCarFormValidator;

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

/**
 * Created by Vasj on 19.08.2016.
 */
@Controller
public class ModelOfCarController {
    @Autowired
    ModelOfCarService serviceModelOfCar;
    @Autowired
    ClassOfCarService serviceClassOfCar;
    
    @InitBinder("modelOfCar")
    protected void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(ClassOfCar.class, new ClassOfCarEditor(serviceClassOfCar));
    	binder.setValidator(new ModelOfCarFormValidator(serviceModelOfCar));
    }
    
    @ModelAttribute("modelOfCar")
    public ModelOfCarForm getModelOfCarForm(){
    	return new ModelOfCarForm();
    }
    
    @ModelAttribute("filter")
    public ModelOfCarFilterForm getFilter(){
    	return new ModelOfCarFilterForm();
    }
    
    @RequestMapping("/admin/modelOfCar")
    public String showModelOfCar(Model model, 
    		@PageableDefault(5) Pageable pageable, 
    		@ModelAttribute(value="filter") ModelOfCarFilterForm form){
    	model.addAttribute("classOfCars", serviceClassOfCar.findAll());
        model.addAttribute("page", serviceModelOfCar.findAll(pageable, form));
        return "adminModelOfCar";
    }
    
    @RequestMapping(value="/admin/modelOfCar", method=RequestMethod.POST)
	public String save(@ModelAttribute("modelOfCar") @Valid ModelOfCarForm modelOfCar, 
			BindingResult br, 
			Model model, 
			@PageableDefault(3) Pageable pageable, 
			@ModelAttribute(value="filter") ModelOfCarFilterForm form){
    	if(br.hasErrors()){
    		model.addAttribute("classOfCars", serviceClassOfCar.findAll());
            model.addAttribute("page", serviceModelOfCar.findAll(pageable, form));
            return "adminModelOfCar";
    	}
    	serviceModelOfCar.saveModelOfCar(modelOfCar);
		return "redirect:/admin/modelOfCar" +getParams(pageable, form);
    }
    
//    @RequestMapping(value="/admin/modelOfCar", method=RequestMethod.POST)
//	public String save(@RequestParam long classOfCarId, 
//			@RequestParam TypeModelCar typeModelCar){
//    	serviceModelOfCar.saveNew(classOfCarId, typeModelCar);;
//		return "redirect:/admin/modelOfCar";
//    }

//    @RequestMapping(value="/admin/modelOfCar/delete/{typeClassOfCar, typeModelCar}")
//	public String delete(@PathVariable TypeClassOfCar typeClassOfCar, 
//			@PathVariable TypeModelCar typeModelCar){
//    	serviceModelOfCar.delete(typeClassOfCar, typeModelCar);
//		return "redirect:/admin/modelOfCar";
//    }
    
    @RequestMapping(value="/admin/modelOfCar/delete/{id}")
	public String delete(@PathVariable long id, 
			@PageableDefault(3) Pageable pageable, 
			@ModelAttribute(value="filter") ModelOfCarFilterForm form){
    		serviceModelOfCar.delete(id);
    		return "redirect:/admin/modelOfCar" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/admin/modelOfCar/update/{id}")
    public String update(Model model, 
    		@PathVariable long id, 
    		@PageableDefault(3) Pageable pageable, 
			@ModelAttribute(value="filter") ModelOfCarFilterForm form){
    	model.addAttribute("modelOfCar", serviceModelOfCar.findForForm(id));
		model.addAttribute("classOfCars", serviceClassOfCar.findAll());
    	model.addAttribute("page", serviceModelOfCar.findAll(pageable, form));
    	return "adminModelOfCar";
    }
    
    @RequestMapping(value="/admin/modelOfCar/deleteAllFilters")
    public String deleteAllFilters(Pageable pageable, 
    		@ModelAttribute(value="filter") ModelOfCarFilterForm form){
    	return "redirect:/admin/modelOfCar" +getStandartParams(pageable, form);
    }
    
    private String getParams(Pageable pageable, ModelOfCarFilterForm form){
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
    private String getStandartParams(Pageable pageable, ModelOfCarFilterForm form){
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=1");
		buffer.append("&size=5");
		buffer.append("&sort=");
		buffer.append("&search=");
		return buffer.toString();
	}
}
