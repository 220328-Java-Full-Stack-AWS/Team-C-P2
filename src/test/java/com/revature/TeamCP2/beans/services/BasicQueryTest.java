package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BasicQueryTest {

    Class<User> targetClass = User.class;
    static User USER_1;

    @MockBean ConnectionManager connectionManagerMock;
    @MockBean Session sessionMock;
    @MockBean CriteriaBuilder criteriaBuilderMock;
    @MockBean CriteriaQuery criteriaQueryMock;
    @MockBean Query queryMock;
    @MockBean Root rootMock;

    @BeforeAll
    public static void beforeall() {
        USER_1 = new User();
        USER_1.setUsername("Us3rn4m3");
        USER_1.setPassword("p4ssw0rd");
        USER_1.setFirstName("Firstname");
        USER_1.setLastName("Lastname");
    }
    
    @BeforeEach
    public void beforeEach() {
        when(connectionManagerMock.getSession()).thenReturn(sessionMock);
        when(sessionMock.getCriteriaBuilder()).thenReturn(criteriaBuilderMock);
        when(sessionMock.createQuery((CriteriaQuery<Object>) any())).thenReturn(queryMock);
        when(criteriaBuilderMock.createQuery(targetClass)).thenReturn(criteriaQueryMock);
        when(criteriaQueryMock.from(targetClass)).thenReturn(rootMock);
        when(criteriaQueryMock.select(rootMock)).thenReturn(criteriaQueryMock);
    }

    @Test
    public void getByIdReturnsItem(@Autowired BasicQuery basicQuery) throws ItemDoesNotExistException {
        int id = 54;
        basicQuery.setaClass(targetClass);
        when(queryMock.getSingleResult()).thenReturn(USER_1);

        Object obj = basicQuery.getById(id);

        assertEquals(USER_1, obj);
        verify(rootMock, times(1)).get("id");
        verify(queryMock, times(1)).getSingleResult();
    }

    @Test
    public void getByIdThrowsItemDoesNotExistException(@Autowired BasicQuery basicQuery) {
        int id = 77;
        basicQuery.setaClass(targetClass);
        when(queryMock.getSingleResult()).thenThrow(NoResultException.class);

        assertThrows(ItemDoesNotExistException.class, () -> {
            basicQuery.getById(id);
        });
        verify(queryMock, times(1)).getSingleResult();
    }

    @Test
    public void getAllReturnsList(@Autowired BasicQuery basicQuery) {
        basicQuery.setaClass(targetClass);
        List itemList = new ArrayList<User>();
        itemList.add(new User());
        itemList.add(new User());
        itemList.add(new User());
        when(queryMock.getResultList()).thenReturn(itemList);

        List returnedItemList = basicQuery.getAll();

        assertEquals(returnedItemList, itemList);
        verify(queryMock, times(1)).getResultList();
    }
}
