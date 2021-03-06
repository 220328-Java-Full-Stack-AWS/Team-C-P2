/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: TestService is meant to be used as a guideline to creating the service layer
 *          and to test all repositories.
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.*;
import com.revature.TeamCP2.entities.*;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestService {
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final ProductsRepository productsRepository;
    private final PaymentRepository paymentRepository;
    private final OnSaleRepository onSaleRepository;
    private final CategoriesRepository categoriesRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderRepository orderRepository;


    @Autowired
    public TestService(UserRepository userRepository, UserAddressRepository userAddressRepository, ProductsRepository productsRepository, PaymentRepository paymentRepository, OnSaleRepository onSaleRepository, CategoriesRepository categoriesRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
        this.productsRepository = productsRepository;
        this.paymentRepository = paymentRepository;
        this.onSaleRepository = onSaleRepository;
        this.categoriesRepository = categoriesRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.orderRepository = orderRepository;
    }

    //create
    public UserAddress createUserAddress (UserAddress address) {
        return userAddressRepository.create(address);
    }

    public Product createProduct(Product product) {
        return productsRepository.create(product);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.create(payment);
    }

    public OnSale createOnSale(OnSale onSale){
        return onSaleRepository.create(onSale);
    }

    public ProductCategory createCategory(ProductCategory category) {
        return categoriesRepository.create(category);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.create(cart);
    }

    public CartItem createCartItem(CartItem item) {
        return cartItemRepository.create(item);
    }

    public Order createOrder(Order order) throws CreationFailedException, ItemHasNonNullIdException {
        return orderRepository.create(order);
    }
}
