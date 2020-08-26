package com.kodilla;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ApplicationTicTacToe extends Application {

    private char currentPlayer = 'X';
    private final Cell[][] cell = new Cell[3][3];
    private final Label statusMessage = new Label("X must play");

    @Override
    public void start (Stage primaryStage) throws Exception {
        GridPane pane = new GridPane(); //do wyświetlenia komórek
        for (int i =0; i<3; i++) { //ustawiamy komórki (cell) w pane w ustalonych pozycjach
            for (int j=0; j<3; j++) {
                cell[i][j] = new Cell();
                pane.add(cell[i][j],j,i);
            }
        }

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusMessage);

        Scene scene = new Scene(borderPane, 450, 300);
        primaryStage.setTitle("Tc Tac Toe Game");
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

    /*
     * Metoda zwracająca pole, które jest zwycięskie dla danego gracza
     *
     * Metoda pobiera jeden argument - obiekt typu Gracz (może to być np. Komputer lub Człowiek)
     * Metoda zwraca liczbę określającą pole, które gracz powinien zakreślić żeby wygrać pojedynek
     * Jeśli metoda zwróci liczbę od 0 do 8 to oznacza to, że istnieje zwycięski ruch dzięki któremu
     * gracz może wygrać w tym ruchu pojedynek. Jeśli natomiast metoda zwróci liczbę -1 to oznacza to, że
     * nie istnieje w tym momencie możliwość zwycięskiego zakończenia pojedynku przez gracza.
     */
	/*public int podajZwycieskiRuch(char player){

        for (int i = 0; i < 3; i++) {
            if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player) {
                return cell[i][2].getPlayer();
            }
            else if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == ' ' && cell[i][2].getPlayer() == player) {
                return cell[i][1].getPlayer();
            }
            else if (cell[i][0].getPlayer() == ' ' && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player) {
                return cell[i][0].getPlayer();
            }
        return -1; //komputer nie ma mozliwosci wygrania w nastepnej rundzie
	}*/

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


        public char getPlayer() {
            return player;
        }
        public void setPlayer(char c) {
            player =c;
            if (player == 'X') {
                /*Image cross = new Image("file:src/main/resources/cross.png");
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
    public class Computer{

    }
}
