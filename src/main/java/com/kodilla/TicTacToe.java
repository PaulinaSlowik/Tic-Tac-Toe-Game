package com.kodilla;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private final Button newGame = new Button("New game");
    private final Random random = new Random();
    List<Integer> freeCells = new ArrayList<>();
    GridPane pane = new GridPane(); //do wyświetlenia komórek


    public void changeCurrentPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        statusMessage.setText(currentPlayer + " must play");
    }

    private void createNewGame() {
        TicTacToe game = new TicTacToe();
        try {
            game.start(new Stage());
        } catch (Exception e) {
            System.out.println(e);
        }
        //pane.getChildren().remove(0, 8);
        //prepareGameBoardCells();
    }

    private void prepareGameBoardCells() {
        for (int i = 0; i < 9; i++) { //ustawiam komórki (cell) w pane w ustalonych pozycjach
            freeCells.add(i);
            gameBoard[i] = new Cell(i);
            int column = getGameBoardColumnIndex(i);
            int row = getGameBoardRowIndex(i);
            pane.add(gameBoard[i], column, row);
        }
    }

    @Override
    public void start (Stage primaryStage) throws Exception {

        prepareGameBoardCells();

        pane.setOnMouseClicked(event -> handleClick(event));
        newGame.setOnAction(event -> buttonNewGameClick(event));


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusMessage);
        borderPane.setRight(newGame);

        Scene scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buttonNewGameClick(ActionEvent event){
        createNewGame();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();

        //createNewGame();
    }



    private void handleClick(MouseEvent event) {
        // todo handle cast error
        Cell clickedCell = (Cell)event.getTarget();
        int clickedCellNumber = clickedCell.getCellNumber();
        //clickedCell.setDisable(true);
        makePlayerMove(clickedCellNumber);
        if(checkIfGameIsFinished()) {
            return;
        }

        changeCurrentPlayer();
        makeComputerMove();
    }

    private void makeComputerMove() {
        int cellNumberToBeMarked = getCellNumberToBeMarked();
        makePlayerMove(cellNumberToBeMarked);
        if(checkIfGameIsFinished()) {
            return;
        }
        changeCurrentPlayer();
    }

    private int getCellNumberToBeMarked() {
       return freeCells.get(random.nextInt(freeCells.size())) ;
    }

    private boolean checkIfGameIsFinished() {
        if (hasWon(currentPlayer)) {
            // todo
            statusMessage.setText(currentPlayer + " won! Game is finished. Click New Game");
            pane.setOnMouseClicked(null);
            return true;
        } else if ((!hasWon(currentPlayer)) && (isBoardFull())) {
            // todo
            statusMessage.setText("Draw ! Game is finished. Click New Game");
            pane.setOnMouseClicked(null);
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

    private void makePlayerMove(int cellToBeMarked) {
        gameBoard[cellToBeMarked].setPlayer(currentPlayer);
        gameBoard[cellToBeMarked].setImage(getImageUrlForPlayer(currentPlayer));
        freeCells.remove(new Integer(cellToBeMarked));
    }

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
        return ((gameBoard[0].getPlayer() == player) && (gameBoard[1].getPlayer() == player) && (gameBoard[2].getPlayer() == player))
                || ((gameBoard[3].getPlayer() == player) && (gameBoard[4].getPlayer() == player) && (gameBoard[5].getPlayer() == player))
                || ((gameBoard[6].getPlayer() == player) && (gameBoard[7].getPlayer() == player) && (gameBoard[8].getPlayer() == player));
    }
}