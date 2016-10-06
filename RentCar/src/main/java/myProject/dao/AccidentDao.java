package myProject.dao;

import myProject.models.Accident;

import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface AccidentDao extends GenericDao<Accident, Long> {
    List<Accident> findAllByDriavarNameAndPassport(String name, String passport);
    List<Accident> findAllByRegistrationNambe(String registrationNamber);
}
