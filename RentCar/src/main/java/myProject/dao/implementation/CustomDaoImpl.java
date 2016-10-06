package myProject.dao.implementation;

import myProject.dao.CarDao;
import myProject.dao.CustomDao;
import myProject.dao.UserDao;
import myProject.models.Car;
import myProject.models.Custom;
import myProject.models.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public class CustomDaoImpl extends GenericDaoImpl<Custom, Long> implements CustomDao {
    public CustomDaoImpl(EntityManager em) {
        super(Custom.class, em);
    }

    private final UserDao USER_DAO = new UserDaoImpl(em);
    private final CarDao CAR_DAO = new CarDaoImpl(em);

    public Custom findByDriavarNameAndPassportAndCarRegistrationNamber(String name, String passport, String registrationNamber){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Custom> cq = cb.createQuery(Custom.class);
        Root<Custom> root = cq.from(Custom.class);
        cq.select(root);

        User user = USER_DAO.findByNameAndPassport(name, passport);
        Car car = CAR_DAO.findByRegistrationNamber(registrationNamber);

        Expression<User> exp1 = root.get("user");
        Predicate pred1 = cb.equal(exp1, user);
        Expression<Car> exp2 = root.get("car");
        Predicate pred2 = cb.equal(exp2, car);
        Predicate allPred = cb.and(pred1, pred2);

        cq.where(allPred);
        Custom custom = em.createQuery(cq).getSingleResult();
        return custom;
    }

    public List<Custom> findAllByDriavarNameAndPassportAndCarRegistrationNamber(String nameFirst, String passportFirst, String registrationNamberFirst,
                                                                                String nameSecond, String passportSecond, String registrationNamberSecond){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Custom> cq = cb.createQuery(Custom.class);
        Root<Custom> root = cq.from(Custom.class);
        cq.select(root);

        User userFirst = USER_DAO.findByNameAndPassport(nameFirst, passportFirst);
        Car carFirst = CAR_DAO.findByRegistrationNamber(registrationNamberFirst);
        User userSecond = USER_DAO.findByNameAndPassport(nameSecond, passportSecond);
        Car carSecond = CAR_DAO.findByRegistrationNamber(registrationNamberSecond);

        Expression<User> exp1 = root.get("user");
        Predicate pred1 = cb.equal(exp1, userFirst);
        Predicate pred2 = cb.equal(exp1, userSecond);
        Expression<Car> exp2 = root.get("car");
        Predicate pred3 = cb.equal(exp2, carFirst);
        Predicate pred4 = cb.equal(exp2, carSecond);
        Predicate predFirst = cb.and(pred1, pred3);
        Predicate predDecond = cb.and(pred2, pred4);
        Predicate allPred = cb.or(predFirst, predDecond);

        cq.where(allPred);
        List<Custom> customList = em.createQuery(cq).getResultList();
        return customList;
    }

    public Integer findCostByDriavarNameAndPpassport(String name, String passport){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Custom> root = cq.from(Custom.class);
        Expression<Integer> costExpression = root.get("cost");//Óæå
        cq.select(cb.sum(costExpression));

        User user = USER_DAO.findByNameAndPassport(name, passport);
        Expression<User> exp1 = root.get("user");
        Predicate pred = cb.equal(exp1, user);

        cq.where(pred);
        Integer allCost = (Integer) em.createQuery(cq).getSingleResult();
        return allCost;
    }
}
