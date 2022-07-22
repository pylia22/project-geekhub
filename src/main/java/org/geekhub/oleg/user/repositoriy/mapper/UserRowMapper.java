package org.geekhub.oleg.user.repositoriy.mapper;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class UserRowMapper implements RowMapper<User> {

    public SqlParameterSource getSqlParamsByModel(User user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (user != null) {
            parameterSource.addValue("id", user.getId());
            parameterSource.addValue("first_name", user.getFirstName());
            parameterSource.addValue("last_name", user.getLastName());
            parameterSource.addValue("email", user.getEmail());
            parameterSource.addValue("login", user.getLogin());
            parameterSource.addValue("password", user.getPassword());
        }

        return parameterSource;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRoleID(rs.getInt("role_id"));

        return user;
    }
}
