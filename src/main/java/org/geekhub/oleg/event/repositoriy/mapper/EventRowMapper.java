package org.geekhub.oleg.event.repositoriy.mapper;

import org.geekhub.oleg.event.model.Category;
import org.geekhub.oleg.event.model.Event;
import org.geekhub.oleg.location.model.Location;
import org.geekhub.oleg.location.repository.mapper.LocationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Base64;
import java.util.Locale;

@Component
public class EventRowMapper implements RowMapper<Event> {
    private final LocationRowMapper locationRowMapper;

    @Autowired
    public EventRowMapper(LocationRowMapper locationRowMapper) {
        this.locationRowMapper = locationRowMapper;
    }

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .toFormatter(Locale.ENGLISH);

        Event event = new Event();
        event.setId(rs.getLong("id"));
        event.setEventName(rs.getString("name"));
        event.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
        event.setDescription(rs.getString("description"));
        event.setDate(rs.getTimestamp("date").toLocalDateTime(), df);
        event.setPrice(rs.getDouble("price"));
        event.setImage(Base64.getEncoder().encodeToString(rs.getBytes("image")));

        Location location = locationRowMapper.mapRow(rs, rowNum);
        event.setLocation(location);

        return event;
    }
}
