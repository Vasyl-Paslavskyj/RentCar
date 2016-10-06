package myProject.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vasj on 27.07.2016.
 */
@Entity
@Table(name = "car", indexes={@Index(name="REGISTRATION_NUMBER_INDEX", columnList="registrationNamber")})
public class Car implements Serializable{

    private static final long serialVersionUID = 1130699935440507056L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ModelOfCar modelOfCar;

    @ManyToOne(fetch = FetchType.LAZY)
    private StatusCar statusCar;

    private String registrationNamber;

    @OneToMany(mappedBy = "car")
    private Set<Custom> customSet = new HashSet<Custom>();

    public Car(){
    }
    public Car(ModelOfCar modelOfCar, StatusCar statusCar, String registrationNamber){
        this.modelOfCar = modelOfCar;
        this.statusCar = statusCar;
        this.registrationNamber = registrationNamber;
    }

    public ModelOfCar getModelOfCar() {
        return modelOfCar;
    }

    public void setModelOfCar(ModelOfCar modelOfCar) {
        this.modelOfCar = modelOfCar;
    }

    public StatusCar getStatusCar() {
        return statusCar;
    }

    public void setStatusCar(StatusCar statusCar) {
        this.statusCar = statusCar;
    }

    public String getRegistrationNamber() {
        return registrationNamber;
    }

    public void setRegistrationNamber(String registrationNamber) {
        this.registrationNamber = registrationNamber;
    }

    public Set<Custom> getCustomSet() {
        return customSet;
    }

    public void setCustomSet(Set<Custom> customSet) {
        this.customSet = customSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
