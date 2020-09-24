package com.kodilla;

public class Player {
    public char currentPlayer = 'X';

    public void changeCurrentPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public String getImageUrlForPlayer(char player) {
        if (player == 'X') {
            return "file:src/main/resources/x.png";
        }

        if (player == 'O') {
            return "file:src/main/resources/o.png";
        }
        return "";
    }
}