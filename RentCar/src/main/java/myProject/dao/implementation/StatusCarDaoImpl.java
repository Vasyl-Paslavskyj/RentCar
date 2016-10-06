package myProject.dao.implementation;

import myProject.dao.StatusCarDao;
import myProject.models.Status;
import myProject.models.StatusCar;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;

/**
 * Created by Vasj on 08.08.2016.
 */
public class StatusCarDaoImpl extends GenericDaoImpl<StatusCar, Long> implements StatusCarDao {
    public StatusCarDaoImpl(EntityManager em) {
        super(StatusCar.class, em);
    }

    public StatusCar findOneByStatus(Status status){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StatusCar> cq = cb.createQuery(StatusCar.class);
        Root<StatusCar> root = cq.from(StatusCar.class);
        cq.select(root);

        Expression<Status> exp = root.get("status");
        Predicate pred = cb.equal(exp, status);

        cq.where(pred);
        StatusCar statusCar = em.createQuery(cq).getSingleResult();
        return statusCar;
    }
}
