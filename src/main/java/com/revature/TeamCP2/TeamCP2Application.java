package com.revature.TeamCP2;

import com.revature.TeamCP2.beans.repositories.CategoriesRepository;
import com.revature.TeamCP2.beans.repositories.OnSaleRepository;
import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.beans.services.OnSaleService;
import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.entities.OnSale;
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
        OnSaleRepository onSaleRepository = context.getBean(OnSaleRepository.class);
        OnSaleService onSaleService = new OnSaleService(onSaleRepository);

        //Create
//        Product product = new Product();
//        product.setDescr("Test ");
//        product.setName("Test Name");
//        product.setIsFeatured(true);
//        product.setPrice(200.25);
//        productService.create(product);

        //Create
        OnSale onSale = new OnSale();
        onSale.setDiscount(0.25);
        onSaleService.createOnSale(onSale);

        //Create
        Product product = new Product();
        product.setImage("assets/images/basket ball");
        product.setDescr("Lets shoot some hoops");
        product.setName("Basketball");
        product.setOnSale(onSale);
        product.setIsFeatured(true);
        product.setPrice(37.95);
        productService.create(product);


        Product product1 = new Product();
        product.setImage("assets/images/yankees cap.jpg");
        product1.setDescr("Nice Cap for Yankees Fans.");
        product1.setName("Yankees Cap");
        product1.setOnSale(onSale);
        product1.setIsFeatured(true);
        product1.setPrice(19.95);
        productService.create(product1);

        Product product2 = new Product();
        product.setImage("assets/images/football.jpg");
        product2.setDescr("For the Football Fan");
        product2.setName("FootBall");
        product2.setOnSale(onSale);
        product2.setIsFeatured(true);
        product2.setPrice(30.95);
        productService.create(product2);

        Product product3 = new Product();
        product.setImage("assets/images/bat.jpg");
        product3.setDescr("Hit a Home Run");
        product3.setName("Slugger");
        product3.setOnSale(onSale);
        product3.setIsFeatured(true);
        product3.setPrice(19.95);
        productService.create(product3);

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
