package com.kodilla;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Cell extends Pane {
    TicTacToe ticTacToe = new TicTacToe();
    private char player = ' ';
    private int cellNumber;

    public Cell(int cellNumber) {
        this.cellNumber = cellNumber;
        setStyle("-fx-border-color: black");
        this.setPrefSize(300, 300);
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public void setImage(String imageUrl) {
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(100);
        imageView.setFitHeight(50);
        getChildren().add(imageView);

//        Image image = new Image(imageUrl, 100, 50, false, false);
//        getChildren().add(new ImageView(image));
    }
}