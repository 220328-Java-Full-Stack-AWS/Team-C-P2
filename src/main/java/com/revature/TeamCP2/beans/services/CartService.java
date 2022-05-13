/**
 Author: Steven Dowd
 Purpose: Cart Service
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CartItemRepository;
import com.revature.TeamCP2.beans.repositories.CartRepository;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart createCart(Cart cart) {
        return cartRepository.create(cart);
    }

    public void addCartItem(CartItem cartItem) {
        cartItemRepository.create(cartItem);
    }


    public List<CartItem> getAllCartItems() {
        return cartItemRepository.getAll();
    }
    public Optional<Cart> getCartbyId(int id) {
        return cartRepository.getById(id);
    }

    public void deleteCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }

    public Cart updateCart(Cart cart) {
        return cartRepository.update(cart);
    }

    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepository.update(cartItem);
    }
}
