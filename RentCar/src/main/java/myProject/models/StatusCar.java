package myProject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vasj on 27.07.2016.
 */
@Entity
@Table(name = "statusCar", indexes={@Index(name="STATUS_INDEX", columnList="status")})
public class StatusCar implements Serializable{

    private static final long serialVersionUID = -133829071610231238L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated
    private Status status;

    @OneToMany(mappedBy = "statusCar")
    private Set<Car> cars = new HashSet<Car>();

    public StatusCar(){
        super();
    }
    public StatusCar(Status status){
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusCar other = (StatusCar) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
}
