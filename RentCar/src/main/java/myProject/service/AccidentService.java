package myProject.service;

import myProject.form.AccidentForm;
import myProject.form.filter.AccidentFilterForm;
import myProject.form.filter.AccidentFilterFormForUser;
import myProject.models.Accident;
import myProject.models.Custom;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * Created by Vasj on 10.08.2016.
 */
public interface AccidentService {
	
	 Accident findById(long id);
	 AccidentForm findForForm(long id);
	 
//    void saveIfOneCar(LocalDateTime dateTime, String damage, int wastage, String name, String passport, String registrationNamber);

//    void saveIfTwoCars(LocalDateTime dateTime, String damage, int wastage,
//                       String nameFirst, String passportFirst, String registrationNamberFirst,
//                       String nameSecond, String passportSecond, String registrationNamberSecond);
	
	void saveIfOneCar(String dateTimeString, String damage, int wastage, long customId);
	
	void saveIfOneCar(AccidentForm accidentForm);
	
	void saveIfTwoCars(String dateTimeString, String damage, int wastage, long customFirstId, long customSecondId);
	
	void saveIfTwoCars(Accident accident, long customFirstId, long customSecondId);

    List<Accident> findAll();
    List<Long> findAllByPrincipal(Principal principal);
    Page<Accident> findAll(Pageable pageable);
    Page<Accident> findAll(Pageable pageable, AccidentFilterForm form);
    Page<Accident> findAll(Pageable pageable, AccidentFilterFormForUser form);

    List<Accident> findAllAccidentsByLoginAndPassword(String login, String password);
    List<Accident> findAllAccidentsByRegistrationNambe(String registrationNamber);
    
    void deleteById(long id);
}
