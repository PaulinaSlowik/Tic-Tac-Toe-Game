package com.kodilla;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class Game {

    private final FlowPane flow;
    private Button[][] button = new Button[3][3];
    boolean winningConditions = false;
    boolean endingConditions = false;

    public Game(FlowPane flow) {
        this.flow = flow;
    }

    public void showBoard() {
        final int[] counter = {0};
        for (int row =0; row < 3; row++)
        {
            for (int col = 0; col < 3; col++) {
                Button button = new Button("");
                button.setPrefHeight(120);
                button.setPrefWidth(120);

                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        //jeżeli counter będzie liczbą parzystą
                        if (counter[0] % 2 == 0) {
                            //Buttons.CROSS
                            button.setText("X");
                            checkForEndingConditions();
                        } else {
                            button.setText("O");
                            checkForEndingConditions();
                        }
                        button.setDisable(true);
                        counter[0]++;

                        /*if (button[0] == button[1])
                            if (button[1] == button[2]) {
                                winningConditions = true;
                            }*/

                        //button.setText("Accepted");
                        //System.out.println("Kliknięto mnie");
                    }
                });
                flow.getChildren().add(button);
            }
        }
        if(winningConditions== true || endingConditions == true) {
            displayRestartWindow();
        }
    }

    public void checkForEndingConditions() {
        for (int x = 0; x < 3; x++) {
            if ((button[0]==button[1])&&(button[1]==button[2])){
                winningConditions = true;
            }
        }
/*
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
        }*/
    }

    public void displayRestartWindow(){
        RestartWindow restartWindow = new RestartWindow(this);
        restartWindow.displayWindow();
    }

    public void restartGame() {
        winningConditions = false;
        endingConditions = false;
        showBoard();
    }
}
