package com.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

interface Command {
    void execute() throws Exception;
}

class ShowPromotionsCommand implements Command {
    @Override
    public void execute() throws Exception {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Promotions");
        Parent promoRoot = FXMLLoader.load(getClass().getResource("/com/views/PromotionsView.fxml"));
        Stage stage = new Stage();
        popupStage.setScene(new Scene(promoRoot));
        popupStage.showAndWait();
    }
}

public class MainController {

    @FXML
    public void showPromotions() {
        executeCommand(new ShowPromotionsCommand());
    }

    @FXML
    public void openOrderView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/views/OrderView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Customize Your Pizza");
        stage.show();
    }

    @FXML
    public void openFeedbackView() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/views/FeedbackView.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Feedback");
        stage.show();
    }

    private void executeCommand(Command command) {
        try {
            command.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
