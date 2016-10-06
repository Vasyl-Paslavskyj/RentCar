package myProject.service.Implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import myProject.form.filter.ClassOfCarFilterForm;
import myProject.models.ClassOfCar;

public class ClassOfCarFilterAdapter implements Specification<ClassOfCar>{
	
	private String search = "";
	
	public ClassOfCarFilterAdapter(ClassOfCarFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}


	public Predicate toPredicate(Root<ClassOfCar> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<String> exp = root.get("typeClassOfCar");
		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
	}

}
