package org.geekhub.oleg.order.repository;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.location.model.Location;
import org.geekhub.oleg.order.model.Order;
import org.geekhub.oleg.order.repository.mapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final NamedParameterJdbcTemplate template;
    private final OrderRowMapper rowMapper;

    @Autowired
    public OrderRepositoryImpl(NamedParameterJdbcTemplate template, OrderRowMapper rowMapper) {
        this.template = template;
        this.rowMapper = rowMapper;
    }

    public SqlParameterSource getSqlParamsByModel(Order order) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (order != null) {
            parameterSource.addValue("event_id", order.getEvent().getId());
            parameterSource.addValue("user_id", order.getUser().getId());
            parameterSource.addValue("quantity", order.getQuantity());
        }

        return parameterSource;
    }

    @Override
    public void purchaseEvent(Order order) {
        String sql = "INSERT INTO orders(event_id, user_id, quantity) VALUES(:event_id, :user_id, :quantity)";

        template.update(sql, getSqlParamsByModel(order));
    }

    public List<Order> getOrdersByUserId(long userId) {
        SqlParameterSource parameter = new MapSqlParameterSource("userId", userId);

        String sql = "SELECT e.name, c.city_name, e.date, e.price, e.category, e.description, e.image, c.city_name as city, " +
                "o.id, o.quantity, u.id, u.first_name, u.email, u.login, u.password, u.role_id, u.last_name " +
                "FROM orders o " +
                "JOIN users u ON u.id = o.user_id " +
                "JOIN event e ON e.id = o.event_id " +
                "JOIN city c ON c.id = e.city_id " +
                "WHERE u.id = :userId " +
                "ORDER BY e.date DESC";

        return template.query(sql, parameter, rowMapper);
    }

    @Override
    public List<Order> getOrdersStatistic() {
        String sql = "SELECT e.name, SUM(o.quantity) AS quantity, e.price, c.city_name AS city " +
                "FROM orders o " +
                "JOIN users u ON u.id = o.user_id " +
                "JOIN event e ON e.id = o.event_id " +
                "JOIN city c ON c.id = e.city_id " +
                "GROUP BY e.name, e.price, c.city_name " +
                "ORDER BY quantity DESC";

        return template.query(sql, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                Order order = new Order();
                Event event = new Event();
                Location location = new Location();

                location.setCity(rs.getString("city"));
                event.setEventName(rs.getString("name"));
                event.setPrice(rs.getDouble("price"));
                event.setLocation(location);

                order.setQuantity(rs.getInt("quantity"));
                order.setEvent(event);
                return order;
            }
        });
    }

    @Override
    public List<Order> getLastMonthOrders() {
        LocalDateTime currentMonth = LocalDateTime.now().minusMonths(1);

        String sql = "SELECT e.name, c.city_name, e.date, e.price, e.category, e.description, e.image, c.city_name as city, " +
                "o.id, o.quantity, u.id, u.first_name, u.email, u.login, u.password, u.role_id, u.last_name " +
                "FROM orders o " +
                "JOIN users u on u.id = o.user_id " +
                "JOIN event e on e.id = o.event_id " +
                "JOIN city c on c.id = e.city_id " +
                "WHERE e.date > '" + currentMonth + "'" +
                "ORDER BY date DESC";

        return template.query(sql, rowMapper);
    }

    @Override
    public List<Order> getLastOrder() {
        String sql = "SELECT e.name, c.city_name, e.date, e.price, e.category, e.description, e.image, c.city_name as city, " +
                "o.quantity, u.id, u.first_name, u.email, u.login, u.password, u.role_id, u.last_name " +
                "FROM orders o " +
                "JOIN users u ON u.id = o.user_id " +
                "JOIN event e ON e.id = o.event_id " +
                "JOIN city c ON c.id = e.city_id " +
                "ORDER BY o.id DESC LIMIT 1";

        return template.query(sql, rowMapper);
    }

    @Override
    public void refundTicket(long id) {
        SqlParameterSource parameter = new MapSqlParameterSource("id", id);
        String sql = "DELETE FROM orders WHERE id = :id";

        template.update(sql, parameter);
    }

    @Override
    public Order getOrderById(long id) {
        SqlParameterSource parameter = new MapSqlParameterSource("id", id);

        String sql = "SELECT e.name, c.city_name, e.date, e.price, e.category, e.description, e.image, c.city_name as city, " +
                "o.id, o.quantity, u.id, u.first_name, u.email, u.login, u.password, u.role_id, u.last_name " +
                "FROM orders o " +
                "JOIN users u ON u.id = o.user_id " +
                "JOIN event e ON e.id = o.event_id " +
                "JOIN city c ON c.id = e.city_id " +
                "WHERE o.id = :id ";

        return template.queryForObject(sql, parameter, rowMapper);
    }
}
