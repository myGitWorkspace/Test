package ua.deliveryservice.domain;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name="orders")
@NamedQueries({
@NamedQuery(name="Order.findAll",
    query="SELECT o FROM Order o ORDER BY o.creationDate"),
@NamedQuery(name="Order.updateState",
    query="UPDATE Order o SET o.state=:state WHERE o.id=:id"),
@NamedQuery(name="Order.findByUser",
    query="SELECT o FROM Order o WHERE o.user=:user"),
@NamedQuery(name="Order.updateReleaseDate",
    query="UPDATE Order o SET o.releaseDate=:releaseDate WHERE o.id=:id")
})
public class Order {
	
	@Transient
	private final int MAX_PIZZA_NUMBER = 10;
	
	public enum State{NEW,INPROGRESS,DONE,CANCELED}
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="state")
	private State state = State.NEW;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="orderPizzaId.order")	
	private List<OrderPizza> orderPizzas = new ArrayList<>();
	
	@Column(name="price")
	private Double price;
	
	@Column(name="discount")
	private Double discount;
	
	@Column(name="creation_date")
	private Date creationDate ;
	
	@Column(name="release_date")
	private Date releaseDate ;
		
	@Transient
	private Discount discountObj;
	
	@Transient
	private OrderState currentState;
	
	public Order() {
		
	}
	
	@Autowired
	public Order(User user, List<Pizza> pizzas) {
		if(user == null || orderPizzas == null)
			throw new IllegalArgumentException("Wrong input parameter in Order construct!");
		if(pizzas.size() > MAX_PIZZA_NUMBER) {
			throw new IllegalArgumentException("Max number of pizzas allowed is "+MAX_PIZZA_NUMBER + " you try to add " + pizzas.size() );
		}
		
		this.user = user;

		Map<Pizza,Integer> pizzasItems = getNumberItemsForEachPizza(pizzas);
		for(Pizza pizza : pizzasItems.keySet()) {
			OrderPizza op = new OrderPizza();
			op.setOrder(this);
			op.setPizza(pizza);
			op.setPrice(pizza.getPrice());
			op.setItems(pizzasItems.get(pizza));
			orderPizzas.add(op);
		}
		discountObj = new DiscountWithCardAndSetOfPizzas(user, pizzas);
		this.price = discountObj.getOrderPriceWithoutDiscount();
		this.discount = discountObj.getDiscount();
		currentState = new NewOrderState();
	}
	
	private Map<Pizza,Integer> getNumberItemsForEachPizza(List<Pizza> pizzas) {
		
		Map<Pizza,Integer> pizzasItems = new HashMap<>();
		for(Pizza pizza : pizzas) {
			if(!pizzasItems.containsKey(pizza)) {
				pizzasItems.put(pizza, 1);
			} else {
				pizzasItems.put(pizza, pizzasItems.get(pizza)+1);
			}
		}
		return pizzasItems;
	}
	/*
	@Override
	public int hashCode() {
		
	}
	*/
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public void setOrderPizzas(List<OrderPizza> orderPizzas) {
		if(orderPizzas == null)
			throw new IllegalArgumentException("Wrong input parameter!");
		if(orderPizzas.size() > MAX_PIZZA_NUMBER) {
			throw new IllegalArgumentException("Max number of pizzas allowed is "+MAX_PIZZA_NUMBER + " you try to add " + orderPizzas.size() );
		}
		this.orderPizzas = orderPizzas;
	}
	
	public List<OrderPizza> getOrderPizzas() {
		return this.orderPizzas;
	}
	/*
	public void addPizza(Pizza pizza) {
		if(pizza == null)
			throw new IllegalArgumentException("Wrong input parameter!");
		if(pizzas.size() >= MAX_PIZZA_NUMBER)
			throw new IllegalArgumentException("Order has max number of pizzas ("+MAX_PIZZA_NUMBER + ") You can't add anyone");
		this.pizzas.add(pizza);
	}
	*/
	public Double getPrice() {
		DecimalFormat df = new DecimalFormat("#.##"); 
		return Double.valueOf(df.format(this.price));
		//return this.price;
	}
	
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Double getDiscount() {

		DecimalFormat dFormat =new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
		return Double.valueOf(dFormat.format(this.discount));
		//return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void process() {
		currentState.process();
	}
	
	public void cancel() {
		currentState.cancel();
	}

	private class NewOrderState implements OrderState {
		public void process() {
			currentState = new InProgressOrderState();			
		}
		public void cancel() {
			currentState = new CanceledOrderState();
		}
	}
	
	private class InProgressOrderState implements OrderState {
		public void process() {
			currentState = new DoneOrderState();		
		}
		public void cancel() {
			throw new UnsupportedOperationException("You can't discard order in progress state!");
		}
	}
	
	private class DoneOrderState implements OrderState {
		public void process() {
			throw new UnsupportedOperationException("Order is already done!");			
		}
		public void cancel() {
			throw new UnsupportedOperationException("You can't discard order in done state!");
		}
	}
	
	private class CanceledOrderState implements OrderState {
		public void process() {
			throw new UnsupportedOperationException("You can't process canceled order!");			
		}
		public void cancel() {
			throw new UnsupportedOperationException("Order is already canceled!");
		}
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", state=" + state + ", price=" + price
				+ ", discount=" + discount + ", creationDate=" + creationDate
				+ ", releaseDate=" + releaseDate + "]";
	}


	
}
