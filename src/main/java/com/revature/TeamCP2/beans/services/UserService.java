/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: UserService provides implementations to persist or retrieve user objects.
 *          UserService @Autowires UserRepository.
 *
 */

package com.revature.TeamCP2.beans.services;

import com.revature.TeamCP2.beans.repositories.UserRepository;
import com.revature.TeamCP2.entities.User;
import com.revature.TeamCP2.exceptions.CreationFailedException;
import com.revature.TeamCP2.exceptions.ItemDoesNotExistException;
import com.revature.TeamCP2.exceptions.ItemHasNonNullIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) throws CreationFailedException, ItemHasNonNullIdException {
        return userRepository.create(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public Optional<User> getById(int id) throws ItemDoesNotExistException {
        return userRepository.getById(id);
    }
}