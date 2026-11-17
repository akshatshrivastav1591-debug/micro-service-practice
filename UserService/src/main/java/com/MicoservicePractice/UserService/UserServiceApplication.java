package com.MicoservicePractice.UserService;


import com.MicoservicePractice.UserService.Service.Client.CartClient;
import com.MicoservicePractice.UserService.Service.Client.PaymentClient;
import com.MicoservicePractice.UserService.Service.Client.ProductClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices(group = "CartService",types = CartClient.class)
@ImportHttpServices(group = "ProductService",types = ProductClient.class)
@ImportHttpServices(group = "PaymentService",types = PaymentClient.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		System.out.println("User Service is Running...");
	}

}
