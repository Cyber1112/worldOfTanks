package sample;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;




public class InformationBar {
    GridPane infoPane;
    public InformationBar(){
        this.infoPane = new GridPane();
        infoPane.setStyle("-fx-background-color: white");
        infoPane.setPrefWidth(300);
        infoPane.setPrefHeight(500);
        infoPane.getChildren().add(infoTable());
    }
    public Node infoTable(){
        VBox vBox = new VBox();
        HBox player1 = new HBox(10);
        Label namePlayer = new Label("Player Owner");
        Label numOfLives = new Label("4");
        player1.getChildren().addAll(namePlayer, numOfLives);
        HBox player2 = new HBox(10);
        Label namePlayerOnline = new Label("Player Owner");
        Label numOfLivesOnline = new Label("4");
        player2.getChildren().addAll(namePlayerOnline, numOfLivesOnline);
        vBox.getChildren().addAll(player1, player2);
        return vBox;
    }
    public GridPane getInfoPane(){
        return infoPane;
    }

}
