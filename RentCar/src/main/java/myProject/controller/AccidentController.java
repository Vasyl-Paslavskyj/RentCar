package myProject.controller;

import javax.validation.Valid;

import myProject.form.AccidentForm;
import myProject.form.filter.AccidentFilterForm;
import myProject.models.Custom;
import myProject.service.AccidentService;
import myProject.service.CustomService;
import myProject.service.Implementation.editor.CustomEditor;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Vasj on 19.08.2016.
 */
@Controller
public class AccidentController {
    @Autowired
    AccidentService serviceAccident;
    @Autowired
    CustomService serviceCustom;
    
    @InitBinder("accident")
    protected void initBinder(WebDataBinder binder){
    	binder.registerCustomEditor(Custom.class, new CustomEditor(serviceCustom));
    	binder.setValidator(new AccidentFormValidator(serviceAccident));
    }
    
    @ModelAttribute("accident")
    public AccidentForm getAccident(){
    	return new AccidentForm();
    }
    
    @ModelAttribute("filter")
    public AccidentFilterForm getFilter(){
    	return new AccidentFilterForm();
    }

    @RequestMapping("/admin/accident")
    public String showAccident(Model model, 
    		@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterForm form) {
    	model.addAttribute("customs", serviceCustom.findAll());
        model.addAttribute("page", serviceAccident.findAll(pageable, form));
        return "adminAccident";
    }
    
    @RequestMapping(value="/admin/accident", method=RequestMethod.POST)
    public String saveIfOne(@ModelAttribute("accident") @Valid AccidentForm accident, 
    		BindingResult br, 
    		Model model, 
    		@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterForm form){
    	if(br.hasErrors()){
    		model.addAttribute("customs", serviceCustom.findAll());
    		model.addAttribute("page", serviceAccident.findAll(pageable, form));
            return "adminAccident";
    	}
    	serviceAccident.saveIfOneCar(accident);
    	return "redirect:/admin/accident" +getParams(pageable, form);
    }
        
    @RequestMapping(value="/admin/accident/delete/{id}")
	public String delete(@PathVariable long id, 
			@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterForm form){
    	serviceAccident.deleteById(id);
    	return "redirect:/admin/accident" +getParams(pageable, form);
    }
    
    @RequestMapping(value="/admin/accident/update/{id}")
	public String update(Model model, 
			@PathVariable long id, 
			@PageableDefault(2) Pageable pageable, 
    		@ModelAttribute(value="filter") AccidentFilterForm form){
    	model.addAttribute("accident", serviceAccident.findForForm(id));
    	model.addAttribute("customs", serviceCustom.findAll());
    	model.addAttribute("page", serviceAccident.findAll(pageable, form));
    	return "adminAccident";
    }
    
    private String getParams(Pageable pageable, AccidentFilterForm form){
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
