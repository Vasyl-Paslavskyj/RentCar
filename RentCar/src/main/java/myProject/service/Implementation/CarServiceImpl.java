package myProject.service.Implementation;

import myProject.form.CarForm;
import myProject.form.filter.CarFilterForm;
import myProject.models.*;
import myProject.repository.CarRepository;
import myProject.repository.ModelOfCarRepository;
import myProject.repository.StatusCarRepository;
import myProject.service.CarService;
import myProject.service.Implementation.specification.CarFilterAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vasj on 14.08.2016.
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    ModelOfCarRepository modelOfCarRepository;
    @Autowired
    StatusCarRepository statusCarRepository;

    public void save(String typeClassOfCar, String typeModelCar, Status status, String registrationNamber) {
        Car car = new Car();
        ModelOfCar modelOfCar = modelOfCarRepository.findByClassOfCarAndTypeModelCar(typeClassOfCar, typeModelCar);
        StatusCar statusCar = statusCarRepository.findByStatus(status);
        car.setModelOfCar(modelOfCar);
        car.setStatusCar(statusCar);
        car.setRegistrationNamber(registrationNamber);
        carRepository.saveAndFlush(car);
    }
    public void saveNew(long modelOfCarId, long statusCarId, String registrationNamber) {
    	Car car = new Car();
    	ModelOfCar modelOfCar = modelOfCarRepository.findById(modelOfCarId);
    	StatusCar statusCar = statusCarRepository.findId(statusCarId);
    	car.setModelOfCar(modelOfCar);
    	car.setStatusCar(statusCar);
    	car.setRegistrationNamber(registrationNamber);
    	carRepository.saveAndFlush(car);
	}
    public void save(CarForm carForm) {
    	Car car = new Car();
    	car.setId(carForm.getId());
		car.setModelOfCar(carForm.getModelOfCar());
		car.setStatusCar(carForm.getStatusCar());
		car.setRegistrationNamber(carForm.getRegistrationNamber());
		carRepository.saveAndFlush(car);
	}
    
    public void saveCar(Car car) {
		carRepository.saveAndFlush(car);		
	}

    public List<Car> findAll() {
        return carRepository.findAll();
    }
    public Page<Car> findAll(Pageable pageable) {
		return carRepository.findAll(pageable);
	}
    @Override
	public Page<Car> findAll(Pageable pageable, CarFilterForm form) {
		return carRepository.findAll(new CarFilterAdapter(form), pageable);
	}
    
    public Car findById(long id) {
		return carRepository.findById(id);
	}
    public CarForm findForForm(long id) {
		Car car = carRepository.findById(id);
		CarForm form = new CarForm();
		form.setId(car.getId());
		form.setModelOfCar(car.getModelOfCar());
		form.setStatusCar(car.getStatusCar());
		form.setRegistrationNamber(car.getRegistrationNamber());
		return form;
	}

    public Car findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(String typeClassOfCar, String typeModelCar, String registrationNamber) {
        return carRepository.findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(typeClassOfCar, typeModelCar, registrationNamber);
    }

    public Car findByRegistrationNamber(String registrationNamber) {
        return carRepository.findByRegistrationNamber(registrationNamber);
    }

    public List<Car> findAllByPrice(Integer priceFirst, Integer priceSecond) {
        return carRepository.findAllByPrice(priceFirst, priceSecond);
    }

    public List<Car> findAllByTypeClassOfCar(String typeClassOfCar) {
        return carRepository.findAllByTypeClassOfCar(typeClassOfCar);
    }

    public void delete(String registrationNamber) {
        carRepository.delete(registrationNamber);
    }
    public void delete(long id) {
    	carRepository.delete(id);
	}

    public List<Car> findAllByStatus(Status status) {
        return carRepository.findAllByStatus(status);
    }
    
	public List<Accident> findAllAccidentsByCarId(long id) {
		return carRepository.findAllAccidentsByCarId(id);
	}
	public List<Car> findAllByLoginAndPassword(String login,
			String password) {
		return carRepository.findAllByLoginAndPassword(login, password);
	}
}
