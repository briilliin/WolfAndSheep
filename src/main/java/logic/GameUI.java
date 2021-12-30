package logic;

import board.Board;
import chesspieces.Sheep;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;

import java.util.Optional;

public interface GameUI {


    default void moveWolf(Board board) {
    }

    default void moveSheep(Board board) {
    }

    default void movementSheep(int vertical, int horizontal, Board board) {
    }

    default void movementWolf(int vertical, int horizontalST, int horizontalSC, Board board, int constI) {
    }

    default void clearSheepSquares(Board board) {
    }

    default boolean moveIsPossible(int vertical, int horizontal) {
        int rowMax = 8;
        int colMax = 8;
        return (vertical < rowMax && vertical >= 0 && horizontal < colMax && horizontal >= 0);
    }


    default void fillingSheepSquare(int vertical, int horizontal, int availableSheepMoves) {
    }

    default void fillingWolfSquare(int vertical, int horizontal) {
    }

    default void sheepWon(Sheep sheep) {
    }


}
