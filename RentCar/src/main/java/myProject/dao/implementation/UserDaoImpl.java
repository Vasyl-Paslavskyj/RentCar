package myProject.dao.implementation;

import myProject.dao.UserDao;
import myProject.models.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {
    public UserDaoImpl(EntityManager em) {
        super(User.class, em);
    }

    public User findByNameAndPassport(String name, String passport){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);

        Expression<String> exp1 = root.get("name");
        Predicate pred1 = cb.equal(exp1, name);
        Expression<String> exp2 = root.get("passport");
        Predicate pred2 = cb.equal(exp2, passport);
        Predicate allPred = cb.and(pred1, pred2);

        cq.where(allPred);
        User user = em.createQuery(cq).getSingleResult();
        return user;
    }

    public List<User> findByCountOrders(Integer count_orders_First, Integer count_orders_Second){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);

        Expression<Integer> exCO = root.get("count_orders");
        Predicate between = cb.between(exCO, count_orders_First, count_orders_Second);
        cq.where(between);
        List<User> userList = em.createQuery(cq).getResultList();
        return userList;
    }

    public List<User> findByDrivingExperience(Integer driving_experience){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        Expression<Integer> expDE = root.get("driving_experience");
        Predicate equal = cb.equal(expDE, driving_experience);

        cq.where(equal);
        List<User> userList = em.createQuery(cq).getResultList();
        return  userList;
    }
}
