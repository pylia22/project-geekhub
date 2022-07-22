package org.geekhub.oleg.location.repository;

import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.location.model.Location;
import org.geekhub.oleg.location.repository.mapper.LocationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepositoryImpl implements LocationRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final LocationRowMapper locationRowMapper;

    @Autowired
    public LocationRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate , LocationRowMapper locationRowMapper) {
        this.namedParameterJdbcTemplate = jdbcTemplate;
        this.locationRowMapper = locationRowMapper;
    }

    public SqlParameterSource getSqlParamsByModel(Location location) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (location != null) {
            parameterSource.addValue("id", location.getId());
            parameterSource.addValue("city", location.getCity());
        }

        return parameterSource;
    }

    @Override
    public List<Location> getCities() {
        String sql = "SELECT c.id, c.city_name AS city FROM city c";

        return namedParameterJdbcTemplate.query(sql, locationRowMapper);
    }

    @Override
    public Location getCityID(Event event) {
        String cityName = event.getLocation().getCity();
        SqlParameterSource parameterSource = new MapSqlParameterSource("city", cityName);
        String sql = "SELECT c.id, c.city_name AS city " +
                "FROM city c " +
                "WHERE c.city_name = '" + cityName + "';";

        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, locationRowMapper);
    }

    @Override
    public void save(Location location) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO location (country, city) OUTPUT id VALUES (:country, :city)";

        namedParameterJdbcTemplate.update(sql, getSqlParamsByModel(location), keyHolder);
    }

    @Override
    public void update(Event event, long id) {
        String cityName = event.getLocation().getCity();
        String sql = "UPDATE city SET id = :id WHERE city_name = '" + cityName + "';";

        namedParameterJdbcTemplate.update(sql, getSqlParamsByModel(event.getLocation()));
    }
}
