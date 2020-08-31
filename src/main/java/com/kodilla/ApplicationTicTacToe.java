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

public class ApplicationTicTacToe extends Application {

    private char currentPlayer = 'X';
    private final Cell[][] cell = new Cell[3][3];
    private final Label statusMessage = new Label("X must play");
    private final Random random = new Random();
    List<Integer> freeFields = new ArrayList<>();


    @Override
    public void start (Stage primaryStage) throws Exception {
        GridPane pane = new GridPane(); //do wyświetlenia komórek
        for (int i =0; i<3; i++) { //ustawiam komórki (cell) w pane w ustalonych pozycjach
            for (int j=0; j<3; j++) {
                cell[i][j] = new Cell();
                pane.add(cell[i][j],j,i);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusMessage);

        Scene scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Tic Tac Toe Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean isBoardFull(){
        for (int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if (cell[i][j].getPlayer()== ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player && cell[2][i].getPlayer() == player) {
                return true;
            }
        }
        if (cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player) {
            return true;
        }
        if (cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player) {
            return true;
        }
        return false;
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
                }
            }
        }

        public int getComputerTurn() {
            for (int i = 1; i <= 9; i++) {
                if (!isBoardFull()) {
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
    /*getComputerTurn w osobnej klasie
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