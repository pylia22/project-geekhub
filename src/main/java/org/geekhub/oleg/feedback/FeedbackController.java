package org.geekhub.oleg.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("event/{id}/feedback")
    public String postFeedback(@PathVariable("id") long id, @RequestParam("message") String message) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Feedback feedback = new Feedback(id, login, message, LocalDateTime.now());
        feedbackService.postFeedback(feedback);

        return "redirect:/event/" + id;
    }

    @GetMapping("event/{id}/feedback/{feedbackId}/delete")
    public String deleteFeedback(@PathVariable("id") long id, @PathVariable("feedbackId") long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);

        return "redirect:/event/" + id;
    }
}
