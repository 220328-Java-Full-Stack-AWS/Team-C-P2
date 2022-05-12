package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.AuthService;
import com.revature.TeamCP2.dtos.LoginDto;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import com.revature.TeamCP2.exceptions.NotAuthorizedException;
import com.revature.TeamCP2.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void register(@RequestBody User user) {
        try {
            String jwtCookieDto = authService.registerUser(user);

            // Add jwt to cookie
        } catch (CreationFailedException e) {
            e.printStackTrace();
            // Respond Error: Failed to Create
        } catch (ItemHasNonNullIdException e) {
            e.printStackTrace();
            // Respond Error: User may already exist
        } catch (UsernameAlreadyExistsException e) {
            e.printStackTrace();
            // Respond Error: Cannot register username already in use
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void login(@RequestBody LoginDto loginInfo) {
        try {
            String jwt = authService.loginUser(loginInfo);

            // Add jwt to cookie
        } catch (NotAuthorizedException e) {
            e.printStackTrace();
            // Respond with 403
            // Username or password incorrect
        }
    }
}
