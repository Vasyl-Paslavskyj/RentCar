package myProject.dao;

import myProject.models.ClassOfCar;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface ClassOfCarDao extends GenericDao<ClassOfCar, Long> {
    ClassOfCar findOneByTypeClassOfCar (String typeClassOfCar);
}
