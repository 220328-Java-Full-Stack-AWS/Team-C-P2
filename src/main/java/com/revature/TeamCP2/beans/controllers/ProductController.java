/**
 * Author(s): @Diego Leon
 * Contributor(s):
 * Purpose: ProductController
 */


package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.ProductService;
import com.revature.TeamCP2.entities.Product;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//annotation, a combination of @Controller and @ResponseBody.

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Possible query Params: ?onSale=true , ?featured=true ,, limit 10-20 on the return

    //getAll()

    //
    @GetMapping()
    public List<Product> getAll() {
        //maybe just display the name and price of this product
        return productService.getAll();
    }

    //getById(/{id}) GET
    @GetMapping("/{id}")
    public Optional<Product> getById(@PathVariable("id") int id) throws ItemDoesNotExistException {
        //display full item description and all
        return productService.getById(id);
    }

    //create(/)POST admin ONly

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@ModelAttribute Product product) throws CreationFailedException, ItemHasNonNullIdException {
        //tried using @RequestBody but was not able to make it work
        return productService.create(product);
    }


    //updateById(/{id})PUT ADMIN
    @PutMapping("/update/{id}/update")
    @ResponseStatus(HttpStatus.CONTINUE)
    public Product updateById(@ModelAttribute Product updatedProduct) {
        return productService.update(updatedProduct);
    }


    //deleteById(/{id})DELETE ADMIN


}
