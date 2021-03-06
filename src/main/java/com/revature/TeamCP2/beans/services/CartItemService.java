/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: CartItemService provides implementations to persist or retrieve CartItem entities.
 *
 */
package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.CartItemRepository;
import com.revature.TeamCP2.entities.Cart;
import com.revature.TeamCP2.entities.CartItem;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNoIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem createCartItem (CartItem item) {
        return cartItemRepository.create(item);
    }

    public Optional<CartItem> getById(int id) throws ItemDoesNotExistException {
        return cartItemRepository.getById(id);
    }

    public List<CartItem> getAll() {
        return cartItemRepository.getAll();
    }

    public List<CartItem> getAllCartItemsByCart(Cart cart) {
        return cartItemRepository.getAllCartItemsByCart(cart);
    }

    public void delete(CartItem model) throws ItemHasNoIdException, ItemDoesNotExistException {
        cartItemRepository.delete(model);
    }

    public CartItem update(CartItem item) {
        return cartItemRepository.update(item);
    }
}
