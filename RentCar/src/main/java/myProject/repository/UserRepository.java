package myProject.repository;

import myProject.models.Accident;
import myProject.models.User;
import myProject.service.Implementation.specification.UserFilterAdapter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vasj on 10.08.2016.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("SELECT u FROM User u WHERE u.login=:login AND u.password=:password")
	User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
    
    @Query("SELECT u FROM User u WHERE u.id=:id")
    User findById(@Param("id") long id);
    
    @Query("SELECT u FROM User u")
    List<User> findAll();
    
    @Query(value="SELECT u FROM User u", countQuery="SELECT count(u.id) FROM User u")
    Page<User> findAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.drivingExperience=:drivingExperience")
    List<User> findByDrivingExperience(@Param("drivingExperience") int drivingExperience);

    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM User u WHERE u.login=:login")
    User findByLogin(@Param("login") String login);
    
    @Query("SELECT u FROM User u WHERE u.fullName=:fullName")
    User findByFullName(@Param("fullName") String fullName);
    
    @Query("SELECT u FROM User u WHERE u.password=:password")
    User findByPassword(@Param("password") String password);
    
    @Query("SELECT u FROM User u WHERE u.verification=:verification")
    User findByVerification(@Param("verification") String verification);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.login=:login AND u.password=:password")
    void delete(@Param("login") String login, @Param("password") String password);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id=:id")
    void deleteById(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.drivingExperience=:drivingExperience WHERE u.login=:login AND u.password=:password")
    int updateUserByDrivingExperience(@Param("login") String login, @Param("password") String password, @Param("drivingExperience") int drivingExperience);

    @Query("SELECT acc FROM Accident acc JOIN FETCH acc.customSet cu JOIN FETCH cu.user u JOIN FETCH cu.car c " +
            "WHERE u.id=:id")
    List<Accident> findAllAccidentsByUserId(@Param("id") long id);
}
