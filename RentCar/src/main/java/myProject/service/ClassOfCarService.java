package myProject.service;

import myProject.form.ClassOfCarForm;
import myProject.form.filter.ClassOfCarFilterForm;
import myProject.models.ClassOfCar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Vasj on 11.08.2016.
 */
public interface ClassOfCarService {
	
	void saveClassOfCar(ClassOfCar classOfCar);

    void save(String typeClassOfCar, int price);
    void save(ClassOfCarForm classOfCarForm);

    ClassOfCar findByTypeClassOfCar(String typeClassOfCar);
    
    ClassOfCar findById(long id);
    ClassOfCarForm findForForm(long id);

    public ClassOfCar findByPrice(int price);
    
    List<ClassOfCar> findAll();
    List<String> findAllTypeClassOfCar();
    Page<ClassOfCar> findAll(Pageable pageable);
    Page<ClassOfCar> findAll(Pageable pageable, ClassOfCarFilterForm form);

    void delete(String typeClassOfCar);

    void delete(long id);

    int updatePriceByTypeClassOfCar(String typeClassOfCar, int price);
}
