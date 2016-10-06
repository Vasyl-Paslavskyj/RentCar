package myProject.models;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Vasj on 26.07.2016.
 */
@Entity
@Table(name = "user", indexes={@Index(name="LOGIN_INDEX", columnList="login"), 
		@Index(name="PASSWORD_INDEX", columnList="password"), 
		@Index(name="EMAIL_INDEX", columnList="email")})
public class User implements Serializable, UserDetails{

    private static final long serialVersionUID = 5052405760529825305L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
//    @Size(min=3, max=20, message="Username must be between 3 and 20 characters long.")
//    @Pattern(regexp="^[a-zA-Z0-9]+$", message="Username must be alphanumeric with no spaces")
    private String login;
    
    private String password;
    
    private String fullName;
    
    private int drivingExperience;
    
    private String email;
    
    @Enumerated
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Custom> customSet = new HashSet<Custom>();
    
    private boolean confirmed;
    private String verification;

    public User(){
    }
    public User(String login, String password, String fullName, int drivingExperience, String email){
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.drivingExperience = drivingExperience;
        this.email = email;
    }
    
    
    public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
		
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.name()));
		return authorities;
	}
	@Override
	public String getUsername() {
		return String.valueOf(id);
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		return confirmed;
	}

}
