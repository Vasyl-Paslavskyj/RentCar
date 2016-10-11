package myProject.service.Implementation.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import myProject.form.filter.AccidentFilterFormForUser;
import myProject.models.Accident;
import myProject.models.Car;
import myProject.models.ClassOfCar;
import myProject.models.Custom;
import myProject.models.ModelOfCar;
import myProject.models.User;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccidentFilterForUserAdapter implements Specification<Accident>{
	private final AccidentFilterFormForUser form;
	private final List<Specification<Accident>> filters = new ArrayList<>();
	
	public AccidentFilterForUserAdapter(AccidentFilterFormForUser form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new AccidentFilterFormForUser();
		}
	}
	
	private void findByRegistrationNamber(){
		if(form.getSearch()!=null){
			filters.add((root, query, cb)->{
				Join<Accident, Custom> join = root.join("customSet");
				Join<Custom, Car> join1 = join.join("car");
				Expression<String> exp = join1.get("registrationNamber");
				Predicate pr = cb.like(cb.upper(exp), form.getSearch().toUpperCase()+"%");
				return pr;
			});
		}
	}
	private void findByLocalDateTime(){
		if(form.getMinDT()!=null&&form.getMaxDT()!=null){
			filters.add((root, query, cb)->{
				return cb.between(root.get("dateTime"), form.getMinDT(), form.getMaxDT());
			});
		}else if(form.getMinDT()!=null){
			filters.add((root, query, cb)->{
				return cb.greaterThanOrEqualTo(root.get("dateTime"), form.getMinDT());
			});
		}else if(form.getMaxDT()!=null){
			filters.add((root, query, cb)->{
				return cb.lessThanOrEqualTo(root.get("dateTime"), form.getMaxDT());
			});
		}
	}
	private void findByTypeClassOfCar(){
		if(!form.getTypeClassOfCar().isEmpty()){
			filters.add((root, query, cb)->{
				Join<Accident, Custom> join = root.join("customSet");
				Join<Custom, Car> join1 = join.join("car");
				Join<Car, ModelOfCar> join2 = join1.join("modelOfCar");
				Join<ModelOfCar, ClassOfCar> join3 = join2.join("classOfCar");
				Predicate pr = join3.get("typeClassOfCar").in(form.getTypeClassOfCar());
				return pr;
			});
		}
	}
	private void mainFindForPrincipal(){
		filters.add((root, query, cb)->{
			Join<Accident, Custom> join = root.join("customSet");
			Join<Custom, User> join1 = join.join("user");
			Expression<String> exp1 = join1.get("id");
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			long idUser = Long.valueOf(id);
			Predicate pr1 = cb.equal(exp1, idUser);
			return pr1;
		});
	}

	@Override
	public Predicate toPredicate(Root<Accident> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class){
			Fetch<Accident, Custom> root1 = root.fetch("customSet");
			root1.fetch("user");
			Fetch<Custom, Car> root2 = root1.fetch("car");
			Fetch<Car, ModelOfCar> root3 = root2.fetch("modelOfCar");
			root3.fetch("classOfCar");
		}
		
		mainFindForPrincipal();
		findByRegistrationNamber();
		findByLocalDateTime();
		findByTypeClassOfCar();
		
		if(!filters.isEmpty()){
			Specifications<Accident> spec = Specifications.where(filters.get(0));
			for(Specification<Accident> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}

}
