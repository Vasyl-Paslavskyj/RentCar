package myProject.dao;

import myProject.models.User;

import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface UserDao extends GenericDao<User, Long> {
    User findByNameAndPassport(String name, String passport);
    List<User> findByCountOrders(Integer count_orders_First, Integer count_orders_Second);
    List<User> findByDrivingExperience(Integer driving_experience);
}
