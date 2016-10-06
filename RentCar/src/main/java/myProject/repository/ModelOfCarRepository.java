package myProject.repository;

import myProject.form.filter.ModelOfCarFilterForm;
import myProject.models.ModelOfCar;

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
public interface ModelOfCarRepository extends JpaRepository<ModelOfCar, Long>, JpaSpecificationExecutor<ModelOfCar> {
    @Query("SELECT mc FROM ModelOfCar mc LEFT JOIN FETCH mc.classOfCar cc WHERE cc.typeClassOfCar=:typeClassOfCar AND mc.typeModelCar=:typeModelCar")
    ModelOfCar findByClassOfCarAndTypeModelCar(@Param("typeClassOfCar")String typeClassOfCar, @Param("typeModelCar")String typeModelCar);

    @Query("SELECT mc FROM ModelOfCar mc LEFT JOIN FETCH mc.classOfCar cc")
    List<ModelOfCar> findAll();
    
    @Query("SELECT distinct mc.typeModelCar FROM ModelOfCar mc")
    List<String> findAllTypeModelCar();
    
    @Query(value="SELECT mc FROM ModelOfCar mc LEFT JOIN FETCH mc.classOfCar", 
    		countQuery="SELECT count(mc.id) FROM ModelOfCar mc")
    Page<ModelOfCar> findAll(Pageable pageable);
    
    @Query("SELECT mc FROM ModelOfCar mc LEFT JOIN FETCH mc.classOfCar cc WHERE mc.id=:id")
    ModelOfCar findById(@Param("id") long id);
    
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM ModelOfCar mc "
//    		+ "JOIN mc.classOfCar cc "
//    		+ "WHERE cc.typeClassOfCar=:typeClassOfCar AND mc.typeModelCar=:typeModelCar")
//    int delete(@Param("typeClassOfCar") TypeClassOfCar typeClassOfCar, 
//    		@Param("typeModelCar")TypeModelCar typeModelCar);
    
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM ModelOfCar mc JOIN FETCH mc.classOfCar cc WHERE mc.id=:id")
//    int delete(@Param("id") long id);
}
