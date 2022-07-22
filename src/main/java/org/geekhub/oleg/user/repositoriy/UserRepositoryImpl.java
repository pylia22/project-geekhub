package org.geekhub.oleg.user.repositoriy;

import org.geekhub.oleg.user.model.User;
import org.geekhub.oleg.user.repositoriy.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcTemplate template;
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    @Autowired
    public UserRepositoryImpl(NamedParameterJdbcTemplate template, JdbcTemplate jdbcTemplate, UserRowMapper rowMapper) {
        this.template = template;
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email, login, password) " +
                "VALUES(:first_name, :last_name, :email, :login, :password)";

        template.update(sql, rowMapper.getSqlParamsByModel(user));
    }

    @Override
    public User findByLogin(String login) {
        String sql = "SELECT u.id, u.first_name, u.last_name, u.email, u.login, u.password, u.role_id " +
                "FROM users u " +
                "WHERE UPPER (u.login) LIKE '" + login.toUpperCase() + "'";

        return jdbcTemplate.queryForObject(sql, rowMapper);
    }
}
