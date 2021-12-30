package ais;


import board.Board;
import board.BoardSquare;
import chesspieces.Sheep;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Optional;

public class BotGame {
    private final BoardSquare[][] boardSquares;
    private int move;
    private int availableSheepMoves;


    public BotGame(Board board, Stage primaryStage) {
        this.boardSquares = board.boardSquares;
        setPawnsOnDefaultPosition(board);
        play(board, primaryStage);
    }

    public void play(Board board, Stage primaryStage) {
        moveSheep(board, primaryStage);
        moveWolf(board, primaryStage);
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


    public void moveWolf(Board board, Stage primaryStage) {
        int constI = 0 + (int) (Math.random() * 3);
        for (int i = 0; i < board.getWolf().length; i++) {
            int randomMove = 0 + (int) (Math.random() * 1);

            if (move % 2 != 0) {
                if (randomMove == 0) {

                    if (moveIsPossible(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft())) {
                        boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(false);
                        boardSquares[board.getWolf()[constI].moveDown()][board.getWolf()[constI].moveLeft()].getChildren().add(board.getWolf()[constI]);
                        board.getWolf()[constI].setRowPosition(board.getWolf()[constI].moveDown());
                        board.getWolf()[constI].setColPosition(board.getWolf()[constI].moveLeft());
                        boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(true);
                        move++;
                        return;
                    } else {
                        if (moveIsPossible(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight())) {
                            boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(false);
                            boardSquares[board.getWolf()[constI].getRowPosition() + 1][board.getWolf()[constI].getColPosition() + 1].getChildren().add(board.getWolf()[constI]);
                            board.getWolf()[constI].setRowPosition(board.getWolf()[constI].moveDown());
                            board.getWolf()[constI].setColPosition(board.getWolf()[constI].moveLeft());
                            move++;
                            return;
                        }

                    }
                }
                if (randomMove == 1) {
                    if (moveIsPossible(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight())) {
                        boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(false);
                        movementWolf(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveRight(), board.getWolf()[constI].moveLeft(), board, constI, primaryStage);
                        boardSquares[board.getWolf()[constI].getRowPosition() + 1][board.getWolf()[constI].getColPosition() + 1].getChildren().add(board.getWolf()[constI]);
                    } else {
                        if (moveIsPossible(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft())) {
                            boardSquares[board.getWolf()[constI].getRowPosition()][board.getWolf()[constI].getColPosition()].setBusy(false);
                            movementWolf(board.getWolf()[constI].moveDown(), board.getWolf()[constI].moveLeft(), board.getWolf()[constI].moveRight(), board, constI, primaryStage);
                            boardSquares[board.getWolf()[constI].getRowPosition() + 1][board.getWolf()[constI].getColPosition() - 1].getChildren().add(board.getWolf()[constI]);
                        }

                    }
                }
            }
        }
        moveSheep(board, primaryStage);
    }


    public void moveSheep(Board board, Stage primaryStage) {
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
                    if (result.get() == ButtonType.OK) {
                        primaryStage.close();
                    }


                }

                if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveLeft())) {
                    boardSquares[board.getSheep().moveDown()][board.getSheep().moveLeft()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveDown(), board.getSheep().moveLeft(), board, primaryStage);
                    });
                }
                if (moveIsPossible(board.getSheep().moveDown(), board.getSheep().moveRight())) {
                    boardSquares[board.getSheep().moveDown()][board.getSheep().moveRight()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveDown(), board.getSheep().moveRight(), board, primaryStage);
                    });
                }
                if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveLeft())) {
                    boardSquares[board.getSheep().moveUp()][board.getSheep().moveLeft()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveUp(), board.getSheep().moveLeft(), board, primaryStage);
                    });
                }
                if (moveIsPossible(board.getSheep().moveUp(), board.getSheep().moveRight())) {
                    boardSquares[board.getSheep().moveUp()][board.getSheep().moveRight()].setOnMouseClicked(mouseEvent1 -> {
                        movementSheep(board.getSheep().moveUp(), board.getSheep().moveRight(), board, primaryStage);
                    });

                }
            }
        });

    }

    public void movementSheep(int vertical, int horizontal, Board board, Stage primaryStage) {
        if (boardSquares[vertical][horizontal].isHighLight()) {
            boardSquares[vertical][horizontal].getChildren().add(board.getSheep());
            clearSheepSquares(board);
            boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setBusy(false);
            board.getSheep().setColPosition(horizontal);
            board.getSheep().setRowPosition(vertical);
            sheepWon(board.getSheep(), primaryStage);
            boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setOnMouseClicked(null);
            boardSquares[board.getSheep().getRowPosition()][board.getSheep().getColPosition()].setBusy(true);
            move++;
            moveWolf(board, primaryStage);

        }
    }

    public void movementWolf(int vertical, int horizontalST, int horizontalSC, Board board, int constI, Stage primaryStage) {
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
            moveSheep(board, primaryStage);
            move++;
        }

    }

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

    public boolean moveIsPossible(int vertical, int horizontal) {
        int rowMax = 8;
        int colMax = 8;
        return (vertical < rowMax && vertical >= 0 && horizontal < colMax && horizontal >= 0);
    }


    public void fillingSheepSquare(int vertical, int horizontal, int availableSheepMoves) {
        if (moveIsPossible(vertical, horizontal)) {
            if (!boardSquares[vertical][horizontal].isBusy()) {
                boardSquares[vertical][horizontal].highlight(Color.DEEPPINK);
                this.availableSheepMoves = availableSheepMoves - 1;
            }
        }
    }

    public void fillingWolfSquare(int vertical, int horizontal) {
        if (moveIsPossible(vertical, horizontal)) {
            if (!boardSquares[vertical][horizontal].isBusy()) {
                boardSquares[vertical][horizontal].highlight(Color.YELLOWGREEN);

            }
        }
    }


    public void sheepWon(Sheep sheep, Stage primaryStage) {
        if (sheep.getRowPosition() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("игра окончена");
            alert.setHeaderText(null);
            alert.setContentText("Овечки победили!!!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                primaryStage.close();
            }
        }
    }


}