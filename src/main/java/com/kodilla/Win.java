package com.kodilla;

public class Win {
    public final Cell[] gameBoard = new Cell[9];

    public boolean hasWon(char player) {
        return isHorizontalWon(player) || isVerticalWon(player) || isObliqueWon(player);
    }

    private boolean isVerticalWon(char player) {
        return ((gameBoard[0].getPlayer() == player) && (gameBoard[3].getPlayer() == player) && (gameBoard[6].getPlayer() == player))
                || ((gameBoard[1].getPlayer() == player) && (gameBoard[4].getPlayer() == player) && (gameBoard[7].getPlayer() == player))
                || ((gameBoard[2].getPlayer() == player) && (gameBoard[5].getPlayer() == player) && (gameBoard[8].getPlayer() == player));
    }

    private boolean isObliqueWon(char player) {
        return ((gameBoard[0].getPlayer() == player) && (gameBoard[4].getPlayer() == player) && (gameBoard[8].getPlayer() == player))
                || ((gameBoard[2].getPlayer() == player) && (gameBoard[4].getPlayer() == player) && (gameBoard[6].getPlayer() == player));
    }


    private boolean isHorizontalWon(char player) {
        return ((gameBoard[0].getPlayer() == player) && (gameBoard[1].getPlayer() == player) && (gameBoard[2].getPlayer() == player))
                || ((gameBoard[3].getPlayer() == player) && (gameBoard[4].getPlayer() == player) && (gameBoard[5].getPlayer() == player))
                || ((gameBoard[6].getPlayer() == player) && (gameBoard[7].getPlayer() == player) && (gameBoard[8].getPlayer() == player));
    }
}