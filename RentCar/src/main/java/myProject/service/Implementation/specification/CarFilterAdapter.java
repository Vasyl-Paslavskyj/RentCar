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

import myProject.form.filter.CarFilterForm;
import myProject.models.Car;
import myProject.models.ClassOfCar;
import myProject.models.ModelOfCar;
import myProject.models.StatusCar;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class CarFilterAdapter implements Specification<Car>{

	private final CarFilterForm form;
	private final List<Specification<Car>> filters = new ArrayList<>();
		
	public CarFilterAdapter(CarFilterForm form) {
		if (form != null) {
			this.form = form;
		} else {
			this.form = new CarFilterForm();
		}
	}

	private void findByTypeClassOfCar(){
		if(form.getSearch()!=null){
			filters.add((root, query, cb)->{
				Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
				Join<ModelOfCar, ClassOfCar> join2 = join1.join("classOfCar");
				Expression<String> exp2 = join2.get("typeClassOfCar");
				Predicate pr2 = cb.like(cb.upper(exp2), form.getSearch().toUpperCase()+"%");
				return pr2;
			});
		}
	}
	private void findByPrice(){
		if(form.getMinInt()!=0&&form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
				Join<ModelOfCar, ClassOfCar> join2 = join1.join("classOfCar");
				Expression<Integer> exp = join2.get("price");
				return cb.between(exp, form.getMinInt(), form.getMaxInt());
			});
		}else if(form.getMinInt()!=0){
			filters.add((root, query, cb)->{
				Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
				Join<ModelOfCar, ClassOfCar> join2 = join1.join("classOfCar");
				Expression<Integer> exp = join2.get("price");
				return cb.ge(exp, form.getMinInt());
			});
		}else if(form.getMaxInt()!=0){
			filters.add((root, query, cb)->{
				Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
				Join<ModelOfCar, ClassOfCar> join2 = join1.join("classOfCar");
				Expression<Integer> exp = join2.get("price");
				return cb.le(exp, form.getMaxInt());
			});
		}
	}
//	private void findClassOfCarByTypeClassOfCar(){
//		if(!form.getClassOfCar().isEmpty()){
//			filters.add((root, query, cb)->{
//				Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
//				Predicate pr = join1.get("classOfCar").in(form.getClassOfCar());
//				return pr;
//			});
//		}
//	}
	private void findByTypeModelCar(){
		if(!form.getTypeModelCar().isEmpty()){
			filters.add((root, query, cb)->{
				Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
				Predicate pr = join1.get("typeModelCar").in(form.getTypeModelCar());
				return pr;
			});
		}
	}
	
//	private void findByModelOfCar(){
//		if(!form.getModelOfCar().isEmpty()){
//			filters.add((root, query, cb)->root.get("modelOfCar").in(form.getModelOfCar()));
//		}
//	}
	
	private void findByTypeClassOfCarTwo(){
		if(!form.getTypeClassOfCar().isEmpty()){
			filters.add((root, query, cb)->{
				Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
				Join<ModelOfCar, ClassOfCar> join2 = join1.join("classOfCar");
				Predicate pr = join2.get("typeClassOfCar").in(form.getTypeClassOfCar());
				return pr;
			});
		}
	}
	private void findByStatusCar(){
		if(!form.getStatusCar().isEmpty()){
			filters.add((root, query, cb)->root.get("statusCar").in(form.getStatusCar()));
		}
	}

	@Override
	public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class && query.getResultType()!=Long.class){
			Fetch<Car, ModelOfCar> root1 = root.fetch("modelOfCar");
			root1.fetch("classOfCar");
			root.fetch("statusCar");
		}
		findByTypeClassOfCar();
		findByPrice();
//		findClassOfCarByTypeClassOfCar();
		findByTypeModelCar();
		findByTypeClassOfCarTwo();
//		findByModelOfCar();
		findByStatusCar();
		if(!filters.isEmpty()){
			Specifications<Car> spec = Specifications.where(filters.get(0));
			for(Specification<Car> s : filters.subList(1, filters.size())){
				spec = spec.and(s);
			}
			return spec.toPredicate(root, query, cb);
		}
		return null;
	}
//	private String search = "";
//	
//	public CarFilterAdapter(CarFilterForm form){
//		if(form.getSearch()!=null)
//		search = form.getSearch();
//	}
	
//	@Override
//	public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> query,
//			CriteriaBuilder cb) {
//		if(query.getResultType()!=Long.class && query.getResultType()!=Long.class){
//			Fetch<Car, ModelOfCar> root1 = root.fetch("modelOfCar");
//			root1.fetch("classOfCar");
//			root.fetch("statusCar");
//		}
//		Join<Car, ModelOfCar> join1 = root.join("modelOfCar");
//		Join<ModelOfCar, ClassOfCar> join2 = join1.join("classOfCar");
//		Expression<Integer> exp1 = join2.get("price");//Expression<String> exp = join2.get(String.valueOf("price"));
//		Expression<String> exp2 = join2.get("typeClassOfCar");
//		
//		Join<Car, StatusCar> join3 = root.join("statusCar");
//		if(search.isEmpty())return null;
//		
//		Predicate pr1 = cb.equal(exp1, Integer.parseInt(search));
//		Predicate pr2 = cb.like(cb.upper(exp2), search.toUpperCase()+"%");
//		Predicate prAll = cb.or(pr1, pr2);
//		return prAll;
////		return cb.like(cb.upper(exp), search.toUpperCase()+"%");
//	}

}
