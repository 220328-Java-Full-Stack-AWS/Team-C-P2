/**
 * UserController is a test file still in progress to see the interactions between front end requests and responses.
 */

package com.revature.TeamCP2.beans.controllers;

import com.revature.TeamCP2.beans.services.UserService;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import com.revature.TeamCP2.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//@RestController is a combination of @Controller and @ResponseBody
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //get all users
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by Id
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable int id) {
        try {
            Optional<User> opUser = userService.getById(id);

            if (!opUser.isPresent())
                throw new ItemDoesNotExistException();

            return opUser.get();
        } catch (ItemDoesNotExistException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    //post a new user - auto generate the ID
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public User persistNewUser(@RequestBody User newUser) throws CreationFailedException, ItemHasNonNullIdException {
        try {
            return userService.create(newUser);
        } catch (UsernameAlreadyExistsException e) {
            e.printStackTrace();
            System.out.println("Failed to create, username: " + newUser.getUsername() + ", already exists");
        }
        return null;
    }

/*
    //copied from kyle's example
    @GetMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public User authorizeUSer(@RequestBody AuthDto authDto) throws Exception {
        return userService.authenticateUser(authDto);
        //TODO: ResponseEntity<User> use this object to send back a different response for unauthorized
    }

    //put (update) an existing user (based on id)
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }
*/


    //delete user by id
    //TODO: add delete method.
}