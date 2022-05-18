package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.PaymentRepository;
import com.revature.TeamCP2.beans.repositories.UserAddressRepository;
import com.revature.TeamCP2.beans.repositories.UserRepository;
import com.revature.TeamCP2.dtos.LoginDto;
import com.revature.TeamCP2.entities.Payment;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.entities.UserAddress;
import com.revature.TeamCP2.exceptions.*;
import com.revature.TeamCP2.interfaces.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    User USER_1;
    User USER_2;

    @MockBean UserRepository userRepositoryMock;
    @MockBean BCryptHash bCryptHashMock;
    @MockBean AuthService authServiceMock;
    @MockBean UserAddressRepository userAddressRepositoryMock;
    @MockBean PaymentRepository paymentRepositoryMock;
    @MockBean CartService cartServiceMock;

    @BeforeEach
    public void beforeEach() {
        USER_1 = new User();
        USER_1.setUsername("MyUsername44");
        USER_1.setPassword("MySecretPassword");
        USER_1.setFirstName("Jeff");
        USER_1.setLastName("Jefferson");

        USER_2 = USER_1;
        USER_2.setRole(Role.USER);
        USER_2.setId(5);
    }

    @Test
    public void createReturnsCreatedUser(@Autowired UserService userService) throws UsernameAlreadyExistsException, CreationFailedException, ItemHasNonNullIdException {
        String hashedPassword = "thisishashed";
        when(userRepositoryMock.getByUsername(USER_1.getUsername())).thenReturn(Optional.empty());
        when(userRepositoryMock.create(any())).thenReturn(USER_2);
        when(bCryptHashMock.hash(USER_1.getPassword())).thenReturn(hashedPassword);

        User returnedUser = userService.create(USER_1);
        USER_1.setRole(Role.USER);
        USER_1.setPassword(hashedPassword);

        assertEquals(returnedUser, USER_1);
        verify(bCryptHashMock, times(1)).hash(any());
        verify(cartServiceMock, times(1)).createCart(USER_1);
    }

    @Test
    public void createThrowsUsernameAlreadyExistsException(@Autowired UserService userService) {
        when(userRepositoryMock.getByUsername(USER_1.getUsername())).thenReturn(Optional.of(USER_1));

        assertThrows(UsernameAlreadyExistsException.class, () -> userService.create(USER_1));
        verify(userRepositoryMock, times(1)).getByUsername(USER_1.getUsername());
        verify(cartServiceMock, times(0)).createCart(any());
    }

    @Test
    public void loginUserConstructsLoginDtoAndCallsAuthService(@Autowired UserService userService) throws NotAuthorizedException {
        String username = "Tortoise34";
        String password = "p4ssword";
        LoginDto loginInfo = new LoginDto();
        loginInfo.setUsername(username);
        loginInfo.setPassword(password);
        when(authServiceMock.loginUser(any())).thenReturn(USER_2);

        User retrievedUser = userService.loginUser(username, password);

        assertEquals(USER_2, retrievedUser);
        verify(authServiceMock, times(1)).loginUser(any());
    }

    @Test
    public void getbyUsernameCallsUserRepo(@Autowired UserService userService) {
        String username = "UsernameToGrab";
        when(userRepositoryMock.getByUsername(username)).thenReturn(Optional.of(USER_2));
        
        User retrievedUser = userService.getByUsername(username);

        assertEquals(retrievedUser, USER_2);
        verify(userRepositoryMock, times(1)).getByUsername(username);
    }

    @Test
    public void updatePasswordReturnsUserWithNewPassword(@Autowired UserService userService) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        String currentPassword = "currentPass";
        String newPassword = "newPassword";
        String hashedPassword = "This is definitely hashed this time!@30a;";
        when(bCryptHashMock.verify(any(), any())).thenReturn(true);
        when(bCryptHashMock.hash(newPassword)).thenReturn(hashedPassword);

        User updatedUser = userService.updatePassword(USER_2, currentPassword, newPassword);


        assertEquals(USER_2, updatedUser);
        verify(bCryptHashMock, times(1)).verify(any(), any());
        verify(bCryptHashMock, times(1)).hash(newPassword);
    }

    @Test
    public void updatePasswordReturnsNullIfVerifyFails(@Autowired UserService userService) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        User user = USER_2;
        String currentPassword = "myCurrentPassword";
        String newPassword = "CleanPass";
        when(bCryptHashMock.verify(currentPassword, user.getPassword())).thenReturn(false);

        User retrievedUser = userService.updatePassword(user, currentPassword, newPassword);

        assertNull(retrievedUser);
        verify(bCryptHashMock, times(1)).verify(currentPassword, user.getPassword());
        verify(bCryptHashMock, times(0)).hash(any());
    }

    @Test
    public void createUserAddressCallsUserAddressRepoAndReturnsUser(@Autowired UserService userService) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        User user = USER_2;
        UserAddress userAddress = new UserAddress();
        userAddress.setCity("Lincoln");
        userAddress.setAddressLine1("509 West 4th Street");
        when(userAddressRepositoryMock.create(userAddress)).thenReturn(userAddress);

        User updatedUser = userService.createUserAddress(user, userAddress);

        assertEquals(updatedUser.getUserAddresses(), userAddress);
        verify(userAddressRepositoryMock, times(1)).create(userAddress);
    }

    @Test
    public void createUserPaymentCallsPaymentRepoAndReturnsUser(@Autowired UserService userService) {
        User user = USER_2;
        Payment payment = new Payment("VISA", "Capital One", 555000302, (short)234, "2022-05-17");
        when(paymentRepositoryMock.create(payment)).thenReturn(payment);

        User retrieveduser = userService.createUserPayment(user, payment);

        assertEquals(retrieveduser.getPayments(), payment);
        verify(paymentRepositoryMock, times(1)).create(payment);
    }

    @Test
    public void updateUserAddressCallsUserAddressRepoAndReturnsUser(@Autowired UserService userService) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        User user = USER_2;
        UserAddress userAddress = new UserAddress(null, "503 West Palm Beach", null, "Dallas", 48839L, "United States", null);
        when(userAddressRepositoryMock.update(userAddress)).thenReturn(userAddress);

        User updatedUser = userService.updateUserAddress(user, userAddress);

        assertEquals(user, updatedUser);
        verify(userAddressRepositoryMock, times(1)).update(userAddress);
    }

    @Test
    public void updateUserPaymentPaymentRepoAndReturnsUser(@Autowired UserService userService) {
        User user = USER_2;
        Payment payment = new Payment("VISA", "Capital One", 555000302, (short)234, "2022-05-17");

        User updatedUser = userService.updateUserPayment(user, payment);

        assertEquals(user, updatedUser);
        verify(paymentRepositoryMock, times(1)).update(payment);
    }

    @Test
    public void getAllUsersCallsUserRepoAndReturnsList(@Autowired UserService userService) {
        List<User> listToReturn = new ArrayList<>();
        listToReturn.add(USER_1);
        listToReturn.add(USER_2);
        listToReturn.add(new User());
        when(userRepositoryMock.getAll()).thenReturn(listToReturn);

        List<User> userList = userService.getAllUsers();

        assertEquals(listToReturn, userList);
        verify(userRepositoryMock, times(1)).getAll();
    }

    @Test
    public void getByIdCallsUserRepoAndReturnsOptionalUser(@Autowired UserService userService) throws ItemDoesNotExistException {
        int id = 34;
        Optional<User> opUserToReturn = Optional.of(USER_2);
        when(userRepositoryMock.getById(id)).thenReturn(opUserToReturn);

        Optional<User> opRetrievedUser = userService.getById(id);

        assertEquals(opUserToReturn, opRetrievedUser);
        verify(userRepositoryMock, times(1)).getById(id);
    }
}
