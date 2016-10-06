package myProject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vasj on 27.07.2016.
 */
@Entity
@Table(name = "modelOfCar", indexes={@Index(name="TMC_INDEX", columnList="typeModelCar")})
public class ModelOfCar implements Serializable{

    private static final long serialVersionUID = 8189913870882823850L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private ClassOfCar classOfCar;

    private String typeModelCar;
    
    private int version;
    
    private String path;

    @OneToMany(mappedBy = "modelOfCar")
    private Set<Car> cars = new HashSet<Car>();

    public ModelOfCar(){
        super();
    }
    public ModelOfCar(ClassOfCar classOfCar, String typeModelCar){
        this.classOfCar = classOfCar;
        this.typeModelCar = typeModelCar;
    }

    public ClassOfCar getClassOfCar() {
        return classOfCar;
    }

    public void setClassOfCar(ClassOfCar classOfCar) {
        this.classOfCar = classOfCar;
    }

    public String getTypeModelCar() {
        return typeModelCar;
    }

    public void setTypeModelCar(String typeModelCar) {
        this.typeModelCar = typeModelCar;
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
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
		ModelOfCar other = (ModelOfCar) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
