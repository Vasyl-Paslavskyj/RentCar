package myProject.dao.implementation;

import myProject.dao.AccidentDao;
import myProject.dao.CarDao;
import myProject.dao.UserDao;
import myProject.models.Accident;
import myProject.models.Car;
import myProject.models.Custom;
import myProject.models.User;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public class AccidentDaoImpl extends GenericDaoImpl<Accident, Long> implements AccidentDao{
    public AccidentDaoImpl(EntityManager em) {
        super(Accident.class, em);
    }
    private final UserDao USER_DAO = new UserDaoImpl(em);
    private final CarDao CAR_DAO = new CarDaoImpl(em);

    public List<Accident> findAllByDriavarNameAndPassport(String name, String passport){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Accident> cq = cb.createQuery(Accident.class);
        Root<Accident> root = cq.from(Accident.class);
        cq.select(root);

        User user = USER_DAO.findByNameAndPassport(name, passport);
        Join<Accident, Custom> accidentCustomJoin = root.join("customSet");
        Join<Custom, User> customUserJoin = accidentCustomJoin.join("user");
        //Expression<User> exp = accidentCustomJoin.getClass("user");
        Predicate pred = cb.equal(customUserJoin, user);

        cq.where(pred);
        List<Accident> customList = em.createQuery(cq).getResultList();
        return customList;
    }

    public List<Accident> findAllByRegistrationNambe(String registrationNamber){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Accident> cq = cb.createQuery(Accident.class);
        Root<Accident> root = cq.from(Accident.class);
        cq.select(root);

        Car car = CAR_DAO.findByRegistrationNamber(registrationNamber);
        Join<Accident, Custom> accidentCustomJoin = root.join("customSet");
        Join<Custom, Car> customCarJoin = accidentCustomJoin.join("car");
        Predicate pred = cb.equal(customCarJoin, car);

        cq.where(pred);
        List<Accident> customList = em.createQuery(cq).getResultList();
        return customList;
    }
}
