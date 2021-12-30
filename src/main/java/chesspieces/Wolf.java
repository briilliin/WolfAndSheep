package chesspieces;

public class Wolf extends Shape {

    MonsterType wolf;

    public Wolf(int colPosition, int rowPosition) {
        this.colPosition = colPosition;
        this.rowPosition = rowPosition;
        this.wolf = MonsterType.WOLF;
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
    public int moveLeft() {
        return getColPosition() - 1;
    }

    @Override
    public int moveRight() {
        return getColPosition() + 1;
    }

    public MonsterType getType() {
        return MonsterType.WOLF;
    }


}
