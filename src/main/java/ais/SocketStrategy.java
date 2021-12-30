package ais;

import board.Board;
import board.BoardSquare;
import chesspieces.Shape;
import chesspieces.Sheep;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.Game;
import logic.GameUI;

import java.util.Optional;

public class SocketStrategy implements GameUI {
    private final BoardSquare[][] boardSquares;
    private int move;
    private int availableSheepMoves;




    public SocketStrategy(Board board) {
        this.boardSquares = board.boardSquares;
        setPawnsOnDefaultPosition(board);
        play(board);
    }

    public void play(Board board) {
        moveSheep(board);
    }

    @Override
    public void setPawnsOnDefaultPosition(Board board) {
        super.setPawnsOnDefaultPosition(board);
    }


    public void moveWolf(Board board, int constI, int vertical, int horizontal) {
        if (move % 2 != 0) {
            possibleMoveWolf(board, constI);

            if (moveIsPossible(vertical, horizontal)) {
                fillingWolfSquare(vertical, horizontal);
                movementWolf(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft(), board.getWolf()[constI].moveRight(), board, constI);

            }
            if (moveIsPossible(vertical, horizontal)) {
                fillingWolfSquare(vertical, horizontal);
                movementWolf(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight(), board.getWolf()[constI].moveLeft(), board, constI);

            }
        }

    }




    @Override
    public void moveSheep(Board board) {
        int randomMove = 0 + (int) (Math.random() * 1);
        if (move % 2 == 0) {
            availableSheepMoves = 4;


            countAvailableSheepMoves(board.getSheep().moveDown(), board.getSheep().moveLeft(), availableSheepMoves);
            countAvailableSheepMoves(board.getSheep().moveDown(), board.getSheep().moveRight(), availableSheepMoves);
            countAvailableSheepMoves(board.getSheep().moveUp(), board.getSheep().moveLeft(), availableSheepMoves);
            countAvailableSheepMoves(board.getSheep().moveUp(), board.getSheep().moveRight(), availableSheepMoves);
            wolfWon();

            possibleMoveSheep(board);


            if (randomMove == 0) {
                if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveLeft())) {
                    movementSheep(board.getSheep().moveUp(), board.getSheep().moveLeft(), board);
                } else if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveRight())) {
                    movementSheep(board.getSheep().moveUp(), board.getSheep().moveRight(), board);
                } else if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveLeft())) {
                    movementSheep(board.getSheep().moveDown(), board.getSheep().moveLeft(), board);
                } else if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveRight())) {
                    movementSheep(board.getSheep().moveDown(), board.getSheep().moveRight(), board);
                }
                return;
            }
            if (randomMove == 1) {
                if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveRight())) {
                    movementSheep(board.getSheep().moveUp(), board.getSheep().moveRight(), board);
                } else if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveLeft())) {
                    movementSheep(board.getSheep().moveUp(), board.getSheep().moveLeft(), board);
                } else if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveLeft())) {
                    movementSheep(board.getSheep().moveDown(), board.getSheep().moveLeft(), board);
                } else if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveRight())) {
                    movementSheep(board.getSheep().moveDown(), board.getSheep().moveRight(), board);
                }
                return;
            }

        }


    }

    public void possibleMoveSheep(Board board) {
        System.out.println("допустимые ходы выбранной овечки:");
        getAvailableMoves(board.getSheep().moveDown(), board.getSheep().moveLeft(), board.getSheep());
        getAvailableMoves(board.getSheep().moveDown(), board.getSheep().moveRight(), board.getSheep());
        getAvailableMoves(board.getSheep().moveUp(), board.getSheep().moveLeft(), board.getSheep());
        getAvailableMoves(board.getSheep().moveUp(), board.getSheep().moveRight(), board.getSheep());

    }


    public void possibleMoveWolf(Board board, int constI) {
        System.out.println("допустимые ходы выбранного волка:");
        getAvailableMoves(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft(), board.getWolf()[constI]);
        getAvailableMoves(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight(), board.getWolf()[constI]);

    }


    @Override
    public void movementSheep(int vertical, int horizontal, Board board) {
        boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setBusy(false);
        boardSquares[vertical][horizontal].getChildren().add(board.getSheep());
        board.getSheep().setColPosition(horizontal);
        board.getSheep().setRowPosition(vertical);
        sheepWon(board.getSheep());
        boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setBusy(true);
        move++;

    }

    public void countAvailableSheepMoves(int vertical, int horizontal, int availableSheepMoves) {
        if (moveIsPossible(vertical, horizontal)) {
            if (!boardSquares[vertical][horizontal].isBusy()) {
                this.availableSheepMoves = availableSheepMoves - 1;
            }
        }
    }

    public BoardSquare getAvailableMoves(int vertical, int horizontal, Shape shape) {
        if (moveIsPossible(vertical, horizontal)) {
            if (!boardSquares[vertical][horizontal].isBusy()) {
                System.out.println(7 - vertical + " " + horizontal);
                return boardSquares[vertical][horizontal];
            }
        }
        return boardSquares[shape.getRowPosition()][shape.getColPosition()];
    }

    @Override
    public void movementWolf(int vertical, int horizontalST, int horizontalSC, Board board, int constI) {
        if (boardSquares[vertical][horizontalST].isHighLight()) {
            boardSquares[vertical][horizontalST].getChildren().add(board.getWolf()[constI]);
            if (moveIsPossible(vertical, horizontalST)) {
                boardSquares[vertical][horizontalST].clear();
            }
            if (moveIsPossible(vertical, horizontalSC)) {
                boardSquares[vertical][horizontalSC].clear();
            }
            boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(false);
            board.getWolf()[constI].setRowPosition(vertical);
            board.getWolf()[constI].setColPosition(horizontalST);
            boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(true);
            move++;
        }
        moveSheep(board);

    }

    @Override
    public void clearSheepSquares(Board board) {
        super.clearSheepSquares(board);
    }

    @Override
    public boolean moveIsPossible(int vertical, int horizontal) {
        int rowMax = 8;
        int colMax = 8;
        return (vertical < rowMax && vertical >= 0 && horizontal < colMax && horizontal >= 0 && (!boardSquares[vertical][horizontal].isBusy()));
    }

    @Override
    public void fillingSheepSquare(int vertical, int horizontal, int availableSheepMoves) {
        super.fillingSheepSquare(vertical, horizontal, availableSheepMoves);
    }

    @Override
    public void fillingWolfSquare(int vertical, int horizontal) {
        if (moveIsPossible(vertical, horizontal)) {
            if (!boardSquares[vertical][horizontal].isBusy()) {
                boardSquares[vertical][horizontal].highlight(Color.YELLOWGREEN);

            }
        }
    }

    @Override
    public void sheepWon(Sheep sheep) {
        if (sheep.getRowPosition() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("игра окончена");
            alert.setHeaderText(null);
            alert.setContentText("Овечки победили!!!");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    public void wolfWon() {
        if (availableSheepMoves == 4) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("игра окончена!!!");
            alert.setHeaderText(null);
            alert.setContentText("Волки победили!!!");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    public boolean isEnd(Board board) {
        if ((board.getSheep().getRowPosition() == 0) || availableSheepMoves == 4) {
            return true;
        }
        return false;
    }

}












