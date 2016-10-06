package myProject.service.Implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import myProject.form.filter.CustomFilterForm;
import myProject.models.Car;
import myProject.models.ClassOfCar;
import myProject.models.Custom;
import myProject.models.ModelOfCar;
import myProject.models.User;

import org.springframework.data.jpa.domain.Specification;

public class CustomFilterAdapter implements Specification<Custom>{
	private String search1 = "";
	
	public CustomFilterAdapter(CustomFilterForm form) {
		if(form.getSearch1()!=null)
			search1 = form.getSearch1();
	}

	@Override
	public Predicate toPredicate(Root<Custom> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class && query.getResultType()!=Long.class){
			root.fetch("user");
			Fetch<Custom, Car> root1 = root.fetch("car");
			Fetch<Car, ModelOfCar> root2 = root1.fetch("modelOfCar");
			root2.fetch("classOfCar");
		}
		
		Join<Custom, User> join1 = root.join("user");
		Join<Custom, Car> join2 = root.join("car");
		
		Expression<String> exp1 = join1.get("login");
		Expression<String> exp2 = join2.get("registrationNamber");
		
		Predicate pr1 = cb.like(cb.upper(exp1), search1.toUpperCase()+"%");
		Predicate pr2 = cb.like(cb.upper(exp2), search1.toUpperCase()+"%");
		Predicate prAll = cb.or(pr1, pr2);
		return prAll;
	}

}
