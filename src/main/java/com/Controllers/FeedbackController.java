package com.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

interface FeedbackListener {
    void onFeedbackSubmitted(String feedback, int rating);
}


abstract class FeedbackValidator {
    private FeedbackValidator next;

    public FeedbackValidator linkWith(FeedbackValidator next) {
        this.next = next;
        return next;
    }

    public boolean validate(String feedback, int rating) {
        if (check(feedback, rating)) {
            if (next != null) {
                return next.validate(feedback, rating);
            }
            return true;
        }
        return false;
    }

    protected abstract boolean check(String feedback, int rating);
}

class EmptyFeedbackValidator extends FeedbackValidator {
    @Override
    protected boolean check(String feedback, int rating) {
        if (feedback == null || feedback.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Feedback cannot be empty.");
            alert.show();
            return false;
        }
        return true;
    }
}

class RatingRangeValidator extends FeedbackValidator {
    @Override
    protected boolean check(String feedback, int rating) {
        if (rating < 1 || rating > 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Rating must be between 1 and 5.");
            alert.show();
            return false;
        }
        return true;
    }
}

public class FeedbackController {
    @FXML
    private TextArea feedbackTextArea;

    @FXML
    private Slider ratingSlider;

    private final List<FeedbackListener> feedbackListeners = new ArrayList<>();

    private final FeedbackValidator validatorChain;

    public FeedbackController() {
        validatorChain = new EmptyFeedbackValidator();
        validatorChain.linkWith(new RatingRangeValidator());
    }

    public void addFeedbackListener(FeedbackListener listener) {
        feedbackListeners.add(listener);
    }

    @FXML
    public void submitFeedback() {
        String feedback = feedbackTextArea.getText();
        int rating = (int) ratingSlider.getValue();

        if (!validatorChain.validate(feedback, rating)) {
            return; // Stop if validation fails
        }

        notifyFeedbackListeners(feedback, rating);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Feedback Submitted");
        alert.setHeaderText("Thank you for your feedback!");
        alert.setContentText("Rating: " + rating + "/5\nFeedback: " + feedback);
        alert.show();

        feedbackTextArea.clear();
        ratingSlider.setValue(1);
    }

    private void notifyFeedbackListeners(String feedback, int rating) {
        for (FeedbackListener listener : feedbackListeners) {
            listener.onFeedbackSubmitted(feedback, rating);
        }
    }
}
