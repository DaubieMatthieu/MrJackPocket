package main.java.GUI;

import javafx.beans.binding.*;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GamePane extends GridPane {
    public final LeftPane leftPane;
    public final RightPane rightPane;


    public GamePane() {
        Background bg = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));
        this.setBackground(bg);

        NumberBinding districtSize = Bindings.min(widthProperty().multiply(0.8), heightProperty().multiply(0.9));
        NumberBinding sideWidth = widthProperty().subtract(districtSize).divide(2);
        NumberBinding topHeight = heightProperty().subtract(districtSize);

        TopPane topPane = new TopPane();
        topPane.prefWidthProperty().bind(districtSize);
        topPane.prefHeightProperty().bind(topHeight);
        add(topPane, 1, 0);

        DistrictPane districtPane = new DistrictPane();
        districtPane.prefWidthProperty().bind(districtSize);
        districtPane.prefHeightProperty().bind(districtSize);
        VBox.setVgrow(districtPane, Priority.ALWAYS);
        HBox.setHgrow(districtPane, Priority.ALWAYS);
        add(districtPane,1,1);

        leftPane = new LeftPane();
        leftPane.prefHeightProperty().bind(heightProperty());
        leftPane.prefWidthProperty().bind(sideWidth);
        add(leftPane, 0, 0,1,2);

        rightPane = new RightPane();
        rightPane.prefHeightProperty().bind(heightProperty());
        rightPane.prefWidthProperty().bind(sideWidth);
        add(rightPane, 2, 0,1,2);
    }
}