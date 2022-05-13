/**
 * Author(s): @George Henderson
 * Contributor(s):
 * Purpose: Controller that handles registering & logging a user in.
 */
package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.AuthService;
import com.revature.TeamCP2.beans.services.JsonWebToken;
import com.revature.TeamCP2.dtos.CookieDto;
import com.revature.TeamCP2.dtos.HttpResponseDto;
import com.revature.TeamCP2.dtos.LoginDto;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import com.revature.TeamCP2.exceptions.NotAuthorizedException;
import com.revature.TeamCP2.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;
    JsonWebToken jsonWebToken;

    @Autowired
    public AuthController(AuthService authService, JsonWebToken jsonWebToken){
        this.authService = authService;
        this.jsonWebToken = jsonWebToken;
    }

    @PostMapping("/register")
    public HttpResponseDto register(@RequestBody User user, HttpServletResponse res) {
        try {
            User registeredUser = authService.registerUser(user);

            String jwtCookieDto = jsonWebToken.sign(new CookieDto(registeredUser));

            Cookie cookie = new Cookie("user_session", jwtCookieDto);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            res.addCookie(cookie);

            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully registered user", registeredUser);
        } catch (CreationFailedException e) {
            e.printStackTrace();
            res.setStatus(500);
            return new HttpResponseDto(500, "Failed to register your account", null);

        }  catch (UsernameAlreadyExistsException | ItemHasNonNullIdException e) {
            e.printStackTrace();
            res.setStatus(400);
            return new HttpResponseDto(400, "Username already exists", null);
        }
    }

    @PostMapping("/login")
    public HttpResponseDto login(@RequestBody LoginDto loginInfo, HttpServletResponse res) {
        try {
            if (loginInfo.getPassword() == null || loginInfo.getUsername() == null){
                res.setStatus(400);
                return new HttpResponseDto(400, "username & password required", null);
            }
            User loggedInUser = authService.loginUser(loginInfo);
            String jwtCookieDto = jsonWebToken.sign(new CookieDto(loggedInUser));

            Cookie cookie = new Cookie("user_session", jwtCookieDto);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            res.addCookie(cookie);

            res.setStatus(200);
            return new HttpResponseDto(200, "Successfully logged in", loggedInUser);
        } catch (NotAuthorizedException e) {
            res.setStatus(403);
            return new HttpResponseDto(403, "Username or password is incorrect", null);
        }
    }

    @PostMapping(("/logout"))
    public HttpResponseDto logout(HttpServletResponse res, @CookieValue(name = "user_session", required = false) String userSession) {
        if(userSession == null){
            res.setStatus(400);
            return new HttpResponseDto(400, "Failed. You are not logged in", null);
        }

        Cookie cookie = new Cookie("user_session", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        res.addCookie(cookie);
        res.setStatus(200);
        return new HttpResponseDto(200, "Successfully logged out", null);
    }
}
