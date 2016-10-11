package myProject.controller;

import myProject.form.StatusCarForm;
import myProject.models.Status;
import myProject.service.StatusCarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vasj on 19.08.2016.
 */
@Controller
public class StatusCarController {
    @Autowired
    StatusCarService serviceStatusCar;
    
    @ModelAttribute("statusCar")
    public StatusCarForm getStatusCarForm(){
    	return new StatusCarForm();
    }

    @RequestMapping("/admin/statusCar")
    public String showStatusCar(Model model, 
    		@PageableDefault(2) Pageable pageable) {
    	model.addAttribute("statuss", Status.values());
        model.addAttribute("page", serviceStatusCar.findAll(pageable));
        return "adminStatusCar";
    }
    
    @RequestMapping(value="/admin/statusCar", method=RequestMethod.POST)
	public String save(@ModelAttribute("statusCar") StatusCarForm statusCar, 
			Model model, 
			@PageableDefault(2) Pageable pageable){
    	serviceStatusCar.save(statusCar);
		return "redirect:/admin/statusCar" +getParams(pageable);
    }
    
    @RequestMapping(value="/admin/statusCar/delete/{id}")
	public String delete(@PathVariable long id, 
			@PageableDefault(2) Pageable pageable){
    	serviceStatusCar.delete(id);
		return "redirect:/admin/statusCar" +getParams(pageable);
    }
    
    @RequestMapping(value="/admin/statusCar/update/{id}")
	public String update(@PathVariable long id, 
			Model model, 
			@PageableDefault(2) Pageable pageable){
    	model.addAttribute("statusCar", serviceStatusCar.findForForm(id));
    	model.addAttribute("statuss", Status.values());
    	model.addAttribute("page", serviceStatusCar.findAll(pageable));
    	return "adminStatusCar";
    }
    
    private String getParams(Pageable pageable){
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
		return buffer.toString();
    }
}
