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
@Table(name = "accident", indexes={@Index(name="WASTAGE_INDEX", columnList="wastage")})
public class Accident implements Serializable{

    private static final long serialVersionUID = -7608004706912044251L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    private LocalDateTime dateTime;
    private String damage;
    private int wastage;

    @ManyToMany
    @JoinTable(name = "accident_custom", joinColumns = {@JoinColumn(name = "accident_id")}, inverseJoinColumns = {@JoinColumn(name = "custom_id")})
    private Set<Custom> customSet = new HashSet<Custom>();

    public Accident(){
    }
    public Accident(LocalDateTime dateTime, String damage, int wastage){
    	this.dateTime = dateTime;
        this.damage = damage;
        this.wastage = wastage;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getWastage() {
        return wastage;
    }

    public void setWastage(int wastage) {
        this.wastage = wastage;
    }

    public Set<Custom> getCustomSet() {
        return customSet;
    }

    public void setCustomSet(Set<Custom> customSet) {
        this.customSet = customSet;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
