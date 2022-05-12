/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: AdminController is meant to be used as a guideline to creating the controller layer
 *          and to test all services.
 */
package com.revature.TeamCP2.beans.controllers;


import com.revature.TeamCP2.beans.services.TestService;
import com.revature.TeamCP2.entities.*;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final TestService testService;

    @Autowired
    public AdminController(TestService testService) {
        this.testService = testService;
    }


    @PostMapping("/address")
    @ResponseStatus(HttpStatus.OK)
    public UserAddress persistAddress(@RequestBody UserAddress t) {
        return testService.createUserAddress(t);
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.OK)
    public Product persistAddress(@RequestBody Product t) {
        return testService.createProduct(t);
    }

    @PostMapping("/onsale")
    @ResponseStatus(HttpStatus.OK)
    public OnSale persistAddress(@RequestBody OnSale t) {
        return testService.createOnSale(t);
    }

    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public Cart persistAddress(@RequestBody Cart t) {
        return testService.createCart(t);
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    public ProductCategory persistAddress(@RequestBody ProductCategory t) {
        return testService.createCategory(t);
    }

    @PostMapping("/cartitem")
    @ResponseStatus(HttpStatus.OK)
    public CartItem persistAddress(@RequestBody CartItem t) {
        return testService.createCartItem(t);
    }

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public Order persistAddress(@RequestBody Order t) throws CreationFailedException, ItemHasNonNullIdException {
        return testService.createOrder(t);
    }
}