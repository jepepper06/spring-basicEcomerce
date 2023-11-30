package com.jepepper.sellingApp;

import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.domain.Role;
import com.jepepper.sellingApp.service.impl.ClientService;
import com.jepepper.sellingApp.service.impl.ProductService;
import com.jepepper.sellingApp.service.impl.StorageProperties;
import com.jepepper.sellingApp.service.impl.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SellingAppApplication {

	public static void main(String[] args) {
 		SpringApplication.run(SellingAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(
			ProductService productService,
			ClientService clientService,
			StorageService storageService){

		return args -> {
			clientService.saveRole(new Role(null,"ROLE_USER",new ArrayList<>()));
			clientService.saveRole(new Role(null,"ROLE_MANAGER",new ArrayList<>()));
			clientService.saveRole(new Role(null,"ROLE_ADMIN",new ArrayList<>()));
			clientService.saveRole(new Role(null,"ROLE_SUPERADMIN",new ArrayList<>()));

			clientService.saveUser(new Client(
					null,
					"Juan Sebastian",
					"juanse06",
					"juansebastiancastilloojeda@gmail",
					"123456789juan",
					new ArrayList<>(),
					new ArrayList<>()));
			clientService.addRoleToClient("juanse06","ROLE_USER");
			clientService.addRoleToClient("juanse06","ROLE_ADMIN");
			clientService.addRoleToClient("juanse06","ROLE_SUPERADMIN");

			productService.saveProduct(
					(new Product( null,
							"coca_cola250cc.jpg",
							"Coca Cola 250cc",
							"A can of soda",
							(Long) null,
							1.2,
							100L,
							null)));
			productService.saveProduct(
					new Product( null,
							"breezeice.png",
							"Breeze Ice 250cc",
							"A can of soda",
							(Long) null,
							2.4,
							100L,
							null));




			//storageService.deleteAll();
			storageService.init();
		};
	}
}
