package com.kodilla;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Cell extends Pane {
    private char player = ' ' ;
    private int cellNumber;

    public Cell(int cellNumber) {
        this.cellNumber = cellNumber;
        setStyle("-fx-border-color: black");
        this.setPrefSize(300,300);
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
        Image image = new Image(imageUrl, 145, 85, false, false);
        getChildren().add(new ImageView(image));
    }
}