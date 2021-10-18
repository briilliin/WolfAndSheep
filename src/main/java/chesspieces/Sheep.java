package chesspieces;

public class Sheep extends Shape{




    public Sheep(int colPosition, int rowPosition) {
        this.colPosition = colPosition;
        this.rowPosition = rowPosition;
    }

    @Override
    public int getColPosition() {
        return colPosition;
    }

    @Override
    public void setColPosition(int colPosition) {
        this.colPosition = colPosition;
    }

    @Override
    public int getRowPosition() {
        return rowPosition;
    }

    @Override
    public void setRowPosition(int rowPosition) {
        this.rowPosition = rowPosition;
    }

    @Override
    public int moveDown() {
        return getRowPosition() + 1;
    }

    @Override
    public int moveUp() {
        return getRowPosition() - 1;
    }

    @Override
    public int moveLeft() {
        return getColPosition() - 1;
    }

    @Override
    public int moveRight() {
        return getColPosition() + 1;
    }

}
