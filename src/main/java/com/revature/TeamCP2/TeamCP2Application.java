package com.revature.TeamCP2;

import com.revature.TeamCP2.beans.repositories.CategoriesRepository;
import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.revature.TeamCP2.beans")
public class TeamCP2Application {

    public static void main(String[] args) throws ItemDoesNotExistException, ItemHasNoIdException, CreationFailedException, ItemHasNonNullIdException {
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
        product.setIs_featured(true);
        product.setPrice(200.25);
        productService.create(product);

        //Create
        Product product1 = new Product();
        product1.setDescr("2 ");
        product1.setName("Test Featured");
        product1.setIs_featured(true);
        product1.setPrice(123);
        productService.create(product1);

//        //update
//        Product update = new Product();
//        product.setDescr("Update");
//        product.setName("Test Name");
//        product.setPrice(200);
//        productService.update(product);

        //get byID
        Optional<Product> test = productService.getById(2);
        System.out.println(test.get().getName());
        //getAll
        List<Product> test2 = productService.getAll();
        System.out.println(test2);

        //delete
        //productService.delete(product);

        //get all deatured
        List<Product> test3 = productService.getAllFeatured();
        for (Product result : test3) {
            System.out.println(result.getId());
        }


        System.out.println(productService.getById(test.get().getId()));
    }

}
