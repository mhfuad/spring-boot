package com.fuad.crud;

import com.fuad.crud.product.ProductRequest;
import com.fuad.crud.product.ProductService;
import com.fuad.crud.userInfo.UserInfo;
import com.fuad.crud.userInfo.UserInfoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserInfoService service, ProductService service2){
		return args -> {
			var user = UserInfo.builder()
					.name("fuad")
					.email("fuad@gmail.com")
					.password("123123")
					.roles("Admin")
					.build();
			service.saveUser(user);

			var product = ProductRequest.builder()
							.name("DSA")
									.owner("fuad")
											.size(12)
												.build();
			service2.save(product);
		};
	}
}
