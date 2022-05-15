/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Controller
 */
package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.AuthService;
import com.revature.TeamCP2.beans.services.OrderService;
import com.revature.TeamCP2.dtos.CookieDto;
import com.revature.TeamCP2.entities.Order;
import com.revature.TeamCP2.exceptions.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders", produces = "application/json")
public class OrderController {

    private final OrderService orderService;
    private final AuthService authService;


    @Autowired
    public OrderController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @GetMapping()
    public ResponseEntity<Object> getAllOrders(@CookieValue(name = "user_session", required = false) String userSession) {
        try {
            if(userSession == null)
                throw new NotAuthorizedException();
            CookieDto userCookie = authService.getCookieDto(userSession);
            if(!authService.isAdmin(userCookie))
                throw new NotAuthorizedException();

            List<Order> orderList = orderService.getAll();
            return new ResponseEntity<>(orderList, HttpStatus.OK);

        } catch (NotAuthorizedException e) {
            JSONObject jsonObject = new JSONObject().put("message", "You are not authorized to make this request");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createOrder(@CookieValue(name = "user_session", required = false) String userSession, @RequestBody Order order) {
        try {
            if(userSession == null)
                throw new NotAuthorizedException();
            CookieDto userCookie = authService.getCookieDto(userSession);
            if(!authService.isAdmin(userCookie))
                throw new NotAuthorizedException();

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
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable(name = "id") Integer id, @CookieValue(name = "user_session", required = false) String userSession) {
        try {
            if(userSession == null)
                throw new NotAuthorizedException();
            CookieDto userCookie = authService.getCookieDto(userSession);
            if(!authService.isAdmin(userCookie))
                throw new NotAuthorizedException();

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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(name = "id") Integer id, @CookieValue(name = "user_session", required = false) String userSession, @RequestBody Order orderToUpdate) {
        try {
            if(userSession == null)
                throw new NotAuthorizedException();
            CookieDto userCookie = authService.getCookieDto(userSession);
            if(!authService.isAdmin(userCookie))
                throw new NotAuthorizedException();

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrder(@PathVariable(name = "id") Integer id, @CookieValue(name = "user_session", required = false) String userSession) {
        try {
            CookieDto userCookie = authService.getCookieDto(userSession);
            if(!authService.isAdmin(userCookie))
                throw new NotAuthorizedException();

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
