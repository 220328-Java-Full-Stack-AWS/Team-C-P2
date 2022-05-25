package com.revature.TeamCP2;

import com.revature.TeamCP2.beans.repositories.CategoriesRepository;
import com.revature.TeamCP2.beans.repositories.OnSaleRepository;
import com.revature.TeamCP2.beans.repositories.ProductsRepository;
import com.revature.TeamCP2.beans.services.CategoriesService;
import com.revature.TeamCP2.beans.services.OnSaleService;
import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.entities.OnSale;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.entities.ProductCategory;
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

        //Byte Outputstream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = baos.toByteArray();


        //Initialize Category repositories
        CategoriesRepository categoriesRepository = context.getBean(CategoriesRepository.class);
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);

        //Create Category
        ProductCategory category = new ProductCategory();
        category.setName("name");
        category.setDescription("description");

        BufferedImage categoryImg = ImageIO.read(new File("src/main/images/cat.png"));
        ImageIO.write(categoryImg, "png", baos);
        bytes = baos.toByteArray();

        category.setImage(bytes);

        categoriesService.create(category);




        //Initializing Product Repositories
        ProductsRepository productsRepository = context.getBean(ProductsRepository.class);
        ProductService productService = new ProductService(productsRepository);

        //Create Product
        Product product1 = new Product();
        product1.setDescr("Image of cat");
        product1.setName("Cat");
        product1.setIsFeatured(true);
        product1.setPrice(1);

        BufferedImage img = ImageIO.read(new File("src/main/images/cat.png"));
        ImageIO.write(img, "png", baos);
        bytes = baos.toByteArray();
        product1.setImage(bytes);
        productService.create(product1);


        //Initializing onSale repositories
        OnSaleRepository onSaleRepository = context.getBean(OnSaleRepository.class);
        OnSaleService onSaleService = new OnSaleService(onSaleRepository);

//        //Create
//        OnSale onSale = new OnSale();
//        onSale.setDiscount(0.25);
//        onSaleService.createOnSale(onSale);
    }
}