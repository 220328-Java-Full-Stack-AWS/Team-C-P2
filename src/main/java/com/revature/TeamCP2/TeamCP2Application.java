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
        byte[] bytes;

        //Initializing onSale repositories
        OnSaleRepository onSaleRepository = context.getBean(OnSaleRepository.class);
        OnSaleService onSaleService = new OnSaleService(onSaleRepository);

        //Initialize Category repositories
        CategoriesRepository categoriesRepository = context.getBean(CategoriesRepository.class);
        CategoriesService categoriesService = new CategoriesService(categoriesRepository);

        //Initializing Product Repositories
        ProductsRepository productsRepository = context.getBean(ProductsRepository.class);
        ProductService productService = new ProductService(productsRepository);

        //Create Cricket Category
        ProductCategory cCategory = new ProductCategory();
        cCategory.setName("Cricket");
        cCategory.setDescription("Items used for playing European Cricket");
        BufferedImage cCategoryImg = ImageIO.read(new File("src/main/images/products/Cricket/cricketCategory.png"));
        ImageIO.write(cCategoryImg, "png", baos);
        bytes = baos.toByteArray();
        cCategory.setImage(bytes);
        categoriesService.create(cCategory);

        //Create Cricket Product
        Product cProduct1 = new Product();
        cProduct1.setCategory(cCategory);
        cProduct1.setDescr("Hard Cricket Ball");
        cProduct1.setName("Cricket Ball");
        cProduct1.setIsFeatured(true);
        cProduct1.setPrice(1);
        BufferedImage c1img = ImageIO.read(new File("src/main/images/products/Cricket/cricketBall.png"));
        ImageIO.write(c1img, "png", baos);
        bytes = baos.toByteArray();
        cProduct1.setImage(bytes);
        productService.create(cProduct1);

        //Create Cricket Product
        Product cProduct2 = new Product();
        cProduct2.setCategory(cCategory);
        cProduct2.setDescr("Bat used to strike cricket balls");
        cProduct2.setName("Cricket Bat");
        cProduct2.setIsFeatured(true);
        cProduct2.setPrice(1);
        BufferedImage c2img = ImageIO.read(new File("src/main/images/products/Cricket/cricketBat.png"));
        ImageIO.write(c2img, "png", baos);
        bytes = baos.toByteArray();
        cProduct2.setImage(bytes);
        productService.create(cProduct2);

        //Create Cricket Product
        Product cProduct3 = new Product();
        cProduct3.setCategory(cCategory);
        cProduct3.setDescr("Cricket Helmet to protect from head injury");
        cProduct3.setName("Cricket Helmet");
        cProduct3.setIsFeatured(true);
        cProduct3.setPrice(1);
        BufferedImage c3img = ImageIO.read(new File("src/main/images/products/Cricket/cricketHelmet.png"));
        ImageIO.write(c3img, "png", baos);
        bytes = baos.toByteArray();
        cProduct3.setImage(bytes);
        productService.create(cProduct3);

        //Create Cricket Product
        Product cProduct4 = new Product();
        cProduct4.setCategory(cCategory);
        cProduct4.setDescr("Pads to protect the legs from being hit by the cricket ball");
        cProduct4.setName("Cricket Pads");
        cProduct4.setIsFeatured(true);
        cProduct4.setPrice(1);
        BufferedImage c4img = ImageIO.read(new File("src/main/images/products/Cricket/cricketPads.png"));
        ImageIO.write(c4img, "png", baos);
        bytes = baos.toByteArray();
        cProduct4.setImage(bytes);
        productService.create(cProduct4);

        //Create Cricket Product
        Product cProduct5 = new Product();
        cProduct5.setCategory(cCategory);
        cProduct5.setDescr("Long-sleeved shirt worn while playing Cricket");
        cProduct5.setName("Cricket Shirt");
        cProduct5.setIsFeatured(true);
        cProduct5.setPrice(1);
        BufferedImage c5img = ImageIO.read(new File("src/main/images/products/Cricket/cricketShirt.png"));
        ImageIO.write(c5img, "png", baos);
        bytes = baos.toByteArray();
        cProduct5.setImage(bytes);
        productService.create(cProduct5);

        //Create Football Category
        ProductCategory fbCategory = new ProductCategory();
        fbCategory.setName("Football");
        fbCategory.setDescription("Items used for playing American Football");
        BufferedImage fbCategoryImg = ImageIO.read(new File("src/main/images/categories/footballCategory.png"));
        ImageIO.write(fbCategoryImg, "png", baos);
        bytes = baos.toByteArray();
        fbCategory.setImage(bytes);
        categoriesService.create(fbCategory);



        /*//Create football Product
        Product fbProduct1 = new Product();
        fbProduct1.setDescr("Premium Leather Football");
        fbProduct1.setName("Football");
        fbProduct1.setIsFeatured(true);
        fbProduct1.setPrice(1);
        BufferedImage fb1img = ImageIO.read(new File("src/main/images/products/Football/football.png"));
        ImageIO.write(fb1img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct1.setImage(bytes);
        productService.create(fbProduct1);

        //Create football Product
        Product fbProduct2 = new Product();
        fbProduct2.setDescr("Shoes used to increase grip");
        fbProduct2.setName("Football Cleats");
        fbProduct2.setIsFeatured(true);
        fbProduct2.setPrice(1);
        BufferedImage fb2img = ImageIO.read(new File("src/main/images/products/Football/footballCleats.png"));
        ImageIO.write(fb2img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct2.setImage(bytes);
        productService.create(fbProduct2);

        //Create football Product
        Product fbProduct3 = new Product();
        fbProduct3.setDescr("Helmet to protect from head injury");
        fbProduct3.setName("Football Helmet");
        fbProduct3.setIsFeatured(true);
        fbProduct3.setPrice(1);
        BufferedImage fb3img = ImageIO.read(new File("src/main/images/products/Football/footballHelmet.png"));
        ImageIO.write(fb3img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct3.setImage(bytes);
        productService.create(fbProduct3);

        //Create football Product
        Product fbProduct4 = new Product();
        fbProduct4.setDescr("Breathable jersey that shows off you team's colors");
        fbProduct4.setName("Football Jersey");
        fbProduct4.setIsFeatured(true);
        fbProduct4.setPrice(1);
        BufferedImage fb4img = ImageIO.read(new File("src/main/images/products/Football/footballJersey.png"));
        ImageIO.write(fb4img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct4.setImage(bytes);
        productService.create(fbProduct4);

        //Create football Product
        Product fbProduct5 = new Product();
        fbProduct5.setDescr("Protect your body from tackling damage");
        fbProduct5.setName("Football Pads");
        fbProduct5.setIsFeatured(true);
        fbProduct5.setPrice(1);
        BufferedImage fb5img = ImageIO.read(new File("src/main/images/products/Football/footballPads.png"));
        ImageIO.write(fb5img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct5.setImage(bytes);
        productService.create(fbProduct5);*/


//        //Create
//        OnSale onSale = new OnSale();
//        onSale.setDiscount(0.25);
//        onSaleService.createOnSale(onSale);
    }
}