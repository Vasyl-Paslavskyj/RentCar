package myProject.service;

import myProject.form.UserForm;
import myProject.form.filter.UserFilterForm;
import myProject.models.Accident;
import myProject.models.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

/**
 * Created by Vasj on 14.08.2016.
 */
public interface UserService {
    void save(String login, String password, String fullName, int drivingExperience, String email);
    void saveUser(User user);
    void save(UserForm form);
    void saveAuthenticationUser(User user);

    User findByLoginAndPassword(String login, String password);
    User findById(long id);
    UserForm findForForm(long id);
    
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Page<User> findAll(Pageable pageable, UserFilterForm form);
    List<User> findByDrivingExperience(int drivingExperience);
    User findByEmail(String email);
    User findByLogin(String login);
    User findByFullName(String fullName);
    User findByPassword(String password);
    
    User findByVerification(String verification);

    void delete(String login, String password);

    void delete(long id);

    int updateUserByDrivingExperience(String login, String password, int drivingExperience);
    
    List<Accident> findAllAccidentsByUserId(long id);
    
}
