package ua.deliveryservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="customer_card")
public class CustomerCard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="total_sum")
	private Double totalPriceOfOrders;
	
	@OneToOne(mappedBy="customerCard")	
	private User user;
	
	public CustomerCard() {
		totalPriceOfOrders = 0.;
	}

	public Double getTotalPriceOfOrders() {
		return totalPriceOfOrders;
	}

	public void addNewOrderPrice(Double orderPrice) {
		this.totalPriceOfOrders += orderPrice;
	}
	
}
