package ua.deliveryservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="pizza_type")
@NamedQueries({
@NamedQuery(name="PizzaType.findAll", 
	query="SELECT p FROM PizzaType p"),
@NamedQuery(name="PizzaType.delete",
		query="DELETE FROM PizzaType p WHERE p.id=:id")	
})
public class PizzaType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", length=50, nullable=false)
	private String name;
	
	@Column(name="description", nullable=false)
	private String description;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="pizzaType")
	private List<Pizza> pizzas = new ArrayList<>();
	
	public PizzaType() {
		
	}
	
	public PizzaType(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name;
	}
	
}
