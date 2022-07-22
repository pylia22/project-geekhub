package org.geekhub.oleg.feedback;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackRowMapper implements RowMapper<Feedback> {
    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Feedback feedback = new Feedback();
        feedback.setId(rs.getLong("id"));
        feedback.setEventId(rs.getLong("event_id"));
        feedback.setUserName(rs.getString("user_name"));
        feedback.setMessage(rs.getString("message"));
        feedback.setDate(rs.getTimestamp("date").toLocalDateTime());

        return feedback;
    }
}
