package main.java.GUI;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RightPane extends VBox {

    RightPane() {
        init();
    }

    public void init() {
        Background bg = new Background(new BackgroundFill(Color.YELLOWGREEN, CornerRadii.EMPTY, Insets.EMPTY));
        setBackground(bg);
        FXHelper.setDefaultVBox(this);
    }
}
