package org.geekhub.oleg.event.repositoriy;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.event.repositoriy.mapper.EventRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class EventRepositoryImpl implements EventRepository<Event> {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final EventRowMapper eventRowMapper;

    @Autowired
    public EventRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, EventRowMapper eventRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventRowMapper = eventRowMapper;
    }

    public SqlParameterSource getSqlParamsByModel(Event event) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (event != null) {
            parameterSource.addValue("id", event.getId());
            parameterSource.addValue("name", event.getEventName());
            parameterSource.addValue("category", String.valueOf(event.getCategory()));
            parameterSource.addValue("description", event.getDescription());
            parameterSource.addValue("date", Timestamp.valueOf(event.getDate()));
            parameterSource.addValue("city_id", event.getLocation().getId());
            parameterSource.addValue("price", event.getPrice());
            parameterSource.addValue("image", event.getImageBytes());
        }

        return parameterSource;
    }

    @Override
    public List<Event> getAllEvents() {
        String sql = "SELECT e.id, e.name, category, description, date, c.city_name as city, price, image " +
                "FROM event e " +
                "JOIN city c ON c.id = e.city_id " +
                "ORDER BY e.date ASC";

        return jdbcTemplate.query(sql, eventRowMapper);
    }

    @Override
    public Event getEventById(long id) {
        SqlParameterSource parameter = new MapSqlParameterSource("id", id);

        String sql = "SELECT e.id, e.name, category, description, date, c.city_name AS city, price, image " +
                "FROM event e " +
                "JOIN city c ON c.id = e.city_id " +
                "WHERE e.id = :id";

        return jdbcTemplate.queryForObject(sql, parameter, eventRowMapper);
    }

    @Override
    public List<Event> getEventsByCategory(String category) {
        SqlParameterSource parameter = new MapSqlParameterSource("category", category);

        String sql = "SELECT e.id, e.name, category, description, date, c.city_name AS city, price, image " +
                "FROM event e " +
                "JOIN city c ON c.id = e.city_id " +
                "WHERE category= :category";

        return jdbcTemplate.query(sql, parameter, eventRowMapper);
    }

    @Override
    public List<Event> getEventsByCity(String city) {
        SqlParameterSource parameter = new MapSqlParameterSource("city", city);

        String sql = "SELECT e.id, e.name, category, description, date, c.city_name AS city, price, image " +
                "FROM event e " +
                "JOIN city c ON c.id = e.city_id " +
                "WHERE c.city_name= :city";

        return jdbcTemplate.query(sql, parameter, eventRowMapper);
    }

    @Override
    public List<Event> getAvailableCities() {
        String sql = "SELECT DISTINCT ON(c.city_name) city_name AS city, e.id, e.name, category, description, date, price, image " +
                "FROM event e " +
                "JOIN city c ON c.id = e.city_id";

        return jdbcTemplate.query(sql, eventRowMapper);
    }

    @Override
    public void create(Event event) {
        String sql = "INSERT INTO event(name, category, description, date, city_id, price, image) " +
                "VALUES (:name, :category, :description, :date, :city_id, :price, :image)";

        jdbcTemplate.update(sql, getSqlParamsByModel(event));
    }

    @Override
    public void update(Event event, long id) {
        String sql = "UPDATE event SET name = :name, category = :category, description = :description, " +
                "date = :date, city_id = :city_id, price = :price, image = :image " +
                "WHERE id = :id";

        jdbcTemplate.update(sql, getSqlParamsByModel(event));
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM event WHERE id = :id";

        jdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    @Override
    public List<Event> searchEvent(String searchValue) {
        SqlParameterSource parameter = new MapSqlParameterSource("searchValue", searchValue);

        String sql = "SELECT e.id, e.name, category, description, date, c.city_name AS city, price, image " +
                "FROM event e " +
                "JOIN city c ON c.id = e.city_id " +
                "WHERE e.name ILIKE '%" + searchValue + "%'";

        return jdbcTemplate.query(sql, parameter, eventRowMapper);
    }
}
