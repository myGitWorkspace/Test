package ua.deliveryservice.domain;

public interface OrderState {
	void process();
	void cancel();
}