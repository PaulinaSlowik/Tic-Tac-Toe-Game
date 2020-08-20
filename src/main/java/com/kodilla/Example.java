package com.kodilla;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Example extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int random = (int)(Math.random() * 3);
                if (random != 2) {
                    String text = (random > 0) ? "file:src/main/resources/cross.png" : "file:src/main/resources/circle.png";
                    pane.add(new ImageView(new Image(text)), j, i);
                }
            }
        }
        Scene scene = new Scene(pane, 150, 150);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);

    }
}

