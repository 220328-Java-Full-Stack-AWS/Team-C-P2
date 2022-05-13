/**
 * Author(s): @Brandon Le
 * Contributor(s):
 * Purpose: UserService provides implementations to persist or retrieve User entities.
 *
 */

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptHash bCrypt;
    private final AuthService authService;
    private final UserAddressRepository userAddressRepository;
    private final PaymentRepository paymentRepository;
    private final CartService cartService;

    @Autowired
    public UserService(UserRepository userRepository, BCryptHash bCrypt, AuthService authService, UserAddressRepository userAddressRepository, PaymentRepository paymentRepository, CartService cartService) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.bCrypt = bCrypt;
        this.userAddressRepository = userAddressRepository;
        this.paymentRepository = paymentRepository;
        this.cartService = cartService;
    }

    public User create(User user) throws CreationFailedException, ItemHasNonNullIdException, UsernameAlreadyExistsException {

        if(userRepository.getByUsername(user.getUsername()).isPresent())
            throw new UsernameAlreadyExistsException();

        user.setRole(Role.USER);
        user.setPassword(bCrypt.hash(user.getPassword()));
        cartService.createCart(user);
        return userRepository.create(user);
    }

    public User loginUser (String username, String password) throws NotAuthorizedException {
        LoginDto user = new LoginDto();
        user.setUsername(username);
        user.setPassword(password);

        return authService.loginUser(user);
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username).get();
    }

    //needs a bit more logic regarding exceptions
    public User updatePassword (User user, String currentPassword, String newPassword) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        if (user.getPassword().equals(currentPassword)) {
            user.setPassword(newPassword);
            return userRepository.update(user);
        }
        else {
            return null;
        }
    }

    public User createUserAddress(User user, UserAddress address) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        user.setUserAddresses(userAddressRepository.create(address));
        return userRepository.update(user);
    }

    public User createUserPayment(User user, Payment payment) {
        payment.setUser(user);
        paymentRepository.create(payment);
        return user;
    }

    public User updateUserAddress(User user, UserAddress address) throws ItemDoesNotExistException, UpdateFailedException, ItemHasNoIdException {
        user.setUserAddresses(userAddressRepository.update(address));
        return userRepository.update(user);
    }

    public User updateUserPayment(Payment payment) {
        paymentRepository.update(payment);
        return payment.getUser();
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public Optional<User> getById(int id) throws ItemDoesNotExistException {
        return userRepository.getById(id);
    }
}