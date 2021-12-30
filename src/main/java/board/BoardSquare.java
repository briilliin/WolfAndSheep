package board;


import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoardSquare extends StackPane {
    boolean isHighLight;
    boolean isBusy;
    private Color color;

    public BoardSquare(Color defaultColor) {
        isHighLight = false;
        isBusy = false;
        color = defaultColor;
        setColor(color);
        setPrefSize(200, 200);


    }



    public void setColor(Color color) {
        BackgroundFill bgFill = new BackgroundFill(color, CornerRadii.EMPTY, new Insets(0));
        Background bg = new Background(bgFill);
        setBackground(bg);

    }


    public void highlight(Color color) {
        setColor(color);
        isHighLight = true;
    }

    public void clear() {
        setColor(color);
        isHighLight = false;
    }


    public boolean isHighLight() {
        return isHighLight;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }


}