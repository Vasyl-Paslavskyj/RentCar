package myProject.service.Implementation.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import myProject.form.filter.UserFilterForm;
import myProject.models.User;

import org.springframework.data.jpa.domain.Specification;

public class UserFilterAdapter implements Specification<User>{
	private String search = "";
		
	public UserFilterAdapter(UserFilterForm form) {
		if(form.getSearch()!=null)
		search = form.getSearch();
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		Expression<String> exp = root.get("login");
		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
	}
}
