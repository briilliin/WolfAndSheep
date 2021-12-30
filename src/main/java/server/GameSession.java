package server;

import ais.SheepRandomBot;
import board.Board;

import java.awt.*;
import java.net.Socket;

public class GameSession implements Runnable {

    private Board board;
    private final SheepRandomBot game;

    public GameSession(Socket socket) {
        board = new Board();
        game = new SheepRandomBot(board);
    }

    public void run() {
        while (!game.isEnd(board)) {
            game.moveSheep(board);
            game.possibleMoveSheep(board);
        }
        System.out.println("Game Over!");
    }

}