package myProject.dao.implementation;

import myProject.dao.CarDao;
import myProject.dao.ClassOfCarDao;
import myProject.dao.ModelOfCarDao;
import myProject.dao.UserDao;
import myProject.models.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public class CarDaoImpl extends GenericDaoImpl<Car, Long> implements CarDao{

    public CarDaoImpl(EntityManager em) {
        super(Car.class, em);
    }

    private final ModelOfCarDao MODEL_OF_CAR_DAO = new ModelOfCarDaoImpl(em);
    private final UserDao USER_DAO = new UserDaoImpl(em);

    public Car findByTypeClassOfCarAndTypeModelCarAndRegistrationNamber(String typeClassOfCar, String typeModelCar, String registrationNamber){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);

        ModelOfCar modelOfCar = MODEL_OF_CAR_DAO.findByTypeClassOfCarAndTypeModelCar(typeClassOfCar, typeModelCar);

        Expression<String> exp1 = root.get("modelOfCar");
        Predicate pred1 = cb.equal(exp1, modelOfCar);
        Expression<String> exp2 = root.get("registrationNamber");
        Predicate pred2 = cb.equal(exp2, registrationNamber);

        Predicate allPred = cb.and(pred1, pred2);
        cq.where(allPred);
        Car car = em.createQuery(cq).getSingleResult();
        return car;
    }

    public Car findByRegistrationNamber(String registrationNamber){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);
        Expression<String> exp2 = root.get("registrationNamber");
        Predicate pred2 = cb.equal(exp2, registrationNamber);
        cq.where(pred2);
        Car car = em.createQuery(cq).getSingleResult();
        return car;
    }

    public List<Car> findAllByDriavarNameAndPassport(String name, String passport){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);

        Join<Car, Custom> carCustomJoin = root.join("customSet");
        Join<Custom, User> customUserJoin = carCustomJoin.join("user");
        User user = USER_DAO.findByNameAndPassport(name, passport);
        Predicate pred = cb.equal(customUserJoin, user);

        cq.where(pred);
        List<Car> carList = em.createQuery(cq).getResultList();
        return carList;
    }

    public List<Car> findAllByPrice(Integer priceFirst, Integer priceSecond){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);
        Join<Car, ModelOfCar> modelOfCarJoin = root.join("modelOfCar");
        Join<ModelOfCar, ClassOfCar> classOfCarJoin = modelOfCarJoin.join("classOfCar");

        Expression<Integer> priceExpression = classOfCarJoin.get("price");
        Predicate priceBetweenPredicate = cb.between(priceExpression, priceFirst, priceSecond);
        cq.where(priceBetweenPredicate);

        List<Car> carList = em.createQuery(cq).getResultList();
        return carList;
    }

    public List<Car> findAllByTypeClassOfCar(String typeClassOfCar){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> root = cq.from(Car.class);
        cq.select(root);
        Join<Car, ModelOfCar> modelOfCarJoin = root.join("modelOfCar");
        Join<ModelOfCar, ClassOfCar> classOfCarJoin = modelOfCarJoin.join("classOfCar");

        Expression<String> typeClassOfCarExpression = classOfCarJoin.get("typeClassOfCar");
        Predicate equal = cb.equal(typeClassOfCarExpression, typeClassOfCar);
        cq.where(equal);
        List<Car> carList = em.createQuery(cq).getResultList();
        return carList;
    }
}
