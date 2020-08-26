package com.kodilla;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class TicTacToe extends Application {

    int counter =0;

    @Override
    public void start(Stage primaryStage) {

        FlowPane flow = new FlowPane();
        //flow.setVgap(9);
        //flow.setHgap(9);
        for (int i=1; i<=9; i++){
            Button button =new Button("");
            button.setPrefHeight(120);
            button.setPrefWidth(120);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    //jeżeli counter będzie liczbą parzystą
                    if (counter %2 ==0){
                        //Buttons.CROSS
                        button.setText("X");
                    }
                    else{
                        button.setText("O");
                    }
                    button.setDisable(true);
                    counter++;
                    //button.setText("Accepted");
                    //System.out.println("Kliknięto mnie");
                }
            });
            flow.getChildren().add(button);

            //8 możliwości wygranej gry
            if      ((positions[0]!=empty)&&(positions[0]==positions[1])&&(positions[1]==positions[2]) ||
                    ((positions[3]!=empty)&&(positions[3]==positions[4])&&(positions[4]==positions[5]) ||
                    ((positions[6]!=empty)&&(positions[6]==positions[7])&&(positions[7]==positions[8]) ||
                    ((positions[0]!=empty)&&(positions[0]==positions[3])&&(positions[3]==positions[6]) ||
                    ((positions[1]!=empty)&&(positions[1]==positions[4])&&(positions[4]==positions[7]) ||
                    ((positions[2]!=empty)&&(positions[2]==positions[5])&&(positions[5]==positions[8]) ||
                    ((positions[0]!=empty)&&(positions[0]==positions[4])&&(positions[4]==positions[8]) ||
                    ((positions[2]!=empty)&&(positions[2]==positions[4])&&(positions[4]==positions[6])
                    {
                System.out.println("Win!");
            }
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

