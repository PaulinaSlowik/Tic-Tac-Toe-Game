package com.kodilla;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe extends Application {

    private char currentPlayer = 'X';
    private final Cell[] gameBoard = new Cell[9];
    private final Label statusMessage = new Label("X must play");
    private final Random random = new Random();
    List<Integer> freeFields = new ArrayList<>();

    public void changeCurrentPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusMessage.setText(currentPlayer + " must play");
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        GridPane pane = new GridPane(); //do wyświetlenia komórek
        for (int i =0; i<9; i++) { //ustawiam komórki (cell) w pane w ustalonych pozycjach
                gameBoard[i] = new Cell(i);
                int column = getGameBoardColumnIndex(i);
                int row = getGameBoardRowIndex(i);
                pane.add(gameBoard[i], column, row);
        }

        pane.setOnMouseClicked(event -> handleClick(event));


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusMessage);

        Scene scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleClick(MouseEvent event) {
        // todo handle cast error
        Cell clickedCell = (Cell)event.getTarget();
        int clickedCellNumber = clickedCell.getCellNumber();
        makePlayerMove(clickedCellNumber);
        if(checkIfGameIsFinished()) {
            return;
        }

        changeCurrentPlayer();

//        getComputerTurn();
    }

    private boolean checkIfGameIsFinished() {
        if (hasWon(currentPlayer)) {
            // todo
            statusMessage.setText(currentPlayer + " won !");
            return true;
        } else if (isBoardFull()) {
            // todo
            statusMessage.setText("Draw !");
            return true;
        }
        return false;
    }


    private String getImageUrlForPlayer(char player) {
        if (player == 'X') {
            return "file:src/main/resources/cross.png";
        }

        if(player == 'O') {
            return "file:src/main/resources/circle.png";
        }

        return "";
    }

    private void makePlayerMove(int clickedCellNumber) {
        gameBoard[clickedCellNumber].setPlayer(currentPlayer);
        gameBoard[clickedCellNumber].setImage(getImageUrlForPlayer(currentPlayer));
    }



//
//    public int getComputerTurn() {
//        boolean isBoardFull = isBoardFull();
//        for (int i = 1; i <= 9; i++) {
//            if (!isBoardFull) {
//                freeFields.add(i);
//            }
//        }
//        return freeFields.get(random.nextInt(freeFields.size()));
//    }
//

    private int getGameBoardColumnIndex(int cellNumber) {
        return cellNumber % 3;
    }

    private int getGameBoardRowIndex(int cellNumber) {
        if( cellNumber <= 2) {
            return 0;
        }
        if( cellNumber <= 5) {
            return 1;
        }
        return 2;
    }

    public boolean isBoardFull(){
        for (int i=0; i<9; i++){
                if (gameBoard[i].getPlayer()== ' ') {
                    return false;
                }
            }
        return true;
    }

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
        boolean isFirstRowWon = true;
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i].getPlayer() != player) {
                isFirstRowWon = false;
            }
        }

        boolean isSecondRowWon = true;
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i].getPlayer() != player) {
                isSecondRowWon = false;
            }
        }

        boolean isThirdRowWon = true;
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i].getPlayer() != player) {
                isThirdRowWon = false;
            }
        }

        return isFirstRowWon || isSecondRowWon || isThirdRowWon;
    }

    /*ewentualnie getComputerTurn w osobnej klasie
    public static class ComputersLogic {

        private final ApplicationTicTacToe state;
        private final Random random = new Random();

        public ComputersLogic(ApplicationTicTacToe state) {
            this.state = state;
        }

        public int getComputerTurn() {
            List<Integer> freeFields = new ArrayList<>();
            for (int i = 1; i <= 9; i++) {
                if (!state.isBoardFull()) {
                    freeFields.add(i);
                }
            }
            return freeFields.get(random.nextInt(freeFields.size()));
        }
    }*/

    // myślałam aby wrzucić to w this.setOnMouseClicked(event -> handleClick()); a dokłądnie w metodę handleClick,
    // tzn. jak ktos kliknie myszką w pole to po nacisnieciu przycisku na samym koncu można wywolac metode
    // a metoda getComputerTurn losuje sobie pole (randamowo w zależności jaką sobie liczbe wylosowal komputer) i tam ustawia znak O

}