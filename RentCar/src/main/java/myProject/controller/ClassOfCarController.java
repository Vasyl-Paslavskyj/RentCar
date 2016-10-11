package myProject.controller;

import javax.validation.Valid;

import myProject.form.ClassOfCarForm;
import myProject.form.filter.ClassOfCarFilterForm;
import myProject.service.ClassOfCarService;
import myProject.service.Implementation.validator.ClassOfCarFormValidator;

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
public class ClassOfCarController {

    @Autowired
    ClassOfCarService serviceClassOfCar;
    
    @InitBinder("classOfCar")
    protected void initBinder(WebDataBinder binder){
    	binder.setValidator(new ClassOfCarFormValidator(serviceClassOfCar));
    }
    
    @ModelAttribute("classOfCar")
    public ClassOfCarForm getClassOfCarForm(){
    	return new ClassOfCarForm();
    }
    
    @ModelAttribute("filter")
    public ClassOfCarFilterForm getFilter(){
    	return new ClassOfCarFilterForm();
    }
    
    @RequestMapping("/admin/classOfCar")
    public String showClassOfCar(Model model, 
    		@PageableDefault(3) Pageable pageable, 
    		@ModelAttribute(value="filter") ClassOfCarFilterForm form){
    	model.addAttribute("page", serviceClassOfCar.findAll(pageable, form));
        return "adminClassOfCar";
    }

    @RequestMapping(value="/admin/classOfCar", method=RequestMethod.POST)
	public String save(@ModelAttribute("classOfCar") @Valid ClassOfCarForm classOfCar, 
			BindingResult br, 
			Model model, 
			@PageableDefault(3) Pageable pageable, 
			@ModelAttribute(value="filter") ClassOfCarFilterForm form){
    	if(br.hasErrors()){
    		model.addAttribute("page", serviceClassOfCar.findAll(pageable, form));
        	return "adminClassOfCar";
    	}
    	serviceClassOfCar.save(classOfCar);
		return "redirect:/admin/classOfCar" +getParams(pageable, form);
    }
    
//    @RequestMapping(value="/admin/classOfCar/delete/{typeClassOfCar}")
//	public String delete(@PathVariable TypeClassOfCar typeClassOfCar){
//    	serviceClassOfCar.delete(typeClassOfCar);
//		return "redirect:/admin/classOfCar";
//    }
    
    @RequestMapping(value="/admin/classOfCar/delete/{id}")
	public String delete(@PathVariable long id, 
			@PageableDefault(3) Pageable pageable, 
			@ModelAttribute(value="filter") ClassOfCarFilterForm form){
    	serviceClassOfCar.delete(id);
		return "redirect:/admin/classOfCar" +getParams(pageable, form);
    }
    
//    @RequestMapping(value="/admin/classOfCar/update/{typeClassOfCar}")
//	public String update(@PathVariable TypeClassOfCar typeClassOfCar){
//    	int price = 111;
//    	serviceClassOfCar.updatePriceByTypeClassOfCar(typeClassOfCar, price);
//		return "redirect:/admin/classOfCar";
//    }
    
    @RequestMapping(value="/admin/classOfCar/update/{id}")
    public String update(@PathVariable long id, 
    		Model model, 
    		@PageableDefault(3) Pageable pageable, 
			@ModelAttribute(value="filter") ClassOfCarFilterForm form){
    	model.addAttribute("classOfCar", serviceClassOfCar.findForForm(id));
    	model.addAttribute("page", serviceClassOfCar.findAll(pageable, form));
    	return "adminClassOfCar";
    }
    
    private String getParams(Pageable pageable, ClassOfCarFilterForm form){
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
