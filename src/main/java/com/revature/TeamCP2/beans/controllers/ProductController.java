/**
 * Author(s): @Diego Leon
 * Contributor(s): @Arun Mohan
 * Purpose: ProductController
 */


package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.AuthService;
import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.dtos.HttpResponseDto;
import com.revature.TeamCP2.dtos.ProductNetPriceDto;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


//annotation, a combination of @Controller and @ResponseBody.

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final AuthService authService;

    @Autowired
    public ProductController(ProductService productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;


    }

    //Possible query Params: ?onSale=true , ?featured=true ,, limit 10-20 on the return

    //getAll()

    //
    @GetMapping()
    public HttpResponseDto getAll(HttpServletResponse res) throws ItemDoesNotExistException {

        //maybe just display the name and price of this product
        List<Product> productList = productService.getAll();
        List<ProductNetPriceDto> dtoList = new LinkedList<>();
        for (Product p : productList) {
            ProductNetPriceDto netPriceDto = new ProductNetPriceDto(p, productService.getNetPrice(p));
            dtoList.add(netPriceDto);
        }

        if (productList.isEmpty()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve all products.", null);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved all products.", dtoList);
        }
    }


    @GetMapping("/category/{id}")
    public HttpResponseDto getByCategoryId(@PathVariable("id") int id, HttpServletResponse res) throws ItemDoesNotExistException {

        //maybe just display the name and price of this product
        List<Product> productList = productService.getByCategoryId(id);
        List<ProductNetPriceDto> dtoList = new LinkedList<>();
        for (Product p : productList) {
            ProductNetPriceDto netPriceDto = new ProductNetPriceDto(p, productService.getNetPrice(p));
            dtoList.add(netPriceDto);
        }

        if (productList.isEmpty()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve all products.", null);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved all products.", dtoList);
        }
    }


    //getById(/{id}) GET
    @GetMapping("/{id}")
    public HttpResponseDto getById(@PathVariable("id") int id, HttpServletResponse res) throws ItemDoesNotExistException {
        //display full item description and all
        Product product = productService.getById(id).get();
        ProductNetPriceDto netPriceDto = new ProductNetPriceDto(product, productService.getNetPrice(product));

        if (product == null) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve product.", null);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved product.", netPriceDto);
        }

    }


    //create(/)POST admin ONly

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponseDto create(@RequestBody Product product, HttpServletResponse res) throws CreationFailedException, ItemHasNonNullIdException, ItemDoesNotExistException, IOException {


        //tried using @RequestBody but was not able to make it wor

        //make dto to prodcut
        //

        Product newProduct = productService.create(product);

        if (!productService.getById(newProduct.getId()).isPresent()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to create product.", newProduct);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully created product.", newProduct);
        }
    }


    //updateById(/{id})PUT ADMIN
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto updateById(@RequestBody Product updatedProduct, HttpServletResponse res) throws ItemDoesNotExistException, UpdateFailedException {

//        return productService.update(updatedProduct);
        Product product = productService.update(updatedProduct);

        if (product.getPrice() != updatedProduct.getPrice()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to updated product.", product);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully updated product " + product.getName(), product);
        }
    }


    //deleteById(/{id})DELETE ADMIN

    @DeleteMapping("/delete/{id}")
    public HttpResponseDto deleteProduct(@PathVariable("id") Integer id, HttpServletResponse res) throws ItemHasNoIdException, ItemDoesNotExistException, DeletionFailedException {
        productService.deletebyId(id);

        if (productService.getById(id).isPresent()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to delete product.", null);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully deleted product.", null);
        }
    }

    @GetMapping("/{id}/price")
    public HttpResponseDto getNetPrice(@PathVariable("id") Integer id, HttpServletResponse res) throws ItemHasNoIdException, ItemDoesNotExistException {

        try {
            if (productService.getById(id).isPresent()) {
                Product product = productService.getById(id).get();

                Double price = product.getPrice();
                // if the product is on discount, calculate new price
                if (product.getOnSale() != null) {
                    Double discount = product.getOnSale().getDiscount();
                    price = ((Double) (1.00 - discount)) * price;

                }
                res.setStatus(200);
                return new HttpResponseDto(200, "Success. Retrieved price.", price);
            }
        } catch (ItemDoesNotExistException e) {
            res.setStatus(400);
        }

        return new HttpResponseDto(400, "Failed. Product does not exist.", null);

    }

    @GetMapping("/featured")
    public HttpResponseDto getAllFeatured(HttpServletResponse res) throws ItemDoesNotExistException {

        List<Product> productList = productService.getAllFeatured();
        List<ProductNetPriceDto> dtoList = new LinkedList<>();
        for (Product p : productList) {
            ProductNetPriceDto netPriceDto = new ProductNetPriceDto(p, productService.getNetPrice(p));
            dtoList.add(netPriceDto);
        }


        if (productList.isEmpty()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve all products.", null);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved all products.", dtoList);
        }
    }

}
