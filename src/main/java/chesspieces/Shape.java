package chesspieces;

import javafx.scene.shape.Circle;

public abstract class Shape extends Circle {

    protected int colPosition;
    protected int rowPosition;

    public enum MonsterType {
        WOLF, SHEEP
    }

    public int getColPosition() {
        return colPosition;
    }

    public void setColPosition(int colPosition) {
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public void setRowPosition(int rowPosition) {
    }

    public int moveDown() {
        return getRowPosition() + 1;
    }

    public int moveUp() {
        return getRowPosition() - 1;
    }

    public int moveLeft() {
        return getColPosition() - 1;
    }

    public int moveRight() {
        return getColPosition() + 1;
    }



}
