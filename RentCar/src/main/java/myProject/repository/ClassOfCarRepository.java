package myProject.repository;

import java.util.List;

import myProject.models.ClassOfCar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vasj on 10.08.2016.
 */
public interface ClassOfCarRepository extends JpaRepository<ClassOfCar, Long>, JpaSpecificationExecutor<ClassOfCar>{
//    ClassOfCar findByTypeClassOfCar(TypeClassOfCar typeClassOfCar);
    @Query("SELECT cc FROM ClassOfCar cc WHERE cc.typeClassOfCar=:typeClassOfCar")
    ClassOfCar findByTypeClassOfCar(@Param("typeClassOfCar") String typeClassOfCar);
    
    @Query("SELECT cc FROM ClassOfCar cc WHERE cc.price=:price")
    ClassOfCar findByPrice(@Param("price") int price);
    
    @Query("SELECT cc FROM ClassOfCar cc WHERE cc.id=:id")
    ClassOfCar findById(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ClassOfCar cc WHERE cc.typeClassOfCar=:typeClassOfCar")
    int deleteByTypeClassOfCar(@Param("typeClassOfCar") String typeClassOfCar);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM ClassOfCar cc WHERE cc.id=:id")
    int deleteById(@Param("id") long id);
    
    @Modifying
    @Transactional
    @Query("UPDATE ClassOfCar cc SET cc.price=:price WHERE cc.typeClassOfCar=:typeClassOfCar")
    int updatePriceByTypeClassOfCar(@Param("typeClassOfCar") String typeClassOfCar, @Param("price") Integer price);
    
    @Query("SELECT cc FROM ClassOfCar cc")
    List<ClassOfCar> findAll();
    
    @Query("SELECT distinct cc.typeClassOfCar FROM ClassOfCar cc")
    List<String> findAllTypeClassOfCar();
}
