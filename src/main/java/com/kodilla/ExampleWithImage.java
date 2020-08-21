package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExampleWithImage  extends Application {

    @Override
    public void start(Stage primaryStage) {

        FlowPane flow = new FlowPane();
        flow.setVgap(9);
        flow.setHgap(9);
        for (int i=1; i<=9; i++){
            Button button =new Button("");
            button.setPrefHeight(120);
            button.setPrefWidth(120);
            //dodawanie zdjecia do przycisków oraz informacja w konsoli że kliknięto w przycisk
            Image image = new Image("file:src/main/resources/question-mark.png");
            button.setGraphic(new ImageView(image));
            button.setOnAction(event -> {
                        System.out.println("Kliknięto mnie");
            });
            flow.getChildren().add(button);
        }

        Scene scene = new Scene(flow, 450, 500);
        primaryStage.setTitle("Tic Tac Toe");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);

    }
}


