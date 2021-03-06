package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.UserRepository;
import com.revature.TeamCP2.dtos.CookieDto;
import com.revature.TeamCP2.dtos.LoginDto;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import com.revature.TeamCP2.exceptions.NotAuthorizedException;
import com.revature.TeamCP2.exceptions.UsernameAlreadyExistsException;
import com.revature.TeamCP2.interfaces.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @MockBean BCryptHash bCryptHashMock;
    @MockBean JsonWebToken jsonWebTokenMock;
    @MockBean UserRepository userRepositoryMock;


    User USER_1;
    User ADMIN_USER;

    @BeforeEach
    public void beforeEach() {
        USER_1 = new User(1, "us3rn4m3", "p4ssw0rd", "Firstname", "Lastname", "email@email.com", Role.USER, null, null, null, null, null, null, null);
        ADMIN_USER = new User(1, "us3rn4m3", "p4ssw0rd", "Firstname", "Lastname", "email@email.com", Role.ADMIN, null, null, null, null, null, null, null);
    }

    @Test
    public void loginUserReturnsUserWhenAuthenticationPasses(@Autowired AuthService authService) throws NotAuthorizedException {
        String username = "us3rn4m3";
        String password = "p4ssw0rd";
        LoginDto loginInfo = new LoginDto(username, password);
        when(userRepositoryMock.getByUsername(username)).thenReturn(Optional.of(USER_1));
        when(bCryptHashMock.verify(any(), any())).thenReturn(true);

        User returnedUser = authService.loginUser(loginInfo);

        assertEquals(returnedUser, USER_1);
    }

    @Test
    public void loginUserThrowsNotAuthorizedExceptionWhenUsernameDoesNotExist(@Autowired AuthService authService) {
        String username = "username";
        String password = "snowBelly";
        LoginDto loginDto = new LoginDto(username, password);
        when(userRepositoryMock.getByUsername(username)).thenReturn(Optional.empty());

        assertThrows(NotAuthorizedException.class, () -> {
           authService.loginUser(loginDto);
        });

        verify(bCryptHashMock, times(0)).verify(any(), any());
    }

    @Test
    public void loginUserThrowsNotAuthorizedExceptionWhenThePasswordIsIncorrect(@Autowired AuthService authService) {
        String username = "myUserName";
        String password = "myPassWord";
        LoginDto loginDto = new LoginDto(username, password);
        when(userRepositoryMock.getByUsername(username)).thenReturn(Optional.of(USER_1));
        when(bCryptHashMock.verify(any(), any())).thenReturn(false);

        assertThrows(NotAuthorizedException.class, () -> {
            authService.loginUser(loginDto);
        });
        verify(bCryptHashMock, times(1)).verify(password, USER_1.getPassword());
    }

    @Test
    public void registerUserReturnsUserAfterRegistration(@Autowired AuthService authService) throws UsernameAlreadyExistsException, CreationFailedException, ItemHasNonNullIdException {
        USER_1.setId(null);
        User USER_2 = USER_1;
        USER_2.setRole(Role.USER);
        when(bCryptHashMock.hash(USER_1.getPassword())).thenReturn(USER_1.getPassword());
        when(userRepositoryMock.getByUsername(USER_1.getUsername())).thenReturn(Optional.empty());
        when(userRepositoryMock.create(USER_1)).thenReturn(USER_2);

        User registeredUser = authService.registerUser(USER_1);
        USER_1.setRole(Role.USER);

        assertEquals(registeredUser, USER_1);
    }

    @Test
    public void registerUserThrowsItemHasNonNullIdExceptionWhenUserIdIsNull (@Autowired AuthService authService) {
        USER_1.setId(5);

        assertThrows(ItemHasNonNullIdException.class, () -> {
            authService.registerUser(USER_1);
        });
        verify(userRepositoryMock, times(0)).getByUsername(any());
    }

    @Test
    public void registerUserThrowsUsernameAlreadyExists(@Autowired AuthService authService) {
        USER_1.setId(null);
        when(userRepositoryMock.getByUsername(USER_1.getUsername())).thenReturn(Optional.of(USER_1));

        assertThrows(UsernameAlreadyExistsException.class, () -> {
            authService.registerUser(USER_1);
        });
        verify(userRepositoryMock, times(1)).getByUsername(USER_1.getUsername());
    }

    @Test
    public void isAdminReturnsTrueCorrectly(@Autowired AuthService authService) {
        CookieDto nonAdminCookieDto = new CookieDto(USER_1);

        boolean isAdminCheck = authService.isAdmin(nonAdminCookieDto);
        assertFalse(isAdminCheck);
    }

    @Test
    public void isAdminReturnsFalseCorrectly(@Autowired AuthService authService) {
        CookieDto adminCookieDto = new CookieDto(ADMIN_USER);

        boolean isAdminCheck = authService.isAdmin(adminCookieDto);
        assertTrue(isAdminCheck);
    }

    @Test
    public void getCookieDtoCallsJsonWebTokenVerify(@Autowired AuthService authService) throws NotAuthorizedException {
        String cookieString = "al;ksdj;fklajs;dklfj;akf";
        CookieDto cookieDto = null;
        when(jsonWebTokenMock.verify(cookieString)).thenReturn(cookieDto);

        authService.getCookieDto(cookieString);

        verify(jsonWebTokenMock, times(1)).verify(cookieString);
    }

    @Test
    public void getAdminCookieReturnsUserCookie(@Autowired AuthService authService) throws NotAuthorizedException {
        String userSessionCookie = "MyUserSessionCookie";
        CookieDto adminCookieDto = new CookieDto(ADMIN_USER);
        AuthService authServiceMock = mock(AuthService.class);
        when(authServiceMock.getAdminCookie(userSessionCookie)).thenCallRealMethod();
        when(authServiceMock.getCookieDto(userSessionCookie)).thenReturn(adminCookieDto);
        when(authServiceMock.isAdmin(any())).thenReturn(true);

        CookieDto adminUserCookie = authServiceMock.getAdminCookie(userSessionCookie);
        assertEquals(adminUserCookie, adminCookieDto);
        verify(authServiceMock, times(1)).isAdmin(adminCookieDto);
        verify(authServiceMock, times(1)).getCookieDto(userSessionCookie);
    }
    @Test
    public void getAdminCookieThrowsNotAuthorizedExceptionWhenUserSessionIsNull(@Autowired AuthService authService) {
        assertThrows(NotAuthorizedException.class, () -> {
           authService.getAdminCookie(null);
        });
    }
    @Test
    public void getAdminCookieThrowsNotAuthorizedExceptionWhenCookieIsNotAdmin() throws NotAuthorizedException {
        String userSessionCookie = "MyUserSessionCookie";
        AuthService authServiceMock = mock(AuthService.class);
        when(authServiceMock.getAdminCookie(userSessionCookie)).thenCallRealMethod();
        when(authServiceMock.isAdmin(any())).thenReturn(false);

        assertThrows(NotAuthorizedException.class, () -> {
            authServiceMock.getAdminCookie(userSessionCookie);
        });
        verify(authServiceMock, times(1)).isAdmin(any());
    }
}


