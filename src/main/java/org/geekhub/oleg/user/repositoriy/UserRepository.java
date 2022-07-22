package org.geekhub.oleg.user.repositoriy;

import org.geekhub.oleg.user.model.User;

public interface UserRepository {
    void create(User user);
    User findByLogin(String login);
}
