package org.geekhub.oleg.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedbacks(long eventId) {
        return feedbackRepository.getAllFeedbacks(eventId);
    }

    public void postFeedback(Feedback feedback) {
        feedbackRepository.insertFeedback(feedback);
    }

    public void deleteFeedback(long id) {
        feedbackRepository.deleteFeedback(id);
    }
}
