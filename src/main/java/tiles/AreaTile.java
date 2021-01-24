package main.java.tiles;

import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import main.java.Controller;
import main.java.FXHelper;
import main.java.RandomHelper;
import main.java.alibis.Alibi;

public class AreaTile extends Tile {
    private final Image hiddenImage;
    private final Alibi alibi;
    private boolean suspectIsVisible = true;
    private int orientation = 0;
    //the orientation correspond to the wall side orientation (N=0;W=1,S=2,E=3)

    public AreaTile(int suspectId) {
        super("/tiles/suspect_" + suspectId + "_tile.png");
        alibi = Alibi.getAlibi(suspectId);
        hiddenImage = new Image("/tiles/street_tile.png");
        this.setOnMouseClicked(e -> Controller.clickedNode(this));
        FXHelper.setClickable(this, false);
        setOrientation(RandomHelper.randomInt(0, 3));
    }

    public boolean suspectIsVisible() {
        return suspectIsVisible;
    }

    public void turnOver() {
        setSuspectIsVisible(!suspectIsVisible);
    }

    public void setSuspectIsVisible(boolean isVisible) {
        suspectIsVisible = isVisible;
        setImage((suspectIsVisible) ? defaultImage : hiddenImage);
    }

    public void rotate(int count, boolean clockWise) {
        rotate(count, clockWise, Controller::playNextAction);
    }

    public void rotate(int count, boolean clockWise, Runnable callback) {
        orientation = (orientation + ((clockWise) ? -count : count) + 4) % 4;
        int angle = ((clockWise) ? 1 : -1) * count * 90;
        rotationAnimation(angle, callback);
    }

    public void rotationAnimation(int rotationAngle, Runnable callback) {
        RotateTransition rt = new RotateTransition(Duration.millis(500), this);
        rt.setByAngle(rotationAngle);
        rt.setOnFinished(e -> callback.run());
        rt.play();
    }

    public void setOrientation(int orientation) {
        int count = (this.orientation - orientation + 4) % 4;
        boolean clockWise = (count != 3);
        if (!clockWise) count -= 2;
        rotate(count, clockWise, () -> {
        });
        this.orientation = orientation;
    }

    public Alibi getAlibi() {
        return alibi;
    }

    //(N=0;E=1,S=2,W=3)
    public boolean isBlocked(int orientation) {
        return this.orientation == orientation;
    }

    @Override
    public String toString() {
        return "AreaTile of " + alibi;
    }
}
