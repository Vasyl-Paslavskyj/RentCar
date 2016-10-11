package myProject.service.Implementation;


import myProject.form.CustomForm;
import myProject.form.CustomFormForUser;
import myProject.form.filter.CustomFilterForm;
import myProject.form.filter.CustomFilterFormForUser;
import myProject.form.filter.HistoryFilterFormForUser;
import myProject.models.*;
import myProject.repository.CarRepository;
import myProject.repository.CustomRepository;
import myProject.repository.UserRepository;
import myProject.service.CustomService;
import myProject.service.Implementation.specification.CustomFilterAdapter;
import myProject.service.Implementation.specification.CustomFilterForUserAdapter;
import myProject.service.Implementation.specification.HistoryFilterForUserAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Vasj on 18.08.2016.
 */
@Service
public class CustomServiceImpl implements CustomService{
    @Autowired
    CustomRepository customRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;
    
    public void saveNew(long userId, long carId, String dateTimeStartStr, 
    		City cityStart, String dateTimeFinishStr, City cityFinish, 
    		Integer rentalLength, Integer cost){
    	Custom custom = new Custom();
    	User user = userRepository.findById(userId);
    	Car car = carRepository.findById(carId);
    	custom.setUser(user);
    	custom.setCar(car);
    	LocalDateTime dateTimeStart = LocalDateTimeParser.localDateTimeParser(dateTimeStartStr);
    	custom.setDateTimeStart(dateTimeStart);
        custom.setCityStart(cityStart);
        LocalDateTime dateTimeFinish = LocalDateTimeParser.localDateTimeParser(dateTimeFinishStr);
        custom.setDateTimeFinish(dateTimeFinish);
        custom.setCityFinish(cityFinish);
        custom.setRentalLength(rentalLength);
        custom.setCost(cost);
        customRepository.saveAndFlush(custom);
    }
    public void save(long userId, long carId, LocalDateTime dateTimeStart,
			City cityStart, LocalDateTime dateTimeFinish, City cityFinish,
			Integer rentalLength, Integer cost) {
    	Custom custom = new Custom();
    	User user = userRepository.findById(userId);
    	Car car = carRepository.findById(carId);
    	custom.setUser(user);
    	custom.setCar(car);
    	custom.setDateTimeStart(dateTimeStart);
    	custom.setCityStart(cityStart);
    	custom.setDateTimeFinish(dateTimeFinish);
    	custom.setCityFinish(cityFinish);
    	custom.setRentalLength(rentalLength);
        custom.setCost(cost);
        customRepository.saveAndFlush(custom);
	}
    public void save(CustomForm customForm) {
    	Custom custom = new Custom();
		custom.setId(customForm.getId());
		custom.setUser(customForm.getUser());
		custom.setCar(customForm.getCar());
		custom.setDateTimeStart(LocalDateTimeParser.localDateTimeParser(customForm.getDateTimeStart()));
		custom.setCityStart(customForm.getCityStart());
		custom.setDateTimeFinish(LocalDateTimeParser.localDateTimeParser(customForm.getDateTimeFinish()));
		custom.setCityFinish(customForm.getCityFinish());
		custom.setRentalLength(Integer.parseInt(customForm.getRentalLength()));
		custom.setCost(Integer.parseInt(customForm.getCost()));
		customRepository.saveAndFlush(custom);
	}
    @Override
	public void save(CustomFormForUser customFormForUser, Principal principal) {
    	Custom custom = new Custom();
    	custom.setId(customFormForUser.getId());
    	String id = principal.getName();
    	long idUser = Long.valueOf(id);
    	custom.setUser(userRepository.findById(idUser));
    	custom.setCar(customFormForUser.getCar());
		custom.setDateTimeStart(LocalDateTimeParser.localDateTimeParser(customFormForUser.getDateTimeStart()));
		custom.setCityStart(customFormForUser.getCityStart());
		custom.setDateTimeFinish(LocalDateTimeParser.localDateTimeParser(customFormForUser.getDateTimeFinish()));
		custom.setCityFinish(customFormForUser.getCityFinish());
		custom.setRentalLength(Integer.parseInt(customFormForUser.getRentalLength()));
		custom.setCost(Integer.parseInt(customFormForUser.getCost()));
		customRepository.saveAndFlush(custom);
	}

    public List<Custom> findAll() {
        return customRepository.findAll();
    }
    @Override
	public List<Custom> findAllByPrincipal(Principal principal) {
    	String id = principal.getName();
    	long idUser = Long.valueOf(id);
		return customRepository.findAllByPrincipal(idUser);
	}
    
    public Page<Custom> findAll(Pageable pageable) {
		return customRepository.findAll(pageable);
	}
    @Override
	public Page<Custom> findAll(Pageable pageable, CustomFilterForm form) {
		return customRepository.findAll(new CustomFilterAdapter(form), pageable);
	}
    @Override
	public Page<Custom> findAll(Pageable pageable, CustomFilterFormForUser form) {
		return customRepository.findAll(new CustomFilterForUserAdapter(form), pageable);
	}
    @Override
	public Page<Custom> findAll(Pageable pageable, HistoryFilterFormForUser form) {
		return customRepository.findAll(new HistoryFilterForUserAdapter(form), pageable);
	}
//    @Override
//	public Page<Custom> findAll(Pageable pageable, CustomFilterFormForUser form, Principal principal) {
//		return customRepository.findAll(pageable, new CustomFilterForUserAdapter(form), principal.getName());
//	}
    
    public Custom findById(long id) {
		return customRepository.findById(id);
	}
    public CustomForm findForForm(long id) {
		Custom custom = customRepository.findById(id);
		CustomForm form = new CustomForm();
		form.setId(custom.getId());
		form.setUser(custom.getUser());
		form.setCar(custom.getCar());
		form.setDateTimeStart(LocalDateTimeParser.localDateTimeParser(custom.getDateTimeStart()));
		form.setCityStart(custom.getCityStart());
		form.setDateTimeFinish(LocalDateTimeParser.localDateTimeParser(custom.getDateTimeFinish()));
		form.setCityFinish(custom.getCityFinish());
		form.setRentalLength(String.valueOf(custom.getRentalLength()));
		form.setCost(String.valueOf(custom.getCost()));
		return form;
	}
    @Override
	public CustomFormForUser findForFormForUser(long id) {
    	Custom custom = customRepository.findById(id);
    	CustomFormForUser form = new CustomFormForUser();
    	form.setId(custom.getId());
		form.setCar(custom.getCar());
		form.setDateTimeStart(LocalDateTimeParser.localDateTimeParser(custom.getDateTimeStart()));
		form.setCityStart(custom.getCityStart());
		form.setDateTimeFinish(LocalDateTimeParser.localDateTimeParser(custom.getDateTimeFinish()));
		form.setCityFinish(custom.getCityFinish());
		form.setRentalLength(String.valueOf(custom.getRentalLength()));
		form.setCost(String.valueOf(custom.getCost()));
		return form;
	}

	public int findCountOrdersForUse(String username, String password) {
		return customRepository.findCountOrdersForUse(username, password);
	}

	public List<Custom> findByLoginAndPasswordAndCarRegistrationNamber(
			String login, String password, String registrationNamber) {
		return customRepository.findByLoginAndPasswordAndCarRegistrationNamber(login, password, registrationNamber);
	}

	public int findCostByLoginAndPassword(String login, String password) {
		return customRepository.findCostByLoginAndPassword(login, password);
	}
	public void delete(long id) {
		customRepository.delete(id);		
	}    
}
