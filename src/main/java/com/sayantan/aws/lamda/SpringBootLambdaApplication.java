package com.sayantan.aws.lamda;

import com.sayantan.aws.lamda.entity.Order;
import com.sayantan.aws.lamda.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class SpringBootLambdaApplication {

	@Autowired
	private OrderRepository orderDao;

	@Bean
	public Supplier<List<Order>> orders() {
		return () -> orderDao.buildOrders();
	}

	@Bean
	public Function<String, List<Order>> findOrderByName() {
		return (input) -> orderDao.buildOrders().stream().filter(order -> order.getName().equals(input)).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLambdaApplication.class, args);
	}

}
