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


        /*//Create Category
        ProductCategory category = new ProductCategory();
        category.setName("name");
        category.setDescription("description");

        BufferedImage categoryImg = ImageIO.read(new File("src/main/images/cat.png"));
        ImageIO.write(categoryImg, "png", baos);
        bytes = baos.toByteArray();

        category.setImage(bytes);

        categoriesService.create(category);*/


        //Initializing Product Repositories
        ProductsRepository productsRepository = context.getBean(ProductsRepository.class);
        ProductService productService = new ProductService(productsRepository);

        //Create Cricket Category
        ProductCategory cCategory = new ProductCategory();
        cCategory.setName("Cricket");
        cCategory.setDescription("Items used for playing European Cricket");
        BufferedImage cCategoryImg = ImageIO.read(new File("src/main/images/categories/cricketCategory.png"));
        ImageIO.write(cCategoryImg, "png", baos);
        bytes = baos.toByteArray();
        cCategory.setImage(bytes);
        categoriesService.create(cCategory);
        baos = new ByteArrayOutputStream();

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
        baos = new ByteArrayOutputStream();

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
        baos = new ByteArrayOutputStream();

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
        baos = new ByteArrayOutputStream();

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
        baos = new ByteArrayOutputStream();

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
        baos = new ByteArrayOutputStream();

        //Create Football Category
        ProductCategory fbCategory = new ProductCategory();
        fbCategory.setName("Football");
        fbCategory.setDescription("Items used for playing American Football");
        BufferedImage fbCategoryImg = ImageIO.read(new File("src/main/images/categories/footballCategory.png"));
        ImageIO.write(fbCategoryImg, "png", baos);
        bytes = baos.toByteArray();
        fbCategory.setImage(bytes);
        categoriesService.create(fbCategory);
        baos = new ByteArrayOutputStream();


        //Create Running Category
        ProductCategory rCategory = new ProductCategory();
        rCategory.setName("Running");
        rCategory.setDescription("Anything and everything that has to do with running.");
        BufferedImage rCategoryImg = ImageIO.read(new File("src/main/images/categories/runningCategory.png"));
        ImageIO.write(rCategoryImg, "png", baos);
        bytes = baos.toByteArray();
        rCategory.setImage(bytes);
        categoriesService.create(rCategory);
        baos = new ByteArrayOutputStream();

        //Create Running Sale
        //Initializing onSale repositories
        OnSale runningSale = new OnSale(.60);
        onSaleService.createOnSale(runningSale);

        //Create Running Product
        Product rProduct1 = new Product();
        rProduct1.setCategory(rCategory);
        rProduct1.setDescr("The best Outdoor Running Belt You Can Find! RevSport Running Waist Belt is made of soft fabric, so the runners belt is very soft and breathable, fits comfortably to your body and doesn't move, jiggle, bounce, ride up or chafe. Our Running Waist Belt Phone Holder has more a durable zipper than any other brand, thousands of tests prove that");
        rProduct1.setName("RevSport Running Waist Belt");
        rProduct1.setIsFeatured(false);
        rProduct1.setPrice(25.00);
        BufferedImage r1img = ImageIO.read(new File("src/main/images/products/Running/runningfannypack.png"));
        ImageIO.write(r1img, "png", baos);
        bytes = baos.toByteArray();
        rProduct1.setImage(bytes);
        productService.create(rProduct1);
        baos = new ByteArrayOutputStream();

        ///////////////////////////////////////////////////////////////
        //Create Running  Product 2
        Product rProduct2 = new Product();
        rProduct2.setCategory(rCategory);
        rProduct2.setDescr("Still our most tested shoe, made to help you stay after those lofty running goals. The RevSport Run 3 feels soft and stable with a smooth ride that carries you through routes, long and short. A breathable upper is made to feel contained, yet flexible. We even added more cushioning around the heel and ankle for a supportive sensation. Keep running, we've got you.");

        rProduct2.setName("RevSport Run 3");
        rProduct2.setIsFeatured(true);
        rProduct2.setPrice(160);
        BufferedImage r2img = ImageIO.read(new File("src/main/images/products/Running/runningshoes1.png"));
        ImageIO.write(r2img, "png", baos);
        bytes = baos.toByteArray();
        rProduct2.setImage(bytes);
        rProduct2.setCategory(cCategory);
        rProduct2.setOnSale(runningSale);
        productService.create(rProduct2);
        baos = new ByteArrayOutputStream();

        ///////////////////////////////////


        //Create Running Product 3
        Product rProduct3 = new Product();
        rProduct3.setCategory(rCategory);
        rProduct3.setDescr("Chilly weather can't stop the run when you suit up in this spin on a favorite. The RecSport Therma-FIT Element Sweater can be worn 4 ways, giving you options for before, during or after your run. One side delivers a classic look while the other has Swoosh-shaped nodes for a riff on the traditional sweater. Heat-managing technology with stretchy fabric helps you stay moving in cool conditions. This product is made with at least 75% recycled polyester fibers.");

        rProduct3.setName("RevSport Running Sweater");

        rProduct3.setIsFeatured(false);
        rProduct3.setPrice(50.97);
        BufferedImage r3img = ImageIO.read(new File("src/main/images/products/Running/runningSweater.png"));
        ImageIO.write(r3img, "png", baos);
        bytes = baos.toByteArray();
        rProduct3.setImage(bytes);
        rProduct3.setCategory(rCategory);
        productService.create(rProduct3);
        baos = new ByteArrayOutputStream();

        //Create Running Product 4
        Product rProduct4 = new Product();
        rProduct4.setCategory(rCategory);
        rProduct4.setDescr("The finish is only a quick sprint away. Let the lightweight feel of the RevSPort Zoom Maxfly take you there. Bounce at every step gives you the responsiveness to match your speed as you race to shatter personal goals. We added plenty of traction to give you extra grip for races from 100 to 400 meters with hurdles.");

        rProduct4.setName("RevSport Sprinting Shoes");

        rProduct4.setIsFeatured(true);
        rProduct4.setPrice(200);
        BufferedImage r4img = ImageIO.read(new File("src/main/images/products/Running/sprintingshoes.png"));
        ImageIO.write(r4img, "png", baos);
        bytes = baos.toByteArray();
        rProduct4.setImage(bytes);
        rProduct4.setCategory(cCategory);
        rProduct4.setOnSale(runningSale);
        productService.create(rProduct4);
        baos = new ByteArrayOutputStream();

        //Create runnign product 5

        Product rProduct5 = new Product();
        rProduct5.setCategory(rCategory);
        rProduct5.setDescr("The road is your runway. Get ready to take flight in the workhorse with wings. Back with extra bounce that’s perfect for hitting the pavement. Whether you’re racking up everyday miles or on your long run, feel the spring in your step with the same cushioned support as its predecessor. Breathable mesh in the upper combines the comfort and durability you want with a wider fit at the toes.");

        rProduct5.setName("REvSport Women's Running Shoes");

        rProduct5.setIsFeatured(true);
        rProduct5.setPrice(300);
        BufferedImage r5img = ImageIO.read(new File("src/main/images/products/Running/womensrunninghsoes.png"));
        ImageIO.write(r5img, "png", baos);
        bytes = baos.toByteArray();
        rProduct5.setImage(bytes);
        rProduct5.setCategory(cCategory);
        rProduct5.setOnSale(runningSale);
        productService.create(rProduct5);
        baos = new ByteArrayOutputStream();



//        //Initializing onSale repositories
//        OnSaleRepository onSaleRepository = context.getBean(OnSaleRepository.class);
//        OnSaleService onSaleService = new OnSaleService(onSaleRepository);
//
//        //Create
//        OnSale onSale = new OnSale();
//        onSale.setDiscount(0.25);
//        onSaleService.createOnSale(onSale);
    }
}
