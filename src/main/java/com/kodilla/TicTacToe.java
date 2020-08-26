package com.kodilla;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class TicTacToe extends Application {

    private static Game game;
    //public static Button reset;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //reset = new Button ("Reset");

        //reset.setTranslateX(0);
        //reset.setTranslateY(350);
        //reset.setFont(new Font("Arial", 15));

        FlowPane flow = new FlowPane();
        flow.setAlignment(Pos.CENTER);

        //flow.getChildren().add(reset);

        Scene scene = new Scene(flow, 450, 500);
        primaryStage.setTitle("Tic Tac Toe");

        primaryStage.setScene(scene);
        primaryStage.show();
        game= new Game(flow);
        game.showBoard();
        game.checkForEndingConditions();

    }
}




