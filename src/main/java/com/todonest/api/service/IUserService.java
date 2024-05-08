package com.todonest.api.service;

import com.todonest.api.entity.User;

public interface IUserService {

    void createUser(User user);

    User getUserByEmail(String email);
}
