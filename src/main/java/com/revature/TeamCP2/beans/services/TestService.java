package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.*;
import com.revature.TeamCP2.entities.*;
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

    @Autowired
    public TestService(UserRepository userRepository, UserAddressRepository userAddressRepository, ProductsRepository productsRepository, PaymentRepository paymentRepository, OnSaleRepository onSaleRepository, CategoriesRepository categoriesRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
        this.productsRepository = productsRepository;
        this.paymentRepository = paymentRepository;
        this.onSaleRepository = onSaleRepository;
        this.categoriesRepository = categoriesRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
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
}
