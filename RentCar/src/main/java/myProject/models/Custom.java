package myProject.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vasj on 27.07.2016.
 */
@Entity
@Table(name = "custom", indexes={@Index(name="COST_INDEX", columnList="cost")})
public class Custom implements Serializable{

    private static final long serialVersionUID = -3982667368192440242L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private LocalDateTime dateTimeStart;
    @Enumerated
    private City cityStart;

    private LocalDateTime dateTimeFinish;
    @Enumerated
    private City cityFinish;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "customSet")
    private Set<Accident> accidentSet = new HashSet<Accident>();

    private int rentalLength;
    private int cost;
    private Boolean isActive = false;

    public Custom(){
        super();
    }
    public Custom(User user, Car car, LocalDateTime dateTimeStart, City cityStart, LocalDateTime dateTimeFinish,
                  City cityFinish, int rentalLength, int cost){
        this.user = user;
        this.car = car;
        this.dateTimeStart = dateTimeStart;
        this.cityStart = cityStart;
        this.dateTimeFinish = dateTimeFinish;
        this.cityFinish = cityFinish;
        this.rentalLength = rentalLength;
        this.cost = cost;
    }

    public LocalDateTime getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(LocalDateTime dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public City getCityStart() {
        return cityStart;
    }

    public void setCityStart(City cityStart) {
        this.cityStart = cityStart;
    }

    public LocalDateTime getDateTimeFinish() {
        return dateTimeFinish;
    }

    public void setDateTimeFinish(LocalDateTime dateTimeFinish) {
        this.dateTimeFinish = dateTimeFinish;
    }

    public City getCityFinish() {
        return cityFinish;
    }

    public void setCityFinish(City cityFinish) {
        this.cityFinish = cityFinish;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Accident> getAccidentSet() {
        return accidentSet;
    }

    public void setAccidentSet(Set<Accident> accidentSet) {
        this.accidentSet = accidentSet;
    }

    public int getRentalLength() {
        return rentalLength;
    }

    public void setRentalLength(int rentalLength) {
        this.rentalLength = rentalLength;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
