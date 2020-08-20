package com.kodilla;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        for (int i=1; i<=9; i++){
            Button button =new Button("Click me");
            pane.getChildren().add(button);
        }

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Tic Tac Toe");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        Application.launch(args);

    }
}

