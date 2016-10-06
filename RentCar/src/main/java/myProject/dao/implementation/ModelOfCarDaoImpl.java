package myProject.dao.implementation;

import myProject.dao.ModelOfCarDao;
import myProject.models.ClassOfCar;
import myProject.models.ModelOfCar;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;


/**
 * Created by Vasj on 08.08.2016.
 */
public class ModelOfCarDaoImpl extends GenericDaoImpl<ModelOfCar, Long> implements ModelOfCarDao {
    public ModelOfCarDaoImpl(EntityManager em) {
        super(ModelOfCar.class, em);
    }

//    modelOfCar = em.createQuery("SELECT distinct mc FROM ModelOfCar mc JOIN fetch mc.classOfCar cc " +
//            "WHERE cc.typeClassOfCar=:typeClassOfCar AND mc.typeModelCar=:typeModelCar", ModelOfCar.class)
//            .setParameter("typeClassOfCar", typeClassOfCar)
//            .setParameter("typeModelCar", typeModelCar)
//            .getSingleResult();
    public ModelOfCar findByTypeClassOfCarAndTypeModelCar(String typeClassOfCar, String typeModelCar){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ModelOfCar> cq = cb.createQuery(ModelOfCar.class);
        Root<ModelOfCar> root = cq.from(ModelOfCar.class);
        cq.select(root);

        Join<ModelOfCar, ClassOfCar> join = root.join("classOfCar");
        Expression<String> typeClassOfCarExpression = join.get("typeClassOfCar");
        Predicate equal1 = cb.equal(typeClassOfCarExpression, typeClassOfCar);

        Expression<String> typeModelCarExpression = root.get("typeModelCar");
        Predicate equal2 = cb.equal(typeModelCarExpression, typeModelCar);

        Predicate allPred = cb.and(equal1, equal2);
        cq.where(allPred);
        ModelOfCar modelOfCar = em.createQuery(cq).getSingleResult();
        return modelOfCar;
    }

}
