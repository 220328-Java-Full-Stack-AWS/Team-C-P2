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

    public void removeCartItem(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.getAll();
    }
    public Optional<Cart> getCartbyId(int id) {
        return cartRepository.getById(id);
    }
}
