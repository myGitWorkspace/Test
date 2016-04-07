package ua.deliveryservice.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="order_pizza")
@AssociationOverrides({
	@AssociationOverride(name="orderPizzaId.order", joinColumns={@JoinColumn(name="order_id")}),
	@AssociationOverride(name="orderPizzaId.pizza", joinColumns={@JoinColumn(name="pizza_id")})
})
@NamedQueries({
@NamedQuery(name="OrderPizza.getOrderPizzaAll",
    query="SELECT o FROM OrderPizza o")          
})
public class OrderPizza {

	@EmbeddedId
	private OrderPizzaId orderPizzaId = new OrderPizzaId();
	
	@Column(name="price")
	private Double price;
	
	@Column(name="items")
	private Integer items;
	
	
	public OrderPizza() {

	}

	public OrderPizzaId getOrderPizzaId() {
		return orderPizzaId;
	}

	public void setOrderPizzaId(OrderPizzaId orderPizzaId) {
		this.orderPizzaId = orderPizzaId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Order getOrder() {
		return orderPizzaId.getOrder();
	}
	
	public void setOrder(Order order) {
		orderPizzaId.setOrder(order);
	}
	
	public Pizza getPizza() {
		return orderPizzaId.getPizza();
	}
	
	public void setPizza(Pizza pizza) {
		orderPizzaId.setPizza(pizza);
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderPizza [orderPizzaId=" + orderPizzaId + ", price=" + price
				+ ", items=" + items + "]";
	}


	
}
