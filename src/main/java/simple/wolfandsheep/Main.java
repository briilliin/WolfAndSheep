package simple.wolfandsheep;

import board.Board;
import board.MenuPanel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.Game;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Board board = new Board();
        MenuPanel menuPanel = new MenuPanel(root, board, primaryStage);
        Game game = new Game(board, primaryStage);
        game.play(board, primaryStage);


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
