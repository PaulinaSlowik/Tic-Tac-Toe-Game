package com.kodilla;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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


    @Override
    public void start (Stage primaryStage) throws Exception {
        GridPane pane = new GridPane(); //do wyświetlenia komórek
        for (int i =0; i<9; i++) { //ustawiam komórki (cell) w pane w ustalonych pozycjach
                gameBoard[i] = new Cell();
                int column = getGameBoardColumnIndex(i);
                int row = getGameBoardRowIndex(i);
                pane.add(gameBoard[i], column, row);
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusMessage);

        Scene scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();
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

    public class Cell extends Pane {
        private char player = ' ' ;

        public Cell() {
            setStyle("-fx-border-color: black");
            this.setPrefSize(300,300);
            this.setOnMouseClicked(event -> handleClick());
        }

        private void handleClick() {
            if (player == ' ' && currentPlayer != ' ') {
                setPlayer(currentPlayer);

                if (hasWon(currentPlayer)) {
                    statusMessage.setText(currentPlayer + " won !");
                    currentPlayer = ' ';
                } else if (isBoardFull()) {
                    statusMessage.setText("Draw !");
                    currentPlayer = ' ';
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    statusMessage.setText(currentPlayer + " must play");
                    //getComputerTurn();
                }

            }
        }

        public int getComputerTurn() {
            boolean isBoardFull = isBoardFull();
            for (int i = 1; i <= 9; i++) {
                if (!isBoardFull) {
                    freeFields.add(i);
                }
            }
            return freeFields.get(random.nextInt(freeFields.size()));
        }

        public char getPlayer() {
            return player;
        }
        public void setPlayer(char c) {
            player =c;
            if (player == 'X') {
                /*inny sposób
                Image cross = new Image("file:src/main/resources/cross.png");
                ImageView imageView = new ImageView();
                imageView.setImage(cross);
                imageView.setPreserveRatio(true);
                imageView.setFitWidth(145);
                imageView.setFitHeight(85);
                getChildren().add(imageView);*/
                Image cross = new Image("file:src/main/resources/cross.png", 145, 85, false, false);
                getChildren().add(new ImageView(cross));

            } else if (player == 'O') {
                Image circle = new Image("file:src/main/resources/circle.png", 145, 85, false, false);
                getChildren().add(new ImageView(circle));
            }
        }
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