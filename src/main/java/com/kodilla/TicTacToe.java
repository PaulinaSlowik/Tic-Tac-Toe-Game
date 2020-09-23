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

    public static Player player = new Player();
    public static Win win = new Win();
    private final Label statusMessage = new Label("X must play");
    private final Button newGame = new Button("New game");
    private final Random random = new Random();
    List<Integer> freeCells = new ArrayList<>();
    GridPane pane = new GridPane(); //do wyświetlenia komórek

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane borderPane = new BorderPane();
        borderPane.setBottom(statusMessage);
        borderPane.setRight(newGame);
        prepareGameBoardCells();
        borderPane.setCenter(pane);
        pane.setOnMouseClicked(event -> handleClick(event));
        newGame.setOnAction(event -> buttonNewGameClick(event));

        Scene scene = new Scene(borderPane, 450, 300);

        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void buttonNewGameClick(ActionEvent event) {
        createNewGame();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    private void handleClick(MouseEvent event) {
        // todo handle cast error
        Cell clickedCell = (Cell) event.getTarget();
        int clickedCellNumber = clickedCell.getCellNumber();
        //clickedCell.setDisable(true);
        makePlayerMove(clickedCellNumber);
        if (checkIfGameIsFinished()) {
            return;
        }

        player.changeCurrentPlayer();
        statusMessage.setText(player.currentPlayer + " must play");

        makeComputerMove();
    }

    private void createNewGame() {
        TicTacToe game = new TicTacToe();
        try {
            game.start(new Stage());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void prepareGameBoardCells() {
        for (int i = 0; i < 9; i++) { //ustawiam komórki (cell) w pane w ustalonych pozycjach
            freeCells.add(i);
            win.gameBoard[i] = new Cell(i);
            int column = getGameBoardColumnIndex(i);
            int row = getGameBoardRowIndex(i);
            pane.add(win.gameBoard[i], column, row);
        }
    }

    private void makeComputerMove() {
        int cellNumberToBeMarked = getCellNumberToBeMarked();
        makePlayerMove(cellNumberToBeMarked);
        if (checkIfGameIsFinished()) {
            return;
        }
        player.changeCurrentPlayer();
        statusMessage.setText(player.currentPlayer + " must play");
    }

    private int getCellNumberToBeMarked() {
        return freeCells.get(random.nextInt(freeCells.size()));
    }

    private boolean checkIfGameIsFinished() {
        if (win.hasWon(player.currentPlayer)) {
            // todo
            statusMessage.setText(player.currentPlayer + " won! Game is finished. Click New Game");
            pane.setOnMouseClicked(null);
            return true;
        } else if ((!win.hasWon(player.currentPlayer)) && (isBoardFull())) {
            // todo
            statusMessage.setText("Draw ! Game is finished. Click New Game");
            pane.setOnMouseClicked(null);
            return true;
        }
        return false;
    }

    private void makePlayerMove(int cellToBeMarked) {
        win.gameBoard[cellToBeMarked].setPlayer(player.currentPlayer);
        win.gameBoard[cellToBeMarked].setImage(player.getImageUrlForPlayer(player.currentPlayer));
        freeCells.remove(new Integer(cellToBeMarked));
    }

    private int getGameBoardColumnIndex(int cellNumber) {
        return cellNumber % 3;
    }

    private int getGameBoardRowIndex(int cellNumber) {
        if (cellNumber <= 2) {
            return 0;
        }
        if (cellNumber <= 5) {
            return 1;
        }
        return 2;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 9; i++) {
            if (win.gameBoard[i].getPlayer() == ' ') {
                return false;
            }
        }
        return true;
    }
}