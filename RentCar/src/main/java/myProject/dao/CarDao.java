package myProject.dao;

import myProject.models.Car;

import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface CarDao extends GenericDao<Car, Long> {
    Car findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(String typeClassOfCar, String typeModelCar, String registrationNamber);
    Car findByRegistrationNamber(String registrationNamber);
    List<Car> findAllByDriavarNameAndPassport(String name, String passport);
    List<Car> findAllByPrice(Integer priceFirst, Integer priceSecond);
    List<Car> findAllByTypeClassOfCar(String typeClassOfCar);
}
