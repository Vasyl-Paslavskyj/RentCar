package myProject.service.Implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import myProject.form.filter.ModelOfCarFilterForm;
import myProject.models.ClassOfCar;
import myProject.models.ModelOfCar;

import org.springframework.data.jpa.domain.Specification;

public class ModelOfCarFilterAdapter implements Specification<ModelOfCar>{
	private String search = "";

	public ModelOfCarFilterAdapter(ModelOfCarFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<ModelOfCar> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class){
			root.fetch("classOfCar");
		}
		Join<ModelOfCar, ClassOfCar> join = root.join("classOfCar");
		Expression<String> exp = join.get("typeClassOfCar");
		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
	}
	
}
