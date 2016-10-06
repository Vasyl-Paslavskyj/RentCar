package myProject.service;

import myProject.form.CustomForm;
import myProject.form.CustomFormForUser;
import myProject.form.filter.CustomFilterForm;
import myProject.form.filter.CustomFilterFormForUser;
import myProject.form.filter.HistoryFilterFormForUser;
import myProject.models.City;
import myProject.models.Custom;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * Created by Vasj on 18.08.2016.
 */
public interface CustomService {
    int findCountOrdersForUse(String login, String password);;

//    void save(String name, String passport,
//            TypeClassOfCar typeClassOfCar, TypeModelCar typeModelCar, String registrationNamber,
//            LocalDateTime dateTimeStart, City cityStart, LocalDateTime dateTimeFinish, City cityFinish,
//            Integer rentalLength, Integer cost);
    
    void saveNew(long userId, long carId, String dateTimeStartStr, 
    		City cityStart, String dateTimeFinishStr, City cityFinish, 
    		Integer rentalLength, Integer cost);
    void save(long userId, long carId, LocalDateTime dateTimeStart, 
    		City cityStart, LocalDateTime dateTimeFinish, City cityFinish, 
    		Integer rentalLength, Integer cost);
    void save(CustomForm customForm);
    void save(CustomFormForUser customFormForUser, Principal principal);

    List<Custom> findAll();
    List<Custom> findAllByPrincipal(Principal principal);
    Page<Custom> findAll(Pageable pageable);
    Page<Custom> findAll(Pageable pageable, CustomFilterForm form);
    Page<Custom> findAll(Pageable pageable, CustomFilterFormForUser form);
    Page<Custom> findAll(Pageable pageable, HistoryFilterFormForUser form);
//    Page<Custom> findAll(Pageable pageable, CustomFilterFormForUser form, Principal principal);
    
    Custom findById(long id);
    CustomForm findForForm(long id);
    CustomFormForUser findForFormForUser(long id);
    
//    int updateIsActiveById(long id);

    List<Custom> findByLoginAndPasswordAndCarRegistrationNamber(String login, String password, String registrationNamber);

//    Custom findCustomByDriavarNameAndPassportAndCarRegistrationNamber(String name, String passport, String registrationNamber);

    int findCostByLoginAndPassword(String login, String password);
    
    void delete(long id);
}
