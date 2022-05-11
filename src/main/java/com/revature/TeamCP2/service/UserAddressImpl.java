package com.revature.TeamCP2.service;

import com.revature.TeamCP2.models.User;
import com.revature.TeamCP2.repositories.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//@Autowire to user controller and call the user address inside the controller
//private UserAddress userAddress
@Service
public class UserAddressImpl implements UserAddressServiceInterface {
    @Autowired
    private UserAddressRepository userAddressRepository;
    @Override
    @Transactional
    public User save(User user){
        return userAddressRepository.save(user);
    }
}
