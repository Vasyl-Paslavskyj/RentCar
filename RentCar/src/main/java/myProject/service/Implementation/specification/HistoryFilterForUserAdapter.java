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




import myProject.form.filter.HistoryFilterFormForUser;
import myProject.models.Accident;
import myProject.models.Car;
import myProject.models.ClassOfCar;
import myProject.models.Custom;
import myProject.models.ModelOfCar;
import myProject.models.User;




import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.core.context.SecurityContextHolder;

public class HistoryFilterForUserAdapter implements Specification<Custom>{
	private final HistoryFilterFormForUser form;
	private final List<Specification<Custom>> filters = new ArrayList<>();
	
	public HistoryFilterForUserAdapter(HistoryFilterFormForUser form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new HistoryFilterFormForUser();
		}
	}

	private void findByLocalDateTime(){
		if(form.getMinDT()!=null&&form.getMaxDT()!=null){
			filters.add((root, query, cb)->{
				Predicate pr1 = cb.between(root.get("dateTimeStart"), form.getMinDT(), form.getMaxDT());
				Predicate pr2 = cb.between(root.get("dateTimeFinish"), form.getMinDT(), form.getMaxDT());
				return cb.or(pr1, pr2);
			});
		}else if(form.getMinDT()!=null){
			filters.add((root, query, cb)->{
				Predicate pr1 = cb.greaterThanOrEqualTo(root.get("dateTimeStart"), form.getMinDT());
				Predicate pr2 = cb.greaterThanOrEqualTo(root.get("dateTimeFinish"), form.getMinDT());
				return cb.or(pr1, pr2);
			});
		}else if(form.getMaxDT()!=null){
			filters.add((root, query, cb)->{
				Predicate pr1 = cb.lessThanOrEqualTo(root.get("dateTimeStart"), form.getMaxDT());
				Predicate pr2 = cb.lessThanOrEqualTo(root.get("dateTimeFinish"), form.getMaxDT());
				return cb.or(pr1, pr2);
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
	private void findByAccident(){
		if(!form.getId().isEmpty()){
			filters.add((root, query, cb)->{
				Join<Custom, Accident> join = root.join("accidentSet");
				Predicate pr = join.get("id").in(form.getId());
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
		if(query.getResultType()!=Long.class && query.getResultType()!=Long.class && query.getResultType()!=Long.class){
			root.fetch("user");
			Fetch<Custom, Car> root1 = root.fetch("car");
			Fetch<Car, ModelOfCar> root2 = root1.fetch("modelOfCar");
			root2.fetch("classOfCar");
			root.fetch("accidentSet");
		}
		mainFindForPrincipal();
		findByAccident();
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
	}

}
