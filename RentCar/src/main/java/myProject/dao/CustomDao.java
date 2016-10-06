package myProject.dao;

import myProject.models.Custom;

import java.util.List;

/**
 * Created by Vasj on 08.08.2016.
 */
public interface CustomDao extends GenericDao<Custom, Long> {
    Custom findByDriavarNameAndPassportAndCarRegistrationNamber(String name, String passport, String registrationNamber);
    List<Custom> findAllByDriavarNameAndPassportAndCarRegistrationNamber(String nameFirst, String passportFirst, String registrationNamberFirst,
                                                                         String nameSecond, String passportSecond, String registrationNamberSecond);
    Integer findCostByDriavarNameAndPpassport(String name, String passport);
}
