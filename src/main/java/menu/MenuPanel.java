package menu;


import board.Board;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPanel{
    VBox menuPanel = new VBox(20);
    VBox startPanel = new VBox(20);
    BorderPane borderPane = new BorderPane();
    Button startButton = new Button("START");
    Button playPvP = new Button("PLAYER VS PLAYER");
    Button playPvE = new Button("PLAYER VS COMPUTER");
    Button optionButton = new Button("OPTION");
    Button exitButton = new Button("EXIT");
    Button backButton = new Button("BACK");

    public MenuPanel(BorderPane borderPane, Board board, Stage stage) {
        this.borderPane = borderPane;
        displayMenu(menuPanel);
        menuPanel.getChildren().addAll(startButton, exitButton, playPvE);
        startPanel.getChildren().add(board.getBoard());
        menuPanel.setAlignment(Pos.CENTER);
        addActionListener(borderPane, stage);

    }

    public void displayMenu(Pane pane){
        borderPane.setCenter(pane);
    }

    public void addActionListener(BorderPane borderPane, Stage stage){
        startButton.setOnAction(actionEvent -> {
            stage.setWidth(800);
            stage.setHeight(800);
            startPanel.setAlignment(Pos.CENTER);
            playPvE.setAlignment(Pos.CENTER);
            displayMenu(startPanel);


        });

        backButton.setOnAction(actionEvent -> {
            displayMenu(menuPanel);
        });

        playPvE.setOnAction(actionEvent -> {

        });
        playPvP.setOnAction(actionEvent -> {
        });
    }

    public Button getExitButton() {
        return exitButton;
    }

}