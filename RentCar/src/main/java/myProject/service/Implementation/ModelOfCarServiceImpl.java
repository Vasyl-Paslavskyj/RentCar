package myProject.service.Implementation;

import myProject.form.ModelOfCarForm;
import myProject.form.filter.ModelOfCarFilterForm;
import myProject.models.ClassOfCar;
import myProject.models.ModelOfCar;
import myProject.repository.ClassOfCarRepository;
import myProject.repository.ModelOfCarRepository;
import myProject.service.FileWriter;
import myProject.service.FileWriter.Folder;
import myProject.service.ModelOfCarService;
import myProject.service.Implementation.specification.ModelOfCarFilterAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vasj on 13.08.2016.
 */
@Service
public class ModelOfCarServiceImpl implements ModelOfCarService {
    @Autowired
    ModelOfCarRepository modelOfCarRepository;
    @Autowired
    ClassOfCarRepository classOfCarRepository;
    @Autowired
    private FileWriter fileWriter;

    public void save(String typeClassOfCar, String typeModelCar) {
        ModelOfCar modelOfCar = new ModelOfCar();
        ClassOfCar classOfCar = classOfCarRepository.findByTypeClassOfCar(typeClassOfCar);
        modelOfCar.setClassOfCar(classOfCar);
        modelOfCar.setTypeModelCar(typeModelCar);
        modelOfCarRepository.saveAndFlush(modelOfCar);
    }
    public void saveNew(long classOfCarId, String typeModelCar) {
    	ModelOfCar modelOfCar = new ModelOfCar();
    	ClassOfCar classOfCar = classOfCarRepository.findById(classOfCarId);
    	modelOfCar.setClassOfCar(classOfCar);
        modelOfCar.setTypeModelCar(typeModelCar);
        modelOfCarRepository.saveAndFlush(modelOfCar);
	}
    public void saveModelOfCar(ModelOfCarForm modelOfCarForm) {
		ModelOfCar modelOfCar = new ModelOfCar();
		modelOfCar.setId(modelOfCarForm.getId());
		modelOfCar.setClassOfCar(modelOfCarForm.getClassOfCar());
		modelOfCar.setTypeModelCar(modelOfCarForm.getTypeModelCar());
		modelOfCar.setPath(modelOfCarForm.getPath());
		modelOfCar.setVersion(modelOfCarForm.getVersion());
		modelOfCarRepository.saveAndFlush(modelOfCar);
		String extension = fileWriter.write(Folder.MODELOFCAR, modelOfCarForm.getFile(), modelOfCar.getId());
		if(extension!=null){
			modelOfCar.setVersion(modelOfCarForm.getVersion()+1);
			modelOfCar.setPath(extension);
			modelOfCarRepository.save(modelOfCar);
		}
	}

    public List<ModelOfCar> findAll() {
        return modelOfCarRepository.findAll();
    }
    @Override
	public List<String> findAllTypeModelCar() {
		return modelOfCarRepository.findAllTypeModelCar();
	}
    public Page<ModelOfCar> findAll(Pageable pageable) {
		return modelOfCarRepository.findAll(pageable);
	}
    @Override
	public Page<ModelOfCar> findAll(Pageable pageable, ModelOfCarFilterForm form) {
		return modelOfCarRepository.findAll(new ModelOfCarFilterAdapter(form), pageable);
	}

    public ModelOfCar findByClassOfCarAndTypeModelCar(String typeClassOfCar, String typeModelCar) {
        return modelOfCarRepository.findByClassOfCarAndTypeModelCar(typeClassOfCar, typeModelCar);
    }

    public void delete(long id) {
        modelOfCarRepository.delete(id);
    }
	public ModelOfCar findById(long id) {
		return modelOfCarRepository.findById(id);
	}
	public ModelOfCarForm findForForm(long id) {
		ModelOfCar modelOfCar = modelOfCarRepository.findById(id);
		ModelOfCarForm form = new ModelOfCarForm();
		form.setId(modelOfCar.getId());
		form.setClassOfCar(modelOfCar.getClassOfCar());
		form.setTypeModelCar(modelOfCar.getTypeModelCar());
		form.setPath(modelOfCar.getPath());
		form.setVersion(modelOfCar.getVersion());
		return form;
	}
		
//	public void delete(TypeClassOfCar typeClassOfCar, TypeModelCar typeModelCar) {
//		modelOfCarRepository.delete(typeClassOfCar, typeModelCar);
//	}
}
