package myProject.repository;

import myProject.models.Accident;
import myProject.models.Car;
import myProject.models.Status;

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
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.statusCar sc LEFT JOIN FETCH c.modelOfCar mc LEFT JOIN FETCH mc.classOfCar cc")
    List<Car> findAll();
    
    @Query(value="SELECT c FROM Car c LEFT JOIN FETCH c.statusCar sc LEFT JOIN FETCH c.modelOfCar mc LEFT JOIN FETCH mc.classOfCar cc", 
    		countQuery="SELECT count(c.id) FROM Car c")
    Page<Car> findAll(Pageable pageable);
    
    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.statusCar sc LEFT JOIN FETCH c.modelOfCar mc LEFT JOIN FETCH mc.classOfCar cc WHERE c.id=:id")
    Car findById(@Param("id") long id);

    @Query("SELECT c FROM Car c LEFT JOIN FETCH c.statusCar sc LEFT JOIN FETCH c.modelOfCar mc LEFT JOIN FETCH mc.classOfCar cc WHERE c.registrationNamber=:registrationNamber")
    Car findByRegistrationNamber(@Param("registrationNamber") String registrationNamber);

    @Query("SELECT c FROM Car c " +
            "LEFT JOIN FETCH c.statusCar sc " +
            "LEFT JOIN FETCH c.modelOfCar mc " +
            "LEFT JOIN FETCH mc.classOfCar cc " +
            "WHERE cc.typeClassOfCar=:typeClassOfCar " +
            "AND mc.typeModelCar=:typeModelCar " +
            "AND c.registrationNamber=:registrationNamber")
    Car findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(@Param("typeClassOfCar")String typeClassOfCar,
                                                                 @Param("typeModelCar")String typeModelCar,
                                                                 @Param("registrationNamber")String registrationNamber);

    @Query("SELECT c FROM Car c " +
            "LEFT JOIN FETCH c.statusCar sc " +
            "LEFT JOIN FETCH c.modelOfCar mc " +
            "LEFT JOIN FETCH mc.classOfCar cc " +
            "WHERE cc.price>:priceFirst AND cc.price<:priceSecond")
    List<Car> findAllByPrice(@Param("price")Integer priceFirst, @Param("price")Integer priceSecond);

    @Query("SELECT c FROM Car c " +
            "LEFT JOIN FETCH c.statusCar sc " +
            "LEFT JOIN FETCH c.modelOfCar mc " +
            "LEFT JOIN FETCH mc.classOfCar cc " +
            "WHERE cc.typeClassOfCar=:typeClassOfCar")
    List<Car> findAllByTypeClassOfCar(@Param("typeClassOfCar")String typeClassOfCar);

    @Query("SELECT c FROM Car c " +
            "LEFT JOIN FETCH c.statusCar sc " +
            "LEFT JOIN FETCH c.modelOfCar mc " +
            "LEFT JOIN FETCH mc.classOfCar cc " +
            "WHERE sc.status=:status")
    List<Car> findAllByStatus(@Param("status")Status status);

    @Modifying
    @Transactional
    @Query("DELETE FROM Car c " +
            "WHERE c.registrationNamber=:registrationNamber")
    void delete(@Param("registrationNamber")String registrationNamber);

    @Query("SELECT c FROM Car c " +
            "LEFT JOIN FETCH c.customSet cs " +
            "LEFT JOIN FETCH  cs.user u " +
            "WHERE u.login=:login AND u.password=:password")
    List<Car> findAllByLoginAndPassword(@Param("login") String login, @Param("password") String password);
    
    @Query("SELECT acc FROM Accident acc JOIN FETCH acc.customSet cu JOIN FETCH cu.user u JOIN FETCH cu.car c " +
            "WHERE c.id=:id")
    List<Accident> findAllAccidentsByCarId(@Param("id") long id);
}
