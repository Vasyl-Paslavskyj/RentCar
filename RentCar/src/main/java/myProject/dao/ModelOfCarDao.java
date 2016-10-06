package myProject.dao;

import myProject.models.ModelOfCar;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface ModelOfCarDao extends GenericDao<ModelOfCar, Long> {
    ModelOfCar findByTypeClassOfCarAndTypeModelCar(String typeClassOfCar, String typeModelCar);
}
