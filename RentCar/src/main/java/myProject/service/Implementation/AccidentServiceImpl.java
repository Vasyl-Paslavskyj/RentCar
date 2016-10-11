package myProject.service.Implementation;

import myProject.form.AccidentForm;
import myProject.form.filter.AccidentFilterForm;
import myProject.form.filter.AccidentFilterFormForUser;
import myProject.models.Accident;
import myProject.models.Custom;
import myProject.repository.AccidentRepository;
import myProject.repository.CustomRepository;
import myProject.service.AccidentService;
import myProject.service.Implementation.specification.AccidentFilterAdapter;
import myProject.service.Implementation.specification.AccidentFilterForUserAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vasj on 10.08.2016.
 */
@Service
public class AccidentServiceImpl implements AccidentService {
    @Autowired
    AccidentRepository accidentRepository;
    @Autowired
    CustomRepository customRepository;


    public Accident findById(long id) {
		return accidentRepository.findById(id);
	}
    
    @Transactional
    public AccidentForm findForForm(long id) {
		Accident accident = accidentRepository.findById(id);
		AccidentForm accidentForm = new AccidentForm();
		accidentForm.setId(accident.getId());
		accidentForm.setDateTime(LocalDateTimeParser.localDateTimeParser(accident.getDateTime()));//accident.getDateTime().format(DateTimeFormatter.ISO_DATE_TIME
		accidentForm.setDamage(accident.getDamage());
		accidentForm.setWastage(String.valueOf(accident.getWastage()));
		for(Custom custom : accident.getCustomSet()){
			accidentForm.getCustomSet().add(custom);
		}
		return accidentForm;
	}

    public void saveIfOneCar(String dateTimeString, String damage, int wastage, long customId) {
    	Accident accident = new Accident();
    	LocalDateTime dateTime = LocalDateTimeParser.localDateTimeParser(dateTimeString);
    	accident.setDateTime(dateTime);
    	accident.setDamage(damage);
    	accident.setWastage(wastage);
    	Custom custom = customRepository.findById(customId);
    	accident.getCustomSet().add(custom);
    	accidentRepository.saveAndFlush(accident);
	}
    
    @Transactional
    public void saveIfOneCar(AccidentForm accidentForm) {
    	Accident accident = new Accident();
    	accident.setId(accidentForm.getId());
    	accident.setDateTime(LocalDateTimeParser.localDateTimeParser(accidentForm.getDateTime()));//LocalDateTime.parse(accidentForm.getDateTime())
    	accident.setDamage(accidentForm.getDamage());
    	accident.setWastage(Integer.parseInt(accidentForm.getWastage()));
    	for(Custom custom : accidentForm.getCustomSet()){
    		accident.getCustomSet().add(custom);
    	}
    	accidentRepository.saveAndFlush(accident);		
	}
    
    public void saveIfTwoCars(String dateTimeString, String damage, int wastage, 
    		long customFirstId, long customSecondId) {
    	Accident accident = new Accident();
    	LocalDateTime dateTime = LocalDateTimeParser.localDateTimeParser(dateTimeString);
    	accident.setDateTime(dateTime);
    	accident.setDamage(damage);
    	accident.setWastage(wastage);
    	Custom customFirst = customRepository.findById(customFirstId);
    	Custom customSecond = customRepository.findById(customSecondId);
    	List<Custom> customList = new ArrayList<Custom>();
    	customList.addAll(Arrays.asList(customFirst, customSecond));
    	accident.getCustomSet().addAll(customList);
    	accidentRepository.saveAndFlush(accident);
	}
    public void saveIfTwoCars(Accident accident, long customFirstId, long customSecondId){
    	Custom customFirst = customRepository.findById(customFirstId);
    	Custom customSecond = customRepository.findById(customSecondId);
    	List<Custom> customList = new ArrayList<Custom>();
    	customList.addAll(Arrays.asList(customFirst, customSecond));
    	accident.getCustomSet().addAll(customList);
    	accidentRepository.saveAndFlush(accident);
    }
    
    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }
    @Override
	public List<Long> findAllByPrincipal(Principal principal) {
    	String id = principal.getName();
    	long idUser = Long.valueOf(id);
		return accidentRepository.findAllByUserId(idUser);
	}
    public Page<Accident> findAll(Pageable pageable) {
		return accidentRepository.findAll(pageable);
	}
    @Override
	public Page<Accident> findAll(Pageable pageable, AccidentFilterForm form) {
		return accidentRepository.findAll(new AccidentFilterAdapter(form), pageable);
	}
    @Override
	public Page<Accident> findAll(Pageable pageable, AccidentFilterFormForUser form) {
		return accidentRepository.findAll(new AccidentFilterForUserAdapter(form), pageable);
	}

    public List<Accident> findAllAccidentsByRegistrationNambe(String registrationNamber) {
        return accidentRepository.findAllAccidentsByRegistrationNambe(registrationNamber);
    }
	public List<Accident> findAllAccidentsByLoginAndPassword(
			String login, String password) {
		return accidentRepository.findAllAccidentsByLoginAndPassword(login, password);
	}
	public void deleteById(long id) {
		accidentRepository.deleteById(id);
	}

}
