package myProject.service;

import myProject.form.StatusCarForm;
import myProject.models.Status;
import myProject.models.StatusCar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Vasj on 11.08.2016.
 */
public interface StatusCarService {
    void save(Status status);
    
    void saveStatusCar(StatusCar statusCar);
    void save(StatusCarForm statusCarForm);

    StatusCar findByStatus(Status status);
    
    StatusCar findById(long id);
    StatusCarForm findForForm(long id);

    List<StatusCar> findAll();
    Page<StatusCar> findAll(Pageable pageable);

    void delete(Status status);

    void delete(long id);
}
