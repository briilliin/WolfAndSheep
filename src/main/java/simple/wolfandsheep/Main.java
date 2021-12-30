package simple.wolfandsheep;


import ais.SheepRandomBot;
import board.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import logic.Game;
import menu.MenuPanel;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Board board = new Board();
        MenuPanel menuPanel = new MenuPanel(root, board, primaryStage);
//        Game game = new Game(board);
//        game.play(board);
//        game.moveSheep(board);

        SheepRandomBot gameBot = new SheepRandomBot(board);
        gameBot.moveSheep(board);
        gameBot.moveWolf(board);

//        Player p1 = new Player(board);


//        BotGame game2 = new BotGame(board, primaryStage);
//        game2.moveSheep(board, primaryStage);



        menuPanel.getExitButton().setOnAction(actionEvent -> {
            primaryStage.close();
        });
        primaryStage.setTitle("Wolf and Sheep");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}