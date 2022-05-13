/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: UserService provides implementations to persist or retrieve user objects.
 *
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.UserRepository;
import com.revature.TeamCP2.dtos.LoginDto;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.*;
import com.revature.TeamCP2.interfaces.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptHash bCrypt;
    private final AuthService authService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptHash bCrypt, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.bCrypt = bCrypt;
    }

    public User create(User user) throws CreationFailedException, ItemHasNonNullIdException, UsernameAlreadyExistsException {

        if(userRepository.getByUsername(user.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException();

        user.setRole(Role.USER);
        user.setPassword(bCrypt.hash(user.getPassword()));
        return userRepository.create(user);
    }

    public User loginUser (String username, String password) throws NotAuthorizedException {
        LoginDto user = new LoginDto();
        user.setUsername(username);
        user.setPassword(password);

        return authService.loginUser(user);
    }
    public List<User> getAllUsers() {


    return userRepository.getAll();
    }

    public Optional<User> getById(int id) throws ItemDoesNotExistException {
        return userRepository.getById(id);
    }
}