package main.java;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public interface FXHelper {
    static void setDefaultVBox(VBox vBox) {
        //sets 2% padding on each side
        setDefaultPadding(vBox);
        //sets 5% width spacing between elements
        vBox.spacingProperty().bind(vBox.prefHeightProperty().divide(20));
        vBox.setAlignment(Pos.CENTER);
    }

    static void setDefaultHBox(HBox hBox) {
        setDefaultPadding(hBox);
        hBox.spacingProperty().bind(hBox.prefHeightProperty().divide(10));
        hBox.setAlignment(Pos.CENTER);
    }

    static void setDefaultLabeled(Labeled labeled, int fontSize) {
        labeled.setAlignment(Pos.CENTER);
        labeled.setTextAlignment(TextAlignment.CENTER);
        labeled.setWrapText(true);
        labeled.setFont(new Font("Cambria", fontSize));
    }

    static void setDefaultPadding(Pane pane) {
        //sets 2% padding on each side
        setPercentagePadding(pane, 2.0, 2.0);
    }

    static void setPercentagePadding(Pane pane, Double widthPercentage, Double heightPercentage) {
        setPaddingPercentage(pane, heightPercentage, widthPercentage, heightPercentage, widthPercentage);
    }

    static void setPaddingPercentage(Pane pane, Double top, Double right, Double bottom, Double left) {
        DoubleBinding tp = pane.prefHeightProperty().multiply(top).divide(100);
        DoubleBinding bp = pane.prefHeightProperty().multiply(bottom).divide(100);
        DoubleBinding rp = pane.prefWidthProperty().multiply(right).divide(100);
        DoubleBinding lp = pane.prefWidthProperty().multiply(left).divide(100);
        pane.paddingProperty().bind(Bindings.createObjectBinding(() ->
                new Insets(tp.doubleValue(), rp.doubleValue(), bp.doubleValue(), lp.doubleValue()), tp, rp, bp, lp));
    }

    static void setClickable(Pane pane, boolean clickable) {
        for (Node node : pane.getChildren()) setClickable(node, clickable);
    }

    static void setClickable(Node node, boolean clickable) {
        if (node.getOnMouseClicked() == null) return;
        node.setMouseTransparent(!clickable);
        String style = (clickable) ? "-fx-effect: innershadow(gaussian, #00FF00, 3, 1, 0, 0);" : null;
        node.setStyle(style);
    }
}
