package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {

        FlowPane flow = new FlowPane();
        flow.setVgap(9);
        flow.setHgap(3);
        for (int i=1; i<=9; i++){
            Button button =new Button("Click me");
            button.setPrefHeight(120);
            button.setPrefWidth(120);
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

