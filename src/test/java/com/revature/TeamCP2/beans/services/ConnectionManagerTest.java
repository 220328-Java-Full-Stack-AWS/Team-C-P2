package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Validator;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ConnectionManagerTest {

    @Mock public SessionFactory sessionFactory;
    @Mock public static Session session;
    @Mock public Configuration config;

    static List<Class> TEST_ENTITIES_LIST = new ArrayList<>();

    @BeforeAll
    public static void beforeAll() {
        TEST_ENTITIES_LIST.add(User.class);
        TEST_ENTITIES_LIST.add(Cart.class);
        TEST_ENTITIES_LIST.add(CartItem.class);
        TEST_ENTITIES_LIST.add(OnSale.class);
        TEST_ENTITIES_LIST.add(Order.class);
        TEST_ENTITIES_LIST.add(Payment.class);
        TEST_ENTITIES_LIST.add(Product.class);
        TEST_ENTITIES_LIST.add(ProductCategory.class);
        TEST_ENTITIES_LIST.add(UserAddress.class);
    }

    @BeforeEach
    public void beforeEach() {
            when(config.buildSessionFactory()).thenReturn(sessionFactory);
            when(sessionFactory.openSession()).thenReturn(session);
    }

    @Test
    public void startTest(@Autowired ConnectionManager connectionManager) {
        connectionManager.setConfig(config);

        connectionManager.start();

        assertEquals(connectionManager.isRunning(), true);
        assertEquals(TEST_ENTITIES_LIST, connectionManager.getModels());
        verify(sessionFactory, times(1)).openSession();
        verify(config, times(1)).buildSessionFactory();
        verify(config, times(TEST_ENTITIES_LIST.size())).addAnnotatedClass(any());
    }


}
