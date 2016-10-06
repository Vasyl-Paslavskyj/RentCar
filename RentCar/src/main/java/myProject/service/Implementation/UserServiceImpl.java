package myProject.service.Implementation;

import myProject.form.UserForm;
import myProject.form.filter.UserFilterForm;
import myProject.models.Accident;
import myProject.models.Role;
import myProject.models.User;
import myProject.repository.UserRepository;
import myProject.service.MailSender;
import myProject.service.UserService;
import myProject.service.Implementation.specification.UserFilterAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

/**
 * Created by Vasj on 15.08.2016.
 */
//@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService{
	private static final String CONTENT = "Successfully registered. Please confirm your email";
	
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    MailSender mailSender;

	public void save(String login, String password, String fullName,
			int drivingExperience, String email) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setFullName(fullName);
		user.setDrivingExperience(drivingExperience);
		user.setEmail(email);
		userRepository.saveAndFlush(user);
	}
	public void save(UserForm form) {
		User user = new User();
		user.setId(form.getId());
		user.setLogin(form.getLogin());
		user.setPassword(encoder.encode(form.getPassword()));
		user.setFullName(form.getFullName());
		user.setDrivingExperience(Integer.parseInt(form.getDrivingExperience()));
		user.setEmail(form.getEmail());
		user.setRole(Role.ROLE_USER);
		
		UUID uuid = UUID.randomUUID();
		String verification = uuid.toString();
		user.setVerification(verification);
		mailSender.sendMail(CONTENT, form.getEmail(), "http://localhost:8080/verification/" + verification);
		userRepository.saveAndFlush(user);
	}
	public void saveAuthenticationUser(User user) {
		userRepository.saveAndFlush(user);
	}
	
	public void saveUser(User user) {
		user.setRole(Role.ROLE_USER);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.saveAndFlush(user);
	}
	@PostConstruct
	public void saveAdmin(){
		User user = userRepository.findOne(1L);
//		if(user==null){
			user = new User();
			user.setRole(Role.ROLE_ADMIN);
			user.setPassword(encoder.encode("admin"));
			user.setLogin("admin");
			user.setId(1L);
			user.setConfirmed(true);
			userRepository.save(user);
//		}
	}

	public User findByLoginAndPassword(String username, String password) {
		return userRepository.findByLoginAndPassword(username, password);
	}

	public User findById(long id) {
		return userRepository.findById(id);
	}
	public UserForm findForForm(long id) {
		User user = userRepository.findById(id);
		UserForm form = new UserForm();
		form.setId(user.getId());
		form.setLogin(user.getLogin());
		form.setPassword(user.getPassword());
		form.setFullName(user.getFullName());
		form.setDrivingExperience(String.valueOf(user.getDrivingExperience()));
		form.setEmail(user.getEmail());
		return form;
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	@Override
	public Page<User> findAll(Pageable pageable, UserFilterForm form) {
		return userRepository.findAll(new UserFilterAdapter(form), pageable);
	}

	public List<User> findByDrivingExperience(int driving_experience) {
		return userRepository.findByDrivingExperience(driving_experience);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
	public User findByFullName(String fullName) {
		return userRepository.findByFullName(fullName);
	}
	public User findByPassword(String password) {
		return userRepository.findByPassword(password);
	}
	@Override
	public User findByVerification(String verification) {
		return userRepository.findByVerification(verification);
	}

	public void delete(String login, String password) {
		userRepository.delete(login, password);		
	}

	public void delete(long id) {
		userRepository.deleteById(id);		
	}

	public int updateUserByDrivingExperience(String login, String password,
			int drivingExperience) {
		return userRepository.updateUserByDrivingExperience(login, password, drivingExperience);
	}

	public List<Accident> findAllAccidentsByUserId(long id) {
		return userRepository.findAllAccidentsByUserId(id);
	}
	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		if(Pattern.matches("^[0-9]{1,12}$", login)){
			return userRepository.findOne(Long.valueOf(login));
		}
		return userRepository.findByLogin(login);
	}
	
	public void setRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	
}
