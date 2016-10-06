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

import myProject.form.filter.CustomFilterFormForUser;
import myProject.models.Car;
import myProject.models.ClassOfCar;
import myProject.models.Custom;
import myProject.models.ModelOfCar;
import myProject.models.User;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomFilterForUserAdapter implements Specification<Custom>{
	private final CustomFilterFormForUser form;
	private final List<Specification<Custom>> filters = new ArrayList<>();
	
	public CustomFilterForUserAdapter(CustomFilterFormForUser form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new CustomFilterFormForUser();
		}
	}
	
	private void findByRegistrationNamber(){
		if(form.getSearch()!=null){
			filters.add((root, query, cb)->{
				Join<Custom, Car> join = root.join("car");
				Expression<String> exp = join.get("registrationNamber");
				Predicate pr = cb.like(cb.upper(exp), form.getSearch().toUpperCase()+"%");
				return pr;
			});
		}
	}
	
	private void findByCost(){
		if(form.getMinInt()!=0&&form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("cost");
				return cb.between(exp, form.getMinInt(), form.getMaxInt());
			});
		}else if(form.getMinInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("cost");
				return cb.ge(exp, form.getMinInt());
			});
		}else if(form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Expression<Integer> exp = root.get("cost");
				return cb.le(exp, form.getMaxInt());
			});
		}
	}
	
	private void findByLocalDateTime(){
		if(form.getMinDT()!=null&&form.getMaxDT()!=null){
			filters.add((root, query, cb)->{
				return cb.between(root.get("dateTimeStart"), form.getMinDT(), form.getMaxDT());
			});
		}else if(form.getMinDT()!=null){
			filters.add((root, query, cb)->{
//				Expression<LocalDateTime> exp = root.get("dateTimeStart");
				return cb.greaterThanOrEqualTo(root.get("dateTimeStart"), form.getMinDT());
			});
		}else if(form.getMaxDT()!=null){
			filters.add((root, query, cb)->{
//				Expression<LocalDateTime> exp = root.get("dateTimeStart");
				return cb.lessThanOrEqualTo(root.get("dateTimeStart"), form.getMaxDT());
			});
		}
	}
	
	private void findByTypeClassOfCar(){
		if(!form.getTypeClassOfCar().isEmpty()){
			filters.add((root, query, cb)->{
				Join<Custom, Car> join = root.join("car");
				Join<Car, ModelOfCar> join1 = join.join("modelOfCar");
				Join<ModelOfCar, ClassOfCar> join2 = join1.join("classOfCar");
				Predicate pr = join2.get("typeClassOfCar").in(form.getTypeClassOfCar());
				return pr;
			});
		}
	}
	
	private void mainFindForPrincipal(){
		filters.add((root, query, cb)->{
			Join<Custom, User> join1 = root.join("user");
			Expression<String> exp1 = join1.get("id");
			String id = SecurityContextHolder.getContext().getAuthentication().getName();
			long idUser = Long.valueOf(id);
			Predicate pr1 = cb.equal(exp1, idUser);
			return pr1;
		});
	}
	
	@Override
	public Predicate toPredicate(Root<Custom> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class && query.getResultType()!=Long.class){
			root.fetch("user");
			Fetch<Custom, Car> root1 = root.fetch("car");
			Fetch<Car, ModelOfCar> root2 = root1.fetch("modelOfCar");
			root2.fetch("classOfCar");
		}
		
		mainFindForPrincipal();
		findByRegistrationNamber();
		findByCost();
		findByLocalDateTime();
		findByTypeClassOfCar();
		
		if(!filters.isEmpty()){
			Specifications<Custom> spec = Specifications.where(filters.get(0));
			for(Specification<Custom> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
		
//		Join<Custom, User> join1 = root.join("user");
//		Join<Custom, Car> join2 = root.join("car");
//		
//		Expression<String> exp1 = join1.get("id");
//		Expression<String> exp2 = join2.get("registrationNamber");
//		
//		String id = SecurityContextHolder.getContext().getAuthentication().getName();
//		long idUser = Long.valueOf(id);
//		Predicate pr1 = cb.equal(exp1, idUser);
//		Predicate pr2 = cb.like(cb.upper(exp2), search.toUpperCase()+"%");
//		Predicate allPred = cb.and(pr1, pr2);
//		
//		return allPred;
	}
	
}
