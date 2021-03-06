package board;


import chesspieces.Sheep;
import chesspieces.Wolf;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Board {
    public BoardSquare[][] boardSquares = new BoardSquare[8][8];
    GridPane board = new GridPane();
    StackPane stackPane;
    private final Wolf[] wolf = new Wolf[4];
    private final Sheep sheep = new Sheep(2, 7);

    public int randomEvenInt(){
        int v  = (int) (Math.random() * 7);
        return v%2 == 0?v:randomEvenInt();
    }



    public Board() {

        for (int row = 0, i = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                BoardSquare boardSquare;
                if ((i % 2) == 0) {
                    boardSquare = new BoardSquare(Color.BEIGE);
                    StackPane square = getBoardSquare(boardSquare);
                    board.add(square, col, row);
                } else {
                    boardSquare = new BoardSquare(Color.BLACK);
                    StackPane square = getBoardSquare(boardSquare);
                    board.add(square, col, row);
                }
                boardSquares[row][col] = boardSquare;
                i++;
            }
            i++;
        }
        for (int row = 0; row < 8; row++) {
            RowConstraints constrains = new RowConstraints();
            board.getRowConstraints().add(constrains);
        }
        for (int col = 0; col < 8; col++) {
            ColumnConstraints constrains = new ColumnConstraints();
            board.getColumnConstraints().add(constrains);
        }
        createPawns();    }


    public void createPawns() {
        NumberBinding radius = Bindings.when(stackPane.widthProperty().greaterThan(stackPane.heightProperty())).then(stackPane.heightProperty().
                subtract(12).divide(2)).otherwise(stackPane.widthProperty().subtract(12).divide(2));

        for (int i = 0; i < wolf.length; i++) {
            this.wolf[i] = new Wolf(i * 2 + 1, 0);
            boardSquares[wolf[i].getRowPosition()][wolf[i].getColPosition()].setBusy(true);
            wolf[i].setFill(Color.DARKGREY);
            wolf[i].radiusProperty().bind(radius);
        }

        sheep.radiusProperty().bind(radius);
        sheep.setFill(Color.CHOCOLATE);
        boardSquares[sheep.getRowPosition()][sheep.getColPosition()].setBusy(true);
    }

    public Wolf[] getWolf() {
        return wolf;
    }

    public Sheep getSheep() {
        return sheep;
    }


    public GridPane getBoard() {
        return board;
    }

    public BoardSquare getBoardSquares(int row, int col) {
        return boardSquares[row][col];
    }

    public BoardSquare[][] getBoardSquares() {
        return boardSquares;
    }

    public StackPane getBoardSquare(BoardSquare boardSquare) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(boardSquare);
        this.stackPane = stackPane;
        return stackPane;
    }

}
