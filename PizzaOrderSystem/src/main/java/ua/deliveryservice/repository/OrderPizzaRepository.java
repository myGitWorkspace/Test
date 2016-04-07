package ua.deliveryservice.repository;

import java.util.List;

import ua.deliveryservice.domain.OrderPizza;

public interface OrderPizzaRepository {

	List<OrderPizza> find();
}
