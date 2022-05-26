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

        //Create football Product
        Product fbProduct1 = new Product();
        fbProduct1.setCategory(fbCategory);
        fbProduct1.setDescr("Premium Leather Football");
        fbProduct1.setName("Football");
        fbProduct1.setIsFeatured(true);
        fbProduct1.setPrice(1);
        BufferedImage fb1img = ImageIO.read(new File("src/main/images/products/Football/football.png"));
        ImageIO.write(fb1img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct1.setImage(bytes);
        productService.create(fbProduct1);
        baos = new ByteArrayOutputStream();

        //Create football Product
        Product fbProduct2 = new Product();
        fbProduct2.setCategory(fbCategory);
        fbProduct2.setDescr("Shoes used to increase grip");
        fbProduct2.setName("Football Cleats");
        fbProduct2.setIsFeatured(true);
        fbProduct2.setPrice(1);
        BufferedImage fb2img = ImageIO.read(new File("src/main/images/products/Football/footballCleats.png"));
        ImageIO.write(fb2img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct2.setImage(bytes);
        productService.create(fbProduct2);
        baos = new ByteArrayOutputStream();

        //Create football Product
        Product fbProduct3 = new Product();
        fbProduct3.setCategory(fbCategory);
        fbProduct3.setDescr("Helmet to protect from head injury");
        fbProduct3.setName("Football Helmet");
        fbProduct3.setIsFeatured(true);
        fbProduct3.setPrice(1);
        BufferedImage fb3img = ImageIO.read(new File("src/main/images/products/Football/footballHelmet.png"));
        ImageIO.write(fb3img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct3.setImage(bytes);
        productService.create(fbProduct3);
        baos = new ByteArrayOutputStream();

        //Create football Product
        Product fbProduct4 = new Product();
        fbProduct4.setCategory(fbCategory);
        fbProduct4.setDescr("Breathable jersey that shows off you team's colors");
        fbProduct4.setName("Football Jersey");
        fbProduct4.setIsFeatured(true);
        fbProduct4.setPrice(1);
        BufferedImage fb4img = ImageIO.read(new File("src/main/images/products/Football/footballJersey.png"));
        ImageIO.write(fb4img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct4.setImage(bytes);
        productService.create(fbProduct4);
        baos = new ByteArrayOutputStream();

        //Create football Product
        Product fbProduct5 = new Product();
        fbProduct5.setCategory(fbCategory);
        fbProduct5.setDescr("Protect your body from tackling damage");
        fbProduct5.setName("Football Pads");
        fbProduct5.setIsFeatured(true);
        fbProduct5.setPrice(1);
        BufferedImage fb5img = ImageIO.read(new File("src/main/images/products/Football/footballPads.png"));
        ImageIO.write(fb5img, "png", baos);
        bytes = baos.toByteArray();
        fbProduct5.setImage(bytes);
        productService.create(fbProduct5);
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
        rProduct5.setOnSale(runningSale);
        productService.create(rProduct5);
        baos = new ByteArrayOutputStream();

        //Create Basketball Category
        ProductCategory category = new ProductCategory();
        category.setName("Basketball");
        category.setDescription("Shop Men's and Women's Basketball Essentials");

        BufferedImage categoryImg = ImageIO.read(new File("src/main/images/categories/basketballCategory.jpg"));
        ImageIO.write(categoryImg, "jpg", baos);

        bytes = baos.toByteArray();
        category.setImage(bytes);
        categoriesService.create(category);
        baos = new ByteArrayOutputStream();

        //Create Basketball Products

        //Wilson Ball
        Product product = new Product();
        product.setCategory(category);
        product.setDescr("Wilson Evolution NCAA Basketball. Official NCAA College Basketball game regulation size.");
        product.setName("Wilson NCAA Basketball");
        product.setIsFeatured(true);
        product.setPrice(1);

        BufferedImage img = ImageIO.read(new File("src/main/images/products/Basketball/wilsonball.jpg"));
        ImageIO.write(img, "jpg", baos);

        bytes = baos.toByteArray();
        product.setImage(bytes);
        productService.create(product);
        baos = new ByteArrayOutputStream();

        //Jordan 11
        Product product1 = new Product();
        product1.setCategory(category);
        product1.setDescr("Jordan 11 Miami Vice Colorway Grey Women's. Available in all Women's sizes.");
        product1.setName("Jordan 11 Women's Basketball Shoe");
        product1.setIsFeatured(true);
        product1.setPrice(1);

        BufferedImage img1 = ImageIO.read(new File("src/main/images/products/Basketball/jordan11womens.jpg"));
        ImageIO.write(img1, "jpg", baos);

        bytes = baos.toByteArray();
        product1.setImage(bytes);
        productService.create(product1);
        baos = new ByteArrayOutputStream();

        //Adidas shorts
        Product product2 = new Product();
        product2.setCategory(category);
        product2.setDescr("Adidas Stripe Elite Basketball Shorts Black Men's. Cotton and mesh. Available in Children and Men's Adult sizes.");
        product2.setName("Adidas Men's Basketball Shorts");
        product2.setIsFeatured(true);
        product2.setPrice(1);

        BufferedImage img2 = ImageIO.read(new File("src/main/images/products/Basketball/nikeshortsmens.jpg"));
        ImageIO.write(img2, "jpg", baos);

        bytes = baos.toByteArray();
        product2.setImage(bytes);
        productService.create(product2);
        baos = new ByteArrayOutputStream();

        //Jordan Flights
        Product product3 = new Product();
        product3.setCategory(category);
        product3.setDescr("Jordan Flight Basketball Shoe Black and Red Colorway Women's. Available in Adult Women's sizes only.");
        product3.setName("Jordan Premier Flight Women's Basketball Shoes");
        product3.setIsFeatured(true);
        product3.setPrice(1);

        BufferedImage img3 = ImageIO.read(new File("src/main/images/products/Basketball/jordanflightwomens.jpg"));
        ImageIO.write(img3, "jpg", baos);

        bytes = baos.toByteArray();
        product3.setImage(bytes);
        productService.create(product3);
        baos = new ByteArrayOutputStream();

        //Pitt Cap
        Product product4 = new Product();
        product4.setCategory(category);
        product4.setDescr("Adidas Basketball Tank Top Blue Men's. Athletic waterproof material. Available in Children's and Adult sizes.");
        product4.setName("Adidas Basketball Tank Top");
        product4.setIsFeatured(true);
        product4.setPrice(1);

        BufferedImage img4 = ImageIO.read(new File("src/main/images/products/Basketball/adidastankmens.jpg"));
        ImageIO.write(img4, "jpg", baos);

        bytes = baos.toByteArray();
        product4.setImage(bytes);
        productService.create(product4);
        baos = new ByteArrayOutputStream();

        //Create Soccer Category
        ProductCategory sCategory = new ProductCategory();
        sCategory.setName("Soccer");
        sCategory.setDescription("Anything and everything that has to do with soccer.");
        BufferedImage sCategoryImg = ImageIO.read(new File("src/main/images/categories/soccerCategory.png"));
        ImageIO.write(sCategoryImg, "png", baos);
        bytes = baos.toByteArray();
        sCategory.setImage(bytes);
        categoriesService.create(sCategory);
        baos = new ByteArrayOutputStream();



        //Create Product
        Product sProduct1 = new Product();
        sProduct1.setCategory(sCategory);
        sProduct1.setDescr("The RevSport Coaches Dri-FIT AWF Jacket gives you a durable-yet-modern design for days when you have to brave rainy weather. It's coated with a water-repellent finish to help you stay dry when you're on the go. This product is made with 100% recycled polyester fibers.");
        sProduct1.setName("RevSport Coaches Dri-FIT AWF Jacket ");
        sProduct1.setIsFeatured(true);
        sProduct1.setPrice(90.00);
        BufferedImage s1img = ImageIO.read(new File("src/main/images/categories/soccerCategory.png"));
        ImageIO.write(s1img, "png", baos);
        bytes = baos.toByteArray();
        sProduct1.setImage(bytes);
        productService.create(sProduct1);
        baos = new ByteArrayOutputStream();


        //Create Running  Product 2
        Product sProduct2 = new Product();
        sProduct2.setCategory(sCategory);
        sProduct2.setDescr("The RevSport Strike Soccer Ball is ready to fly into the back of the net. A textured casing and stabilizing grooves help you put the ball right where you want it.");
        sProduct2.setName("RevSport Strike Soccer Ball");
        sProduct2.setIsFeatured(false);
        sProduct2.setPrice(35);
        BufferedImage s2img = ImageIO.read(new File("src/main/images/products/Soccer/soccerball.png"));
        ImageIO.write(s2img, "png", baos);
        bytes = baos.toByteArray();
        sProduct2.setImage(bytes);
        productService.create(sProduct2);
        baos = new ByteArrayOutputStream();


        //Create Running Product 3
        Product sProduct3 = new Product();
        sProduct3.setCategory(sCategory);
        sProduct3.setDescr("The RevSport Goalkeeper Match Gloves are ready to make every save. Soft padding provides cushioning against shots, while a smooth surface gives you grip in wet or dry conditions.");

        sProduct3.setName("RevSport Goalkeeper Match Gloves ");

        sProduct3.setIsFeatured(true);
        sProduct3.setPrice(100);
        BufferedImage s3img = ImageIO.read(new File("src/main/images/products/Soccer/soccergloves.png"));
        ImageIO.write(s3img, "png", baos);
        bytes = baos.toByteArray();
        sProduct3.setImage(bytes);
        productService.create(sProduct3);
        baos = new ByteArrayOutputStream();

        //Create Running Product 4
        Product sProduct4 = new Product();
        sProduct4.setCategory(sCategory);
        sProduct4.setDescr("Like other jerseys from our Match collection, this one pairs authentic design details with lightweight, quick-drying fabric to help keep the world’s biggest soccer stars cool and comfortable on the field. This product is made with at least 75% recycled polyester fibers.");
        sProduct4.setName("RevSport Match Jersey");
        sProduct4.setIsFeatured(true);
        sProduct4.setPrice(160);
        BufferedImage s4img = ImageIO.read(new File("src/main/images/products/Soccer/soccerjersey.png"));
        ImageIO.write(s4img, "png", baos);
        bytes = baos.toByteArray();
        sProduct4.setImage(bytes);
        productService.create(sProduct4);
        baos = new ByteArrayOutputStream();

        //Create runnign product 5

        Product sProduct5 = new Product();
        sProduct5.setCategory(sCategory);
        sProduct5.setDescr("The REvSport Mercurial Dream Speed Superfly 8 Elite embodies Cristiano Ronaldo’s greatest self-proclaimed strength: the power of the mind and meditation. Calming shades of green work together with energizing tones of purple and yellow, creating a cleat that radiates positivity.");

        sProduct5.setName("REvSport Nike Mercurial Dream Speed Superfly 8 Elite");

        sProduct5.setIsFeatured(true);
        sProduct5.setPrice(270);
        BufferedImage s5img = ImageIO.read(new File("src/main/images/products/Soccer/soccershoes.png"));
        ImageIO.write(s5img, "png", baos);
        bytes = baos.toByteArray();
        sProduct5.setImage(bytes);
        productService.create(sProduct5);
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
