package myProject.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vasj on 27.07.2016.
 */
@Entity
@Table(name = "classOfCar", 
indexes={@Index(name="TCC_INDEX",columnList="typeClassOfCar"), @Index(name="PRICE_INDEX",columnList="price")})
public class ClassOfCar implements Serializable{

    private static final long serialVersionUID = -2320397395105892434L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    private String typeClassOfCar;
    private int price;

    @OneToMany(mappedBy = "classOfCar")
    private Set<ModelOfCar> modelOfCar = new HashSet<ModelOfCar>();

    public ClassOfCar(){
    }
    public ClassOfCar(String typeClassOfCar, int price){
        this.typeClassOfCar = typeClassOfCar;
        this.price = price;
    }

    public String getTypeClassOfCar() {
        return typeClassOfCar;
    }

    public void setTypeClassOfCar(String typeClassOfCar) {
        this.typeClassOfCar = typeClassOfCar;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<ModelOfCar> getModelOfCar() {
        return modelOfCar;
    }

    public void setModelOfCar(Set<ModelOfCar> modelOfCar) {
        this.modelOfCar = modelOfCar;
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
		ClassOfCar other = (ClassOfCar) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
}
