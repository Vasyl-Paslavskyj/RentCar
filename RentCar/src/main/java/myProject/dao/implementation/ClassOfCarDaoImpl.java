package myProject.dao.implementation;

import myProject.dao.ClassOfCarDao;
import myProject.models.ClassOfCar;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.*;

/**
 * Created by Vasj on 08.08.2016.
 */
public class ClassOfCarDaoImpl extends GenericDaoImpl<ClassOfCar, Long> implements ClassOfCarDao {

    public ClassOfCarDaoImpl(EntityManager em) {
        super(ClassOfCar.class, em);
    }


    public ClassOfCar findOneByTypeClassOfCar(String typeClassOfCar) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ClassOfCar> cq = cb.createQuery(ClassOfCar.class);
        Root<ClassOfCar> root = cq.from(ClassOfCar.class);
        cq.select(root);

        Expression<String> exp1 = root.get("typeClassOfCar");
        Predicate pred1 = cb.equal(exp1, typeClassOfCar);
        cq.where(pred1);

        ClassOfCar classOfCar = em.createQuery(cq).getSingleResult();
        return classOfCar;
    }
}
