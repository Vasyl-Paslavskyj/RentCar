package myProject.repository;

import java.util.List;

import myProject.models.Status;
import myProject.models.StatusCar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vasj on 10.08.2016.
 */
public interface StatusCarRepository extends JpaRepository<StatusCar, Long>, JpaSpecificationExecutor<StatusCar> {
	@Query("SELECT s FROM StatusCar s WHERE s.status=:status")
	StatusCar findByStatus(@Param("status") Status status);
    
    @Query("SELECT s FROM StatusCar s WHERE s.id=:id")
    StatusCar findId(@Param("id") long id);
    
    @Query("SELECT s FROM StatusCar s")
    List<StatusCar> findAll();
    
    @Modifying
    @Transactional
    @Query("DELETE FROM StatusCar s WHERE s.id=:id")
    int delete(@Param("id") long id);

//    @Modifying - для делете і апдейт
//    @Query("SELECT s FROM StatusCar s WHERE s.status=:status")
//    StatusCar findByStatus(@Param("status") Status status);
}
