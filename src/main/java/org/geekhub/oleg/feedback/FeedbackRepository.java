package org.geekhub.oleg.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedbackRepository {
    private final NamedParameterJdbcTemplate template;
    private final FeedbackRowMapper feedBackRowMapper;

    @Autowired
    public FeedbackRepository(NamedParameterJdbcTemplate template, FeedbackRowMapper feedBackRowMapper) {
        this.template = template;
        this.feedBackRowMapper = feedBackRowMapper;
    }

    public SqlParameterSource getSqlParamsByModel(Feedback feedback) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        if (feedback != null) {
            parameterSource.addValue("event_id", feedback.getEventId());
            parameterSource.addValue("user_name", feedback.getUserName());
            parameterSource.addValue("message", feedback.getMessage());
            parameterSource.addValue("date", feedback.getDate());
        }
        return parameterSource;
    }

    public List<Feedback> getAllFeedbacks(long eventId) {
        SqlParameterSource parameter = new MapSqlParameterSource("eventId", eventId);

        String sql = "SELECT f.id, f.event_id, f.user_name, f.message, f.date " +
                "FROM feedback f " +
                "WHERE f.event_id = :eventId";

        return template.query(sql, parameter, feedBackRowMapper);
    }

    public void insertFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback (event_id, user_name, message, date) " +
                "VALUES (:event_id, :user_name, :message, :date)";

        template.update(sql, getSqlParamsByModel(feedback));
    }

    public void deleteFeedback(long id) {
        String sql = "DELETE FROM feedback f WHERE f.id = '" + id + "'";

        template.update(sql, new MapSqlParameterSource("message", id));
    }
}
