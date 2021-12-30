package ais;

import board.Board;
import board.BoardSquare;
import chesspieces.Shape;
import chesspieces.Sheep;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;
import logic.GameUI;

import java.util.Optional;

public class GameConsole implements GameUI {
//    private final BoardSquare[][] boardSquares;
//    private int move;
//    private int availableSheepMoves;


    public void moveWolf(Board board, BoardSquare from, BoardSquare to) {
        for (int i = 0; i < board.getWolf().length; i++) {
            if (moveIsPossible(board.getWolf()[i].moveDown(), board.getWolf()[i].moveLeft())) {
                movementWolf(board.getWolf()[i].moveDown(), board.getWolf()[i].moveLeft(),  board.getWolf()[i].moveRight(), board, i);
            }
            if (moveIsPossible(board.getWolf()[i].moveDown(), board.getWolf()[i].moveRight())) {
                movementWolf(board.getWolf()[i].moveDown(), board.getWolf()[i].moveLeft(),  board.getWolf()[i].moveRight(), board, i);
            }
        }

    }

    @Override
    public void moveSheep(Board board) {
        GameUI.super.moveSheep(board);
    }

    @Override
    public void movementSheep(int vertical, int horizontal, Board board) {
        GameUI.super.movementSheep(vertical, horizontal, board);
    }

    @Override
    public void movementWolf(int vertical, int horizontalST, int horizontalSC, Board board, int constI) {
        GameUI.super.movementWolf(vertical, horizontalST, horizontalSC, board, constI);
    }

    @Override
    public void clearSheepSquares(Board board) {
        GameUI.super.clearSheepSquares(board);
    }




    @Override
    public void fillingSheepSquare(int vertical, int horizontal, int availableSheepMoves) {
        GameUI.super.fillingSheepSquare(vertical, horizontal, availableSheepMoves);
    }

    @Override
    public void fillingWolfSquare(int vertical, int horizontal) {
        GameUI.super.fillingWolfSquare(vertical, horizontal);
    }

    @Override
    public void sheepWon(Sheep sheep) {
        GameUI.super.sheepWon(sheep);
    }
}
