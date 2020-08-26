package com.kodilla;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RestartWindow {

    private final Game game;

    public RestartWindow(Game game){
        this.game= game;
    }

    public void displayWindow() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label();
        Button button = new Button();
        if(game.winningConditions == true){
            window.setTitle("We have a winner");
            button.setText("Restart game");
        }

        if(game.endingConditions == true) {
            window.setTitle("We have a tie");
            label.setText("There was a tie between Player X and Player O");
            button.setText("Restart game");
        }

        button.setOnAction(e -> {
            window.close();
            game.restartGame();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 300, 100);
        window.setScene(scene);
        window.showAndWait();
    }
}
