package myProject.service.Implementation;

import myProject.form.ClassOfCarForm;
import myProject.form.filter.ClassOfCarFilterForm;
import myProject.models.ClassOfCar;
import myProject.repository.ClassOfCarRepository;
import myProject.service.ClassOfCarService;
import myProject.service.Implementation.specification.ClassOfCarFilterAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vasj on 11.08.2016.
 */
@Service
public class ClassOfCarServiceImpl implements ClassOfCarService {
    @Autowired
    ClassOfCarRepository classOfCarRepository;

    public void save(String typeClassOfCar, int price) {
        ClassOfCar classOfCar = new ClassOfCar();
        classOfCar.setTypeClassOfCar(typeClassOfCar);
        classOfCar.setPrice(price);
        classOfCarRepository.saveAndFlush(classOfCar);
    }
    public void save(ClassOfCarForm form) {
    	ClassOfCar classOfCar = new ClassOfCar();
    	classOfCar.setId(form.getId());
    	classOfCar.setTypeClassOfCar(form.getTypeClassOfCar());
    	classOfCar.setPrice(Integer.parseInt(form.getPrice()));
    	classOfCarRepository.saveAndFlush(classOfCar);
	}
    public void saveClassOfCar(ClassOfCar classOfCar) {
		classOfCarRepository.saveAndFlush(classOfCar);		
	}

    public ClassOfCar findByTypeClassOfCar(String typeClassOfCar) {
        return classOfCarRepository.findByTypeClassOfCar(typeClassOfCar);
    }
    public ClassOfCar findByPrice(int price) {
		return classOfCarRepository.findByPrice(price);
	}

    public List<ClassOfCar> findAll() {
        return classOfCarRepository.findAll();
    }
    @Override
	public List<String> findAllTypeClassOfCar() {
		return classOfCarRepository.findAllTypeClassOfCar();
	}
    public Page<ClassOfCar> findAll(Pageable pageable) {
		return classOfCarRepository.findAll(pageable);
	}
    public Page<ClassOfCar> findAll(Pageable pageable, ClassOfCarFilterForm form) {
		return classOfCarRepository.findAll(new ClassOfCarFilterAdapter(form), pageable);
	}

    public void delete(String typeClassOfCar) {
        classOfCarRepository.deleteByTypeClassOfCar(typeClassOfCar);
    }

    public void delete(long id) {
        classOfCarRepository.deleteById(id);
    }

    public int updatePriceByTypeClassOfCar(String typeClassOfCar, int price) {
        return classOfCarRepository.updatePriceByTypeClassOfCar(typeClassOfCar, price);
    }

	public ClassOfCar findById(long id) {
		return classOfCarRepository.findById(id);
	}
	public ClassOfCarForm findForForm(long id) {
		ClassOfCar classOfCar = classOfCarRepository.findById(id);
		ClassOfCarForm form = new ClassOfCarForm();
		form.setId(classOfCar.getId());
		form.setTypeClassOfCar(classOfCar.getTypeClassOfCar());
		form.setPrice(String.valueOf(classOfCar.getPrice()));
		return form;
	}	
}
