package org.geekhub.oleg.location.repository.mapper;

import org.geekhub.oleg.location.model.Location;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LocationRowMapper implements RowMapper<Location> {

    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setId(rs.getLong("id"));
        location.setCity(rs.getString("city"));

        return location;
    }
}
