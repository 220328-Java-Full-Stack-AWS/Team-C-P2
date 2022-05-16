/**
 Author: Steven Dowd
 Contributor(s): @Brandon Le
 Purpose: Cart Service
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CartItemRepository;
import com.revature.TeamCP2.beans.repositories.CartRepository;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.CartItem;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemService cartItemService;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
    }

    //@BL
    //Creates cart and assign to user upon creation
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.create(cart);
    }

    public Cart addCartItem(CartItem cartItem) {

        cartItemService.createCartItem(cartItem);
        return cartItem.getCart();
    }

    public Cart removeCartItem(CartItem cartItem) throws ItemHasNoIdException, ItemDoesNotExistException {

        cartItemService.delete(cartItem);
        return cartItem.getCart();
    }

    public Cart updateCartItem(CartItem cartItem) {

        cartItemService.update(cartItem);
        return cartItem.getCart();
    }

    public List<CartItem> getAllCartItems(Cart cart) {
        return cartItemService.getAllCartItemsByCart(cart);
    }
    public Optional<Cart> getCartbyId(int id) {
        return cartRepository.getById(id);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    public Cart updateCart(Cart cart) {
        return cartRepository.update(cart);
    }

}
