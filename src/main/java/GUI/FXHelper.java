package main.java.GUI;

import javafx.beans.binding.*;
import javafx.geometry.*;
import javafx.scene.layout.*;

public interface FXHelper {
    static void setDefaultVBox(VBox vBox) {
        setDefaultAlignment(vBox);
        setDefaultPadding(vBox);
    }

    static void setDefaultAlignment(VBox vBox) {
        vBox.setAlignment(Pos.CENTER);
        //sets 5% height spacing between elements
        vBox.spacingProperty().bind(vBox.heightProperty().divide(20));
    }

    static void setDefaultPadding(Pane pane) {
        //sets 2% padding on each side
        setPercentagePadding(pane, 2.0,2.0);
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
                new Insets(tp.doubleValue(),rp.doubleValue(),bp.doubleValue(),lp.doubleValue()), tp, rp, bp, lp));
    }
}
