/*package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Zgadywanka extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Zgadywanka");
        FlowPane flow = new FlowPane();
        flow.setVgap(8);
        flow.setHgap(4);
        int id;
        for (int i = 1; i <= 8; i++) {
            Button button = new Button("Hello");
            button.setPrefHeight(120);
            button.setPrefWidth(120);
            id = i<=4 ? i : i-4;
            button.setId(String.valueOf(new Integer(id)));
            Image image = new Image(getClass().getResourceAsStream("/"+id+"back.png"));
            button.setGraphic(new ImageView(image));
            flow.getChildren().add(button);
        }
        primaryStage.setScene(new Scene(flow, 500, 300));
        primaryStage.show();
    }
    public static void main(String[] args) {

        launch(args);

    }
}
*/