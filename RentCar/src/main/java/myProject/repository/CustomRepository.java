package myProject.repository;

import myProject.form.filter.CustomFilterFormForUser;
import myProject.models.Custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
public interface CustomRepository extends JpaRepository<Custom, Long>, JpaSpecificationExecutor<Custom> {
    @Query("SELECT SUM (cu.id) FROM Custom cu JOIN cu.user u WHERE u.login=:login AND u.password=:password")
    int findCountOrdersForUse(@Param("login") String login, @Param("password") String password);

    @Query("SELECT cu FROM Custom cu JOIN FETCH cu.user u JOIN FETCH cu.car c JOIN FETCH c.statusCar sc JOIN FETCH c.modelOfCar mc JOIN FETCH mc.classOfCar cc")
    List<Custom> findAll();
    
    @Query("SELECT cu FROM Custom cu JOIN FETCH cu.accidentSet acc JOIN FETCH cu.user u JOIN FETCH cu.car c JOIN FETCH c.statusCar sc JOIN FETCH c.modelOfCar mc JOIN FETCH mc.classOfCar cc")
    List<Custom> findAllWithAccident();
    
    @Query("SELECT cu FROM Custom cu JOIN FETCH cu.user u JOIN FETCH cu.car c JOIN FETCH c.statusCar sc JOIN FETCH c.modelOfCar mc JOIN FETCH mc.classOfCar cc WHERE u.id=:id")
    List<Custom> findAllByPrincipal(@Param("id") long id);
    
    @Query(value="SELECT cu FROM Custom cu LEFT JOIN FETCH cu.user u LEFT JOIN FETCH cu.car c LEFT JOIN FETCH c.statusCar sc LEFT JOIN FETCH c.modelOfCar mc LEFT JOIN FETCH mc.classOfCar cc", 
    		countQuery="SELECT count(cu.id) FROM Custom cu")
    Page<Custom> findAll(Pageable pageable);
    
//    @Query(value="SELECT cu FROM Custom cu LEFT JOIN FETCH cu.user u LEFT JOIN FETCH cu.car c LEFT JOIN FETCH c.statusCar sc LEFT JOIN FETCH c.modelOfCar mc LEFT JOIN FETCH mc.classOfCar cc WHERE u.login=:login", 
//    		countQuery="SELECT count(cu.id) FROM Custom cu LEFT JOIN cu.user u WHERE u.login=:login")
//    Page<Custom> findAll(Pageable pageable, Specification<Custom> spec, @Param("login") String login);
    
    @Query("SELECT cu FROM Custom cu JOIN FETCH cu.user u JOIN FETCH cu.car c WHERE cu.id=:id")
    Custom findById(@Param("id") long id);

    @Query("SELECT cu FROM Custom cu JOIN FETCH cu.user u JOIN FETCH cu.car c " +
            "WHERE u.login=:login AND u.password=:password AND c.registrationNamber=:registrationNamber")
    List<Custom> findByLoginAndPasswordAndCarRegistrationNamber(@Param("login") String login, 
    		@Param("password") String password, 
    		@Param("registrationNamber") String registrationNamber);

    @Query("SELECT cu FROM Custom cu JOIN FETCH cu.user u JOIN FETCH cu.car c " +
            "WHERE u.login=:login AND u.password=:password AND c.registrationNamber=:registrationNamber")
    Custom findCustomByLoginAndPasswordAndCarRegistrationNamber(@Param("login") String login, 
    		@Param("password") String password, 
    		@Param("registrationNamber") String registrationNamber);

    @Query("SELECT cu FROM Custom cu JOIN FETCH cu.user u JOIN FETCH cu.car c " +
            "WHERE (u.login=:loginFirst AND u.password=:passwordFirst AND c.registrationNamber=:registrationNamberFirst) " +
            "OR (u.login=:loginSecond AND u.password=:passwordSecond AND c.registrationNamber=:registrationNamberSecond)")
    List<Custom> findListCustomByLoginAndPasswordAndCarRegistrationNamber(@Param("login") String loginFirst,
                                                                                @Param("password") String passwordFirst,
                                                                                @Param("registrationNamber") String registrationNamberFirst,
                                                                                @Param("login") String loginSecond,
                                                                                @Param("password") String passwordSecond,
                                                                                @Param("registrationNamber") String registrationNamberSecond);

    @Query("SELECT SUM (cu.cost) FROM Custom cu JOIN cu.user u WHERE u.login=:login AND u.password=:password")
    int findCostByLoginAndPassword(@Param("login") String login, @Param("password") String password);

//    @Modifying
//    @Transactional
//    @Query("UPDATE Custom cu SET cu.isActive=:isActive WHERE cu.id=:id")
//    int updateIsActiveById(@Param("id") long id, @Param("isActive") Boolean isActive);
    
}
