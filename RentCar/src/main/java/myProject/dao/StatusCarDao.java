package myProject.dao;

import myProject.models.Status;
import myProject.models.StatusCar;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface StatusCarDao extends GenericDao<StatusCar, Long> {
    StatusCar findOneByStatus(Status status);
}
