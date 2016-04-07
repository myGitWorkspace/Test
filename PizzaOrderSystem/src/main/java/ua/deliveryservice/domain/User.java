package ua.deliveryservice.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="users")
@NamedQueries({
@NamedQuery(name="User.findAll",
    query="SELECT u FROM User u"),
@NamedQuery(name="User.findByLogin",
    query="SELECT u FROM User u WHERE u.login=:login"),
@NamedQuery(name="User.updateLastVisitDate",
    query="UPDATE User u SET u.lastVisitDate=:lastVisitDate WHERE u.id=:id"),
@NamedQuery(name="User.findPassword",
    query="SELECT u.password FROM User u WHERE u.id=:id")    
})
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@Size(min=2, max=50)
	@Column(name="name")
	private String name;
		
	@Size(min=2, max=50)
	@Column(name="login")
	private String login;
	
	@Size(min=6, max=60)
	@Column(name="password")
	private String password;
		
	@Transient
	private String password2;
	
	@Column(name="enabled")
	private Integer enabled = 1;
	
	@NotNull
	@Column(name="register_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerDate;
	
	@NotNull
	@Column(name="last_visit_date ")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastVisitDate;
	
	@NotNull
	@OneToOne(orphanRemoval=true, cascade={CascadeType.ALL})
	@JoinColumn(name="address_id")
	private Address address;
	
	@OneToOne(orphanRemoval=true, cascade={CascadeType.MERGE})
	@JoinColumn(name="customer_card_id")
	private CustomerCard customerCard;
		
	@OneToMany(orphanRemoval=true, cascade={CascadeType.MERGE}, mappedBy="user")
	private List<Order> orders = new ArrayList<>();
	
	@NotEmpty
	@ManyToMany(targetEntity = Role.class, fetch=FetchType.EAGER)
	@JoinTable(name = "users_roles", 
    		joinColumns = { @JoinColumn(name = "user_id") }, 
    		inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private List<Role> roles = new ArrayList<>();
	
	public User() {
		
	}
		
	public User(Integer id, String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.customerCard = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CustomerCard getCustomerCard() {
		return customerCard;
	}

	public void setCustomerCard(CustomerCard customerCard) {
		this.customerCard = customerCard;
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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Date getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address
				+ ", customerCard=" + customerCard + "]";
	}

}
