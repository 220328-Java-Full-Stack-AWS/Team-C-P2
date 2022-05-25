package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.*;
import com.revature.TeamCP2.entities.*;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestServiceTest {

    @MockBean UserAddressRepository userAddressRepositoryMock;
    @MockBean ProductsRepository productsRepositoryMock;
    @MockBean PaymentRepository paymentRepositoryMock;
    @MockBean OnSaleRepository onSaleRepositoryMock;
    @MockBean CategoriesRepository categoriesRepositoryMock;
    @MockBean CartRepository cartRepositoryMock;
    @MockBean CartItemRepository cartItemRepositoryMock;
    @MockBean OrderRepository orderRepositoryMock;

    @Test
    public void createUserAddressCallsUserAddressRepoAndReturnsUserAddress(@Autowired TestService testService) {
        UserAddress userAddressToPass = new UserAddress();
        userAddressToPass.setCountry("France");
        UserAddress userAddressToReturn = new UserAddress();
        userAddressToReturn.setCity("New York");
        userAddressToReturn.setPostalCode(445566L);
        userAddressToReturn.setCountry("USA");
        when(userAddressRepositoryMock.create(userAddressToPass)).thenReturn(userAddressToReturn);

        UserAddress returnedUserAddress = testService.createUserAddress(userAddressToPass);

        assertEquals(userAddressToReturn, returnedUserAddress);
        verify(userAddressRepositoryMock, times(1)).create(userAddressToPass);
    }

    @Test
    public void createProductCallsProductRepoAndReturnsProduct(@Autowired TestService testService) {
        Product productToPass = new Product();
        productToPass.setName("Basket ball");
        productToPass.setDescr("Orange ball");
        Product productToReturn = productToPass;
        productToReturn.setId(1);
        when(productsRepositoryMock.create(productToPass)).thenReturn(productToReturn);

        Product createdProduct = testService.createProduct(productToPass);

        assertEquals(productToReturn, createdProduct);
        verify(productsRepositoryMock, times(1)).create(productToPass);
    }

    @Test
    public void createPaymentCallsPaymentRepoAndReturnsPayment(@Autowired TestService testService) {
        Payment paymentToPass = new Payment();
        paymentToPass.setCardNumber("5566778899");
        paymentToPass.setExpirationDate("2023-05-25");
        paymentToPass.setSecurityCode(444);
        Payment paymentToReturn = paymentToPass;
        paymentToReturn.setId(55);
        when(paymentRepositoryMock.create(paymentToPass)).thenReturn(paymentToReturn);

        Payment createdPayment = testService.createPayment(paymentToPass);

        assertEquals(paymentToReturn, createdPayment);
        verify(paymentRepositoryMock, times(1)).create(paymentToPass);
    }

    @Test
    public void createOnSaleCallsOnSaleRepoAndReturnsOnSale(@Autowired TestService testService) {
        OnSale onSaleToPass = new OnSale();
        onSaleToPass.setDiscount(.20);
        OnSale onSaleToReturn = onSaleToPass;
        onSaleToReturn.setId(42);
        when(onSaleRepositoryMock.create(onSaleToPass)).thenReturn(onSaleToReturn);

        OnSale createdOnSale = testService.createOnSale(onSaleToPass);

        assertEquals(onSaleToReturn, createdOnSale);
        verify(onSaleRepositoryMock, times(1)).create(onSaleToPass);
    }

    @Test
    public void createCategoryCallsProductCategoryRepoAndReturnsProductCategory(@Autowired TestService testService) {
        ProductCategory categoryToPass = new ProductCategory("Basket ball", "Sport played on a court", null);
        ProductCategory categoryToReturn = categoryToPass;
        categoryToReturn.setId(22);
        when(categoriesRepositoryMock.create(categoryToPass)).thenReturn(categoryToReturn);

        ProductCategory createdCategory = testService.createCategory(categoryToPass);

        assertEquals(categoryToReturn, createdCategory);
        verify(categoriesRepositoryMock, times(1)).create(categoryToPass);
    }

    @Test
    public void createCartCallsCartRepoAndReturnsCart(@Autowired TestService testService) {
        Cart cartToPass = new Cart(null, 800.00);
        Cart cartToReturn = cartToPass;
        cartToReturn.setId(199);
        when(cartRepositoryMock.create(cartToPass)).thenReturn(cartToReturn);

        Cart createdCart = testService.createCart(cartToPass);

        assertEquals(cartToReturn, createdCart);
        verify(cartRepositoryMock, times(1)).create(cartToPass);
    }

    @Test
    public void createCartItemCallsCartItemRepoAndReturnsCartItem(@Autowired TestService testService) {
        CartItem cartItemToPass = new CartItem(null, new Product(), 10);
        CartItem cartItemToReturn = cartItemToPass;
        cartItemToReturn.setId(99);
        when(cartItemRepositoryMock.create(cartItemToPass)).thenReturn(cartItemToReturn);

        CartItem createdCartItem = testService.createCartItem(cartItemToPass);

        assertEquals(cartItemToReturn, createdCartItem);
        verify(cartItemRepositoryMock, times(1)).create(cartItemToPass);
    }

    @Test
    public void createOrderCallsOrderRepoAndReturnsUserOrder(@Autowired TestService testService) throws CreationFailedException, ItemHasNonNullIdException {
        Order orderToPass = new Order(new Cart(), "2022-07-20");
        Order orderToReturn = new Order();
        orderToReturn.setId(43);
        when(orderRepositoryMock.create(orderToPass)).thenReturn(orderToReturn);

        Order createdOrder = testService.createOrder(orderToPass);

        assertEquals(orderToReturn, createdOrder);
        verify(orderRepositoryMock, times(1)).create(orderToPass);
    }
}
