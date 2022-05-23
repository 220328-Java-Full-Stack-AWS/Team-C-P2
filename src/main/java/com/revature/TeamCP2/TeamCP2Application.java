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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages = "com.revature.TeamCP2.beans")
public class TeamCP2Application {

    public static void main(String[] args) throws ItemDoesNotExistException, ItemHasNoIdException, CreationFailedException, ItemHasNonNullIdException, IOException {
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
        Product product = new Product();
        product.setDescr("Test ");
        product.setName("Test Name");
        product.setIsFeatured(true);
        product.setPrice(200.25);
        productService.create(product);

        //Create
        OnSale onSale = new OnSale();
        onSale.setDiscount(0.25);
        onSaleService.createOnSale(onSale);

        //Create
        Product product1 = new Product();
        product1.setDescr("Image of cat");
        product1.setName("Cat");
        product1.setOnSale(onSale);
        product1.setIsFeatured(true);
        product1.setPrice(20);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedImage img = ImageIO.read(new File("src/main/images/cat.png"));
        ImageIO.write(img, "png", baos);
        byte[] bytes = baos.toByteArray();
        product1.setImage(bytes);
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
