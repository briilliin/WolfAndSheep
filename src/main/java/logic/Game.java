package logic;

import board.Board;
import board.BoardSquare;
import chesspieces.Sheep;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

public class Game implements GameUI{
    private  BoardSquare[][] boardSquares;
    private int move;


    private int availableSheepMoves;


    public int getAvailableSheepMoves() {
        return availableSheepMoves;
    }



    public void play(Board board) {
        moveSheep(board);
        moveWolf(board);
    }


    public void setPawnsOnDefaultPosition(Board board) {

        for (int i = 0, j = 0; i < board.getBoardSquares().length; i++) {
            if (i % 2 != 0) {
                board.getBoardSquares(board.getWolf()[j].getRowPosition(), i).getChildren().add(board.getWolf()[j]);
                j++;
            }
        }
        board.getBoardSquares(board.getSheep().getRowPosition(), board.getSheep().getColPosition()).getChildren().add(board.getSheep());
    }


    @Override
    public void moveWolf(Board board) {
            for (int i = 0; i < board.getWolf().length; i++) {
                int constI = i;
                board.getWolf()[constI].setOnMouseClicked(mouseEvent -> {

                    if (move % 2 != 0) {

                        if (moveIsPossible(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft())) {
                            fillingWolfSquare(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft());
                            boardSquares[board.getWolf()[constI].getRowPosition() + 1][board.getWolf()[constI].getColPosition() - 1].setOnMouseClicked(mouseEvent1 ->
                                    movementWolf(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft(), board.getWolf()[constI].moveRight(), board, constI));

                        }
                        if (moveIsPossible(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight())) {
                            fillingWolfSquare(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight());
                            boardSquares[board.getWolf()[constI].getRowPosition() + 1][board.getWolf()[constI].getColPosition() + 1].setOnMouseClicked(mouseEvent1 ->
                                    movementWolf(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight(), board.getWolf()[constI].moveLeft(), board, constI));

                        }
                    }
                });
            }
        }

    @Override
    public void moveSheep(Board board) {
        board.getSheep().setOnMouseClicked(mouseEvent -> {

            if (move % 2 == 0) {
                availableSheepMoves = 4;

                fillingSheepSquare(board.getSheep().moveDown(), board.getSheep().moveLeft(), availableSheepMoves);
                fillingSheepSquare(board.getSheep().moveDown(), board.getSheep().moveRight(), availableSheepMoves);
                fillingSheepSquare(board.getSheep().moveUp(), board.getSheep().moveLeft(), availableSheepMoves);
                fillingSheepSquare(board.getSheep().moveUp(), board.getSheep().moveRight(), availableSheepMoves);

                if (availableSheepMoves == 4) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("игра окончена!!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Волки победили!!!");
                    Optional<ButtonType> result = alert.showAndWait();


                }

                if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveLeft())) {
                    boardSquares[board.getSheep().moveDown()][board.getSheep().moveLeft()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveDown(), board.getSheep().moveLeft(), board);
                    });
                }
                if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveRight())) {
                    boardSquares[board.getSheep().moveDown()][board.getSheep().moveRight()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveDown(), board.getSheep().moveRight(), board);
                    });
                }
                if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveLeft())) {
                    boardSquares[board.getSheep().moveUp()][board.getSheep().moveLeft()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveUp(), board.getSheep().moveLeft(), board);
                    });
                }
                if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveRight())) {
                    boardSquares[board.getSheep().moveUp()][board.getSheep().moveRight()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveUp(), board.getSheep().moveRight(), board);
                    });

                }
            }
        });


    }
    @Override
    public void movementSheep(int vertical, int horizontal, Board board) {
        if (boardSquares[vertical][horizontal].isHighLight()) {
            boardSquares[vertical][horizontal].getChildren().add(board.getSheep());
            clearSheepSquares(board);
            boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setBusy(false);
            board.getSheep().setColPosition(horizontal);
            board.getSheep().setRowPosition(vertical);
            sheepWon(board.getSheep());
            boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setOnMouseClicked(null);
            boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setBusy(true);
            move++;
        }
    }
    @Override
    public void movementWolf(int vertical, int horizontalST, int horizontalSC, Board board, int constI) {
        if (boardSquares[vertical][horizontalST].isHighLight()) {
            boardSquares[vertical][horizontalST].setOnMouseClicked(null);
            boardSquares[vertical][horizontalST].getChildren().add(board.getWolf()[constI]);
            if (moveIsPossible(vertical, horizontalST)) {
                boardSquares[vertical][horizontalST].clear();
            }
            if (moveIsPossible(vertical, horizontalSC)) {
                boardSquares[vertical][horizontalSC].clear();
                boardSquares[vertical][horizontalSC].setOnMouseClicked(null);
            }
            boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(false);
            boardSquares[vertical][horizontalST].clear();
            board.getWolf()[constI].setRowPosition(vertical);
            board.getWolf()[constI].setColPosition(horizontalST);
            boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(true);
            move++;
        }

    }
    @Override
    public void clearSheepSquares(Board board) {
        if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveLeft())) {
            boardSquares[board.getSheep().moveDown()][board.getSheep().moveLeft()].clear();
            boardSquares[board.getSheep().moveDown()][board.getSheep().moveLeft()].setOnMouseClicked(null);

        }
        if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveRight())) {
            boardSquares[board.getSheep().moveDown()][board.getSheep().moveRight()].clear();
            boardSquares[board.getSheep().moveDown()][board.getSheep().moveRight()].setOnMouseClicked(null);

        }
        if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveLeft())) {
            boardSquares[board.getSheep().moveUp()][board.getSheep().moveLeft()].clear();
            boardSquares[board.getSheep().moveUp()][board.getSheep().moveLeft()].setOnMouseClicked(null);

        }
        if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveRight())) {
            boardSquares[board.getSheep().moveUp()][board.getSheep().moveRight()].clear();
            boardSquares[board.getSheep().moveUp()][board.getSheep().moveRight()].setOnMouseClicked(null);

        }

    }

    @Override
    public boolean moveIsPossible(int vertical, int horizontal) {
        return GameUI.super.moveIsPossible(vertical, horizontal);
    }

    @Override
    public void fillingSheepSquare(int vertical, int horizontal, int availableSheepMoves) {
        if (moveIsPossible(vertical, horizontal)) {
            if (!boardSquares[vertical][horizontal].isBusy()) {
                boardSquares[vertical][horizontal].highlight(Color.DEEPPINK);
                this.availableSheepMoves = availableSheepMoves - 1;
            }
        }
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



}