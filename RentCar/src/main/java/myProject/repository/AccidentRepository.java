package myProject.repository;

import myProject.models.Accident;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Vasj on 10.08.2016.
 */
public interface AccidentRepository extends JpaRepository<Accident, Long>, JpaSpecificationExecutor<Accident> {

    @Query("SELECT distinct acc FROM Accident acc JOIN FETCH acc.customSet cu JOIN FETCH cu.user u JOIN FETCH cu.car c")
    List<Accident> findAll();
    
    @Query("SELECT distinct acc.id FROM Accident acc JOIN acc.customSet cu JOIN cu.user u JOIN cu.car c WHERE u.id=:id")
    List<Long> findAllByUserId(@Param("id") long id);
    
    @Query(value="SELECT distinct acc FROM Accident acc LEFT JOIN FETCH acc.customSet cu LEFT JOIN FETCH cu.user u LEFT JOIN FETCH cu.car c", 
    		countQuery="SELECT count(acc) FROM Accident acc")
    Page<Accident> findAll(Pageable pageable);
    
    @Query("SELECT acc FROM Accident acc JOIN FETCH acc.customSet cu JOIN FETCH cu.user u JOIN FETCH cu.car c "
    		+ "WHERE acc.id=:id")
    Accident findById(@Param("id") long id);

    @Query("SELECT acc FROM Accident acc JOIN FETCH acc.customSet cu JOIN FETCH cu.user u JOIN FETCH cu.car c " +
            "WHERE u.login=:login AND u.password=:password")
    List<Accident> findAllAccidentsByLoginAndPassword(@Param("login") String login, @Param("password") String password);

    @Query("SELECT acc FROM Accident acc JOIN FETCH acc.customSet cu JOIN FETCH cu.user u JOIN FETCH cu.car c " +
            "WHERE c.registrationNamber=:registrationNamber")
    List<Accident> findAllAccidentsByRegistrationNambe(@Param("registrationNamber") String registrationNamber);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Accident acc WHERE acc.id=:id")
    void deleteById(@Param("id") long id);
    
}
