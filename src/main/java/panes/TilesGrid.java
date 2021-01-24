package main.java.panes;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import main.java.Controller;
import main.java.alibis.Alibi;
import main.java.tiles.AreaTile;
import main.java.tiles.OuterTiles;


public class TilesGrid extends GridPane {

    TilesGrid() {
        initTiles();
        setAlignment(Pos.CENTER);
    }

    public void initTiles() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if ((i == 0 || i == 4) ^ (j == 0 || j == 4)) {
                    OuterTiles sideTile = new OuterTiles();
                    sideTile.fitHeightProperty().bind(prefHeightProperty().divide(5));
                    sideTile.fitWidthProperty().bind(prefWidthProperty().divide(5));
                    add(sideTile, i, j);
                }
                if (i > 0 && i < 4 && j > 0 && j < 4) {
                    AreaTile areaTile = new AreaTile((i - 1) * 3 + j);
                    if (j == 1) {
                        if (i == 1) {
                            areaTile.setOrientation(1);
                        }
                        if (i == 3) areaTile.setOrientation(3);
                    }
                    if (i == 2 && j == 3) areaTile.setOrientation(2);
                    areaTile.fitHeightProperty().bind(prefHeightProperty().divide(5));
                    areaTile.fitWidthProperty().bind(prefWidthProperty().divide(5));
                    add(areaTile, i, j);
                }
            }
        }
    }

    public AreaTile[][] getAreaTiles() {
        AreaTile[][] areaTiles = new AreaTile[3][3];
        for (Node node : getChildren())
            if (node instanceof AreaTile) {
                areaTiles[getColumnIndex(node) - 1][getRowIndex(node) - 1] = (AreaTile) node;
            }
        return areaTiles;
    }

    public AreaTile getAreaTile(Alibi alibi) {
        for (Node node : getChildren())
            if (node instanceof AreaTile) {
                AreaTile areaTile = (AreaTile) node;
                if (areaTile.getAlibi() == alibi) return areaTile;
            }
        return null;
    }

    public void switchTiles(AreaTile tile1, AreaTile tile2) {
        int col1 = getColumnIndex(tile1);
        int row1 = getRowIndex(tile1);
        int col2 = getColumnIndex(tile2);
        int row2 = getRowIndex(tile2);
        tileMoveAnimation(tile1, col1, row1, col2, row2, () -> {
        });
        tileMoveAnimation(tile2, col2, row2, col1, row1, Controller::playNextAction);
    }

    //TODO make animation always go on top of static tiles
    public void tileMoveAnimation(AreaTile areaTile, int originCol, int originRow, int targetCol, int targetRow, Runnable callback) {
        double deltaX = getX(targetCol) - getX(originCol);
        double deltaY = getY(targetRow) - getY(originRow);
        TranslateTransition translation = new TranslateTransition(
                new Duration(500), areaTile);
        translation.setFromX(0);
        translation.setFromY(0);
        translation.setToX(deltaX);
        translation.setToY(deltaY);
        translation.setOnFinished(e -> {
            getChildren().remove(areaTile);
            areaTile.setTranslateX(0);
            areaTile.setTranslateY(0);
            add(areaTile, targetCol, targetRow);
            callback.run();
        });
        translation.play();
    }

    public double getX(int col) {
        return (col - 0.5) * getPrefWidth() / 5;
    }

    public double getY(int row) {
        return (row - 0.5) * getPrefHeight() / 5;
    }
}
