/**
 * Author(s): @Brandon Le, @Arun Mohan
 * Contributor(s):
 * Purpose: UserController processes requests from the front end and interacts with services to formulate a response.
 */

package com.revature.TeamCP2.beans.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TeamCP2.beans.services.AuthService;
import com.revature.TeamCP2.beans.services.BCryptHash;
import com.revature.TeamCP2.beans.services.OrderService;
import com.revature.TeamCP2.beans.services.UserService;
import com.revature.TeamCP2.dtos.CookieDto;
import com.revature.TeamCP2.dtos.HttpResponseDto;
import com.revature.TeamCP2.dtos.UpdateAddressDto;
import com.revature.TeamCP2.dtos.UpdatePaymentDto;
import com.revature.TeamCP2.entities.Payment;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.entities.UserAddress;
import com.revature.TeamCP2.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthService authService;
    private final OrderService orderService;


    @Autowired
    public UserController(UserService userService, AuthService authService, OrderService orderService) {
        this.userService = userService;
        this.authService = authService;
        this.orderService = orderService;
    }


    //get all users
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by Id
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable int id) {
        try {
            Optional<User> opUser = userService.getById(id);

            if(!opUser.isPresent())
                throw new ItemDoesNotExistException();

            return opUser.get();
        } catch (ItemDoesNotExistException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}/profile")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto getUserProfile (HttpServletResponse res, @PathVariable int id, @CookieValue(name = "user_session", required = false) String userSession) throws NotAuthorizedException, ItemDoesNotExistException {
        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }

        CookieDto cookie = authService.getCookieDto(userSession);

        if (cookie.getUserId() != id) {
            res.setStatus(403);
            return new HttpResponseDto(403, "Forbidden access", null);
        }
        else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Success", userService.getById(id).get());
        }
    }

    @GetMapping("/{id}/orders")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto getAllOrders (HttpServletResponse res, @PathVariable int id, @CookieValue(name = "user_session", required = false) String userSession) throws NotAuthorizedException, ItemDoesNotExistException {
        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }

        CookieDto cookie = authService.getCookieDto(userSession);

        if (cookie.getUserId() != id) {
            res.setStatus(403);
            return new HttpResponseDto(403, "Forbidden access", null);
        }
        else {
            res.setStatus(200);
            return new HttpResponseDto(200, "Success", orderService.getByUserId(id));
        }
    }



    @PutMapping("/update/password")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto updatePassword (HttpServletResponse res, HttpServletRequest req, @CookieValue(name = "user_session", required = false) String userSession) throws NotAuthorizedException, ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException, IOException {
        Integer userID = req.getIntHeader("id");
        String currentPassword = req.getHeader("currentPassword");
        String newPassword = req.getHeader("newPassword");

        User user = userService.getById(userID).get();

        if (userService.updatePassword(user, currentPassword, newPassword) == null) {
            res.setStatus(401);
            return new HttpResponseDto(401, "Unauthorized. Incorrect password.", user);
        }
        else {
            res.setStatus(200);

            //sets cookie to null
            Cookie cookie = new Cookie("user_session", "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            res.addCookie(cookie);

            return new HttpResponseDto(200, "Successfully changed password.", user);
        }
    }

    @PutMapping("/update/address")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto updateAddress (@RequestBody UpdateAddressDto newAddress, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) throws NotAuthorizedException, ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }

        CookieDto cookie = authService.getCookieDto(userSession);
        System.out.println(newAddress);
        User user = userService.getById(newAddress.getUserID()).get();
        UserAddress address = new UserAddress();
        address.setAddressLine1(newAddress.getAddressLine1());
        address.setAddressLine2(newAddress.getAddressLine2());
        address.setCity(newAddress.getCity());
        address.setPhoneNumber(newAddress.getPhoneNumber());
        address.setPostalCode(newAddress.getPostalCode());
        address.setCountry(newAddress.getCountry());

        if (cookie.getUserId() != user.getId()) {
            res.setStatus(403);
            return new HttpResponseDto(403, "Forbidden access", null);
        }
        else {
            if (user.getUserAddresses() == null) {
                System.out.println("Address: " + address);
                user = userService.createUserAddress(user, address);
            }
            else {
                address.setId(user.getUserAddresses().getId());
                user = userService.updateUserAddress(user, address);
            }

            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully updated address.", user);
        }
    }

    @PutMapping("/update/payment")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto updatePayment (@RequestBody UpdatePaymentDto newPayment, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) throws NotAuthorizedException, ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }

        CookieDto cookie = authService.getCookieDto(userSession);

        User user = userService.getById(newPayment.getUserID()).get();
        Payment payment = new Payment();
        payment.setCardNumber(newPayment.getCardNumber());
        payment.setIssuer(newPayment.getIssuer());
        payment.setNetwork(newPayment.getNetwork());
        payment.setExpirationDate(newPayment.getExpDate());
        payment.setSecurityCode(newPayment.getSecurityCode());

        if (cookie.getUserId() != user.getId()) {
            res.setStatus(403);
            return new HttpResponseDto(403, "Forbidden access", null);
        }
        else {
            if (user.getPayments() == null) {
                user = userService.createUserPayment(user, payment);
            }
            else {
                payment.setId(user.getPayments().getId());
                user = userService.updateUserPayment(user, payment);
            }

            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully updated payment.", user);
        }
    }

    //post a new user - auto generate the ID
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public User persistNewUser(@RequestBody User newUser) throws CreationFailedException, ItemHasNonNullIdException {
        try {
            return userService.create(newUser);
        } catch (UsernameAlreadyExistsException e) {
            e.printStackTrace();
            System.out.println("Failed to create, username: " + newUser.getUsername() + ", already exists");
        }
        return null;
    }

/*
    //copied from kyle's example
    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public User authorizeUSer(@RequestBody AuthDto authDto) throws Exception {
        return userService.authenticateUser(authDto);
        //TODO: ResponseEntity<User> use this object to send back a different response for unauthorized
    }

    //put (update) an existing user (based on id)
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }
*/



    //delete user by id
    //TODO: add delete method.
}