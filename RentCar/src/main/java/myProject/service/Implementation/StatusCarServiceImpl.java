package myProject.service.Implementation;

import myProject.form.StatusCarForm;
import myProject.models.Status;
import myProject.models.StatusCar;
import myProject.repository.StatusCarRepository;
import myProject.service.StatusCarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vasj on 11.08.2016.
 */
@Service
public class StatusCarServiceImpl implements StatusCarService {
    @Autowired
    StatusCarRepository statusCarRepository;

    public void save(Status status) {
        StatusCar statusCar = new StatusCar();
        statusCar.setStatus(status);
        statusCarRepository.saveAndFlush(statusCar);
    }
    public void saveStatusCar(StatusCar statusCar) {
		statusCarRepository.saveAndFlush(statusCar);
	}
    @Override
	public void save(StatusCarForm statusCarForm) {
    	StatusCar statusCar = new StatusCar();
    	statusCar.setId(statusCarForm.getId());
    	statusCar.setStatus(statusCarForm.getStatus());
    	statusCarRepository.saveAndFlush(statusCar);
	}

    public StatusCar findByStatus(Status status) {
        return statusCarRepository.findByStatus(status);
    }

    public List<StatusCar> findAll() {
        return statusCarRepository.findAll();
    }
    public Page<StatusCar> findAll(Pageable pageable) {
		return statusCarRepository.findAll(pageable);
	}

    public void delete(Status status) {
        statusCarRepository.delete(statusCarRepository.findByStatus(status));
    }

    public void delete(long id) {
        statusCarRepository.delete(id);
    }

	public StatusCar findById(long id) {
		return statusCarRepository.findId(id);
	}
	@Override
	public StatusCarForm findForForm(long id) {
		StatusCar statusCar = statusCarRepository.findId(id);
		StatusCarForm statusCarForm = new StatusCarForm();
		statusCarForm.setId(statusCar.getId());
		statusCarForm.setStatus(statusCar.getStatus());
		return statusCarForm;
	}
}
