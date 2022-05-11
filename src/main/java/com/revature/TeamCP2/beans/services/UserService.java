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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.create(user);
    }
}

