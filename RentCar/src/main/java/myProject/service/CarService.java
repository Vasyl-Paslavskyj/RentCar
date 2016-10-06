package myProject.service;

import myProject.form.CarForm;
import myProject.form.filter.CarFilterForm;
import myProject.models.Accident;
import myProject.models.Car;
import myProject.models.Status;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * Created by Vasj on 14.08.2016.
 */
public interface CarService {
    void save(String typeClassOfCar, String typeModelCar, Status status, String registrationNamber);
    
    void saveNew(long modelOfCarId, long statusCarId, String registrationNamber);
    void save(CarForm carForm);
    
    void saveCar(Car car);

    List<Car> findAll();
    Page<Car> findAll(Pageable pageable);
    Page<Car> findAll(Pageable pageable, CarFilterForm filter);
    
    Car findById(long id);
    CarForm findForForm(long id);

    Car findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(String typeClassOfCar, String typeModelCar, String registrationNamber);

    Car findByRegistrationNamber(String registrationNamber);

    List<Car> findAllByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    List<Car> findAllByPrice(Integer priceFirst, Integer priceSecond);

    List<Car> findAllByTypeClassOfCar(String typeClassOfCar);

    void delete(String registrationNamber);
    void delete(long id);

    List<Car> findAllByStatus(Status status);
    
    List<Accident> findAllAccidentsByCarId(long id);
}
