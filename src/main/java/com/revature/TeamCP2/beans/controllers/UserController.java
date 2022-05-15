/**
 * Author(s): @Brandon Le, @Arun Mohan
 * Contributor(s):
 * Purpose: UserController processes requests from the front end and interacts with services to formulate a response.
 */

package com.revature.TeamCP2.beans.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.TeamCP2.beans.services.*;
import com.revature.TeamCP2.dtos.*;
import com.revature.TeamCP2.entities.*;
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
    private final ProductService productService;
    private final CartService cartService;
    private final CartItemService cartItemService;


    @Autowired
    public UserController(UserService userService, AuthService authService, OrderService orderService, CartService cartService, ProductService productService, CartItemService cartItemService) {
        this.userService = userService;
        this.authService = authService;
        this.orderService = orderService;
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemService = cartItemService;
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

    @PostMapping("/cart/checkout")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto createOrder (@RequestBody CartDto cartDto, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) throws NotAuthorizedException, ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException, CreationFailedException, ItemHasNonNullIdException {

        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }
        Order order = new Order();
        if (cartService.getCartbyId(userService.getById(cartDto.getUserId()).get().getActiveCartID()).isPresent()) {
            Cart cart = cartService.getCartbyId(userService.getById(cartDto.getUserId()).get().getActiveCartID()).get();
            order.setCart(cart);
            order.setDateCreated(cartDto.getDateCreated());
            orderService.createOrder(order);
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully checked out.", order);
        }

        return new HttpResponseDto(404, "Order creation failed. User not found.", null);

    }

    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto viewCart (@RequestHeader Integer userId, @RequestHeader String dateCreated, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) throws ItemDoesNotExistException {

        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }
        if (userService.getById(userId).isPresent()) {
            User user = userService.getById(userId).get();
            List<CartItem> cartItemList = cartItemService.getAllCartItemsByCart(cartService.getCartbyId(user.getActiveCartID()).get());

            res.setStatus(200);
            return new HttpResponseDto(200, "Success. Viewing Cart.", cartItemList);
        }

        return new HttpResponseDto(404, "Failed. User not found.", null);
    }

    @PostMapping("/cart/add")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto addToCart (@RequestBody CartItemDto cartItemDto, HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) throws ItemDoesNotExistException {

        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }
        if (userService.getById(cartItemDto.getUserId()).isPresent() && productService.getById(cartItemDto.getProductId()).isPresent()) {
            User user = userService.getById(cartItemDto.getUserId()).get();
            Cart cart = cartService.getCartbyId(user.getActiveCartID()).get();
            CartItem cartItem = new CartItem(cart, productService.getById(cartItemDto.getProductId()).get(), cartItemDto.getQuantity());

            cartService.addCartItem(cartItem);
            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully added item to cart.", cartItem);

        }

        return new HttpResponseDto(404, "Add to cart failed. User or product not found", null);

    }

    @GetMapping("/cart/remove")
    @ResponseStatus(HttpStatus.OK)
    public HttpResponseDto removeFromCart () {
        return null;
    }
}