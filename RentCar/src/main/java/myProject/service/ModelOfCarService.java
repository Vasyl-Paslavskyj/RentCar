package myProject.service;

import myProject.form.ModelOfCarForm;
import myProject.form.filter.ModelOfCarFilterForm;
import myProject.models.ModelOfCar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Vasj on 13.08.2016.
 */
public interface ModelOfCarService {
    void save(String typeClassOfCar, String typeModelCar);
    
    void saveNew(long classOfCarId, String typeModelCar);
    
    void saveModelOfCar(ModelOfCarForm modelOfCarForm);

    List<ModelOfCar> findAll();
    List<String> findAllTypeModelCar();
    Page<ModelOfCar> findAll(Pageable pageable);
    Page<ModelOfCar> findAll(Pageable pageable, ModelOfCarFilterForm form);

    ModelOfCar findByClassOfCarAndTypeModelCar(String typeClassOfCar, String typeModelCar);

    ModelOfCar findById(long id);
    ModelOfCarForm findForForm(long id);
    
    void delete(long id);

}
