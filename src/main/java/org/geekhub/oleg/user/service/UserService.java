package org.geekhub.oleg.user.service;

import org.geekhub.oleg.user.model.User;

public interface UserService {
    void createUser(User user);

    User findByLogin(String login);
}
