package main.java.GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class TopPane extends Pane {
    TopPane() {
        Background bg = new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY));
        setBackground(bg);
        Label titleLabel = new Label("MrJackPocket");
        getChildren().add(titleLabel);
    }
}
