/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: ProductController
 */


package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.AuthService;
import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.dtos.HttpResponseDto;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

        if (productList.isEmpty()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve all products.", productList);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved all products.", productList);
        }
    }

    //getById(/{id}) GET
    @GetMapping("/{id}")
    public HttpResponseDto getById(@PathVariable("id") int id, HttpServletResponse res) throws ItemDoesNotExistException {
        //display full item description and all
        Product product = productService.getById(id).get();

        if (product == null) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve product.", product);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved product.", product);
        }
    }


    //create(/)POST admin ONly

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpResponseDto create(@RequestBody Product product, HttpServletResponse res) throws CreationFailedException, ItemHasNonNullIdException, ItemDoesNotExistException {


        //tried using @RequestBody but was not able to make it work
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
            return new HttpResponseDto(400, "Successfully updated address.", null);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully deleted product.", null);
        }
    }

    @GetMapping("/featured")
    public HttpResponseDto getAllFeatured(HttpServletResponse res) throws ItemDoesNotExistException {

        List<Product> productList = productService.getAllFeatured();

        if (productList.isEmpty()) {
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed to retrieve all products.", productList);
        } else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully retrieved all products.", productList);
        }
    }

}
