package com.revature.TeamCP2;

import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.entities.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.revature.TeamCP2.beans")
public class TeamCP2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TeamCP2Application.class, args);
        context.start();

		/*
		//Working example of creating a user through the service layer and persisting it into database.
		UserRepository userRepository = context.getBean(UserRepository.class);
		UserService userService = new UserService(userRepository);

		User newUser = new User();
		newUser.setUsername("BL");
		newUser.setFirstName("Brandon");
		newUser.setLastName("Le");
		userService.create(newUser);

		*/

        ProductsRepository productsRepository = context.getBean(ProductsRepository.class);
        ProductService productService = new ProductService(productsRepository);

        //Create
        Product product = new Product();
        product.setDescr("Test ");
        product.setName("Test Name");
        product.setPrice(200.25);
        productService.create(product);
    }

}
