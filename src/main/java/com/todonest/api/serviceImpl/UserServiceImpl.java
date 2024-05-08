package com.todonest.api.serviceImpl;

import com.todonest.api.entity.User;
import com.todonest.api.repository.UserRepository;
import com.todonest.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {
        User newUser = userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
