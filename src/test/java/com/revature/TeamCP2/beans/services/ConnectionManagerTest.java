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
    @Mock public Session session;
    @Mock static List<Class> TEST_ENTITIES_LIST = new ArrayList<>();
    @Mock public Configuration config = new Configuration();
    @MockBean ConnectionManager mockConnection;

    private List<Class> ENTITIES_LIST = new ArrayList<>();



    @BeforeAll
    public static void beforeAll() {
        when(TEST_ENTITIES_LIST.add(User.class)).thenReturn(TEST_ENTITIES_LIST.contains(User.class));
        when(TEST_ENTITIES_LIST.add(Cart.class)).thenReturn(TEST_ENTITIES_LIST.contains(Cart.class));
        when(TEST_ENTITIES_LIST.add(CartItem.class)).thenReturn(TEST_ENTITIES_LIST.contains(CartItem.class));
        when(TEST_ENTITIES_LIST.add(OnSale.class)).thenReturn(TEST_ENTITIES_LIST.contains(OnSale.class));
        when(TEST_ENTITIES_LIST.add(Order.class)).thenReturn(TEST_ENTITIES_LIST.contains(Order.class));
        when(TEST_ENTITIES_LIST.add(Payment.class)).thenReturn(TEST_ENTITIES_LIST.contains(Payment.class));
        when(TEST_ENTITIES_LIST.add(Product.class)).thenReturn(TEST_ENTITIES_LIST.contains(Product.class));
        when(TEST_ENTITIES_LIST.add(ProductCategory.class)).thenReturn(TEST_ENTITIES_LIST.contains(ProductCategory.class));
        when(TEST_ENTITIES_LIST.add(UserAddress.class)).thenReturn(TEST_ENTITIES_LIST.contains(UserAddress.class));


    }

    @Test
    public void startTest(@Autowired ConnectionManager connectionManager) {
        connectionManager.setSession(session);
        connectionManager.setSessionFactory(sessionFactory);
        connectionManager.setConfig(config);

        connectionManager.start();


        for (Class entity : TEST_ENTITIES_LIST) {
            config.addAnnotatedClass(entity);
        }

        System.out.println(3);
        when(config.buildSessionFactory()).thenReturn(sessionFactory);
        System.out.println(4);
        when(sessionFactory.openSession()).thenReturn(session);
        System.out.println(5);
        assertEquals(connectionManager.isRunning(), true);
        verify(connectionManager, times(1)).start();
    }


}
