package myProject.service.Implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import myProject.form.filter.AccidentFilterForm;
import myProject.models.Accident;
import myProject.models.Car;
import myProject.models.Custom;
import myProject.models.User;

import org.springframework.data.jpa.domain.Specification;

public class AccidentFilterAdapter implements Specification<Accident>{
	private String search = "";
		
	public AccidentFilterAdapter(AccidentFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<Accident> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class){
			Fetch<Accident, Custom> rootFirst = root.fetch("customSet");
			rootFirst.fetch("car");
			rootFirst.fetch("user");
			query.distinct(true);
		}
		Join<Accident, Custom> join = root.join("customSet");
		Join<Custom, Car> join1 = join.join("car");
		Join<Custom, User> join2 = join.join("user");
		
		Expression<String> exp1 = join1.get("registrationNamber");
		Expression<String> exp2 = join2.get("login");
		
		Predicate pr1 = cb.like(cb.upper(exp1), search.toUpperCase()+"%");
		Predicate pr2 = cb.like(cb.upper(exp2), search.toUpperCase()+"%");
		Predicate prAll = cb.or(pr1, pr2);
		return prAll;
	}

}
