package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException{
        HBox pane = new HBox();
        pane.setPrefWidth(800);
        pane.setStyle("-fx-background-color: white");
        InformationBar informationBar = new InformationBar();
        File source = new File("map2.txt");
        Scanner readFile = new Scanner(source);
        Player player = new Tank();
        Game game = null;
        Map map = null;
        map = new Map(readFile);
        game = new Game(map);
        BotPlayer botPlayer = new BotPlayer(map);
        game.addPlayer(player, botPlayer);

        pane.getChildren().add(map.getMap());
        pane.getChildren().add(informationBar.getInfoPane());
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnKeyPressed(ActionE -> {
            switch (ActionE.getCode()) {
                case W:
                    player.moveUp();
                    break;
                case S:
                    player.moveDown();
                    break;
                case D:
                    player.moveRight();
                    break;
                case A:
                    player.moveLeft();
                    break;
                case SPACE:
                    player.moveBullet();
                    break;
            }
        });
    }
}
