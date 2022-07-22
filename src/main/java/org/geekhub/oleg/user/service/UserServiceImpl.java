package org.geekhub.oleg.user.service;

import org.geekhub.oleg.user.model.User;
import org.geekhub.oleg.user.repositoriy.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepositoryImpl;

    @Autowired
    public UserServiceImpl(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }


    @Override
    public void createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepositoryImpl.create(user);
    }

    @Override
    public User findByLogin(String login) {
        return userRepositoryImpl.findByLogin(login);
    }
}