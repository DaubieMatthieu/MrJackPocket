package main.java;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.java.GUI.GamePane;


public class Game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new GamePane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mr Jack Pocket");

        primaryStage.setWidth(720);
        primaryStage.setHeight(480);
        primaryStage.setMinWidth(640);
        primaryStage.setMinHeight(360);
        primaryStage.setMaximized(true);

        primaryStage.show();
    }
}
