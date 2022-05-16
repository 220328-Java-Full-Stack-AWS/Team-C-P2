/**
 * Author(s): @Brandon Le
 * Contributor(s): @George Henderson
 * Purpose: AdminController is meant to be used as a guideline to creating the controller layer
 *          and to test all services.
 */

package com.revature.TeamCP2.beans.controllers;


import com.revature.TeamCP2.beans.services.AuthService;
import com.revature.TeamCP2.beans.services.OrderService;
import com.revature.TeamCP2.beans.services.TestService;
import com.revature.TeamCP2.dtos.CookieDto;
import com.revature.TeamCP2.entities.*;
import com.revature.TeamCP2.exceptions.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/admin", produces = "application/json")
public class AdminController {
    private final TestService testService;
    private final OrderService orderService;
    private final AuthService authService;

    @Autowired
    public AdminController(TestService testService, OrderService orderService, AuthService authService) {
        this.testService = testService;
        this.orderService = orderService;
        this.authService = authService;
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

    @GetMapping("/orders")
    public ResponseEntity<Object> getAllOrders(
            @CookieValue(name = "user_session", required = false) String userSession) {
        try {
            CookieDto userCookie = authService.getAdminCookie(userSession);;

            List<Order> orderList = orderService.getAll();
            return new ResponseEntity<>(orderList, HttpStatus.OK);

        } catch (NotAuthorizedException e) {
            JSONObject jsonObject = new JSONObject().put("message", "You are not authorized to make this request");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/orders")
    public ResponseEntity<Object> createOrder(
            @CookieValue(name = "user_session", required = false) String userSession,
            @RequestBody Order order) {
        try {
            CookieDto userCookie = authService.getAdminCookie(userSession);

            Order createdOrder = orderService.createOrder(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);

        } catch (NotAuthorizedException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "You are not authorized to make this request");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.FORBIDDEN);

        } catch (ItemHasNonNullIdException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Item already has an Id");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);

        } catch (ItemDoesNotExistException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Item does not exist");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
        } catch (CreationFailedException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Creating the order failed");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrder(
            @CookieValue(name = "user_session", required = false) String userSession,
            @PathVariable(name = "id") Integer id) {
        try {
            CookieDto userCookie = authService.getAdminCookie(userSession);

            Optional<Order> retrievedOrder = orderService.getOrderById(id);
            if(!retrievedOrder.isPresent())
                throw new ItemDoesNotExistException();

            return new ResponseEntity<>(retrievedOrder.get(), HttpStatus.OK);

        } catch (NotAuthorizedException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "You are not authorized to make this request");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.FORBIDDEN);

        } catch (ItemDoesNotExistException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "The order id: " + id + " does not exist");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Object> updateOrder(
            @CookieValue(name = "user_session", required = false) String userSession,
            @PathVariable(name = "id") Integer id,
            @RequestBody Order orderToUpdate) {
        try {
            CookieDto adminCookie = authService.getAdminCookie(userSession);

            Order updatedOrder = orderService.updateOrder(orderToUpdate);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);

        } catch (NotAuthorizedException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "You are not authorized to make this request");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.FORBIDDEN);
        }

        catch(ItemDoesNotExistException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "The item you are trying to update does not exist");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);

        } catch (UpdateFailedException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Updating this order failed please try again");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (ItemHasNoIdException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Id is required for the request");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> deleteOrder(
            @CookieValue(name = "user_session", required = false) String userSession,
            @PathVariable(name = "id") Integer id) {
        try {
            CookieDto userCookie = authService.getAdminCookie(userSession);

            orderService.deleteOrder(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Successfully deleted order");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);

        } catch (NotAuthorizedException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "You are not authorized to make this request");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.FORBIDDEN);

        } catch (ItemDoesNotExistException | ItemHasNoIdException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "The specified order does not exist");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.BAD_REQUEST);

        } catch (DeletionFailedException e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "Deleting the order failed, please try again");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}