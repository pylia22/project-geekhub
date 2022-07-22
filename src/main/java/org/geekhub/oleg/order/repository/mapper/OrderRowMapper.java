package org.geekhub.oleg.order.repository.mapper;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.event.repositoriy.mapper.EventRowMapper;
import org.geekhub.oleg.order.model.Order;
import org.geekhub.oleg.user.model.User;
import org.geekhub.oleg.user.repositoriy.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderRowMapper implements RowMapper<Order> {
    private final EventRowMapper eventRowMapper;
    private final UserRowMapper userRowMapper;

    @Autowired
    public OrderRowMapper(EventRowMapper eventRowMapper, UserRowMapper userRowMapper) {
        this.eventRowMapper = eventRowMapper;
        this.userRowMapper = userRowMapper;
    }

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("id"));
        order.setQuantity(rs.getInt("quantity"));

        User user = userRowMapper.mapRow(rs, rowNum);
        order.setUser(user);

        Event event = eventRowMapper.mapRow(rs, rowNum);
        order.setEvent(event);

        return order;
    }
}
