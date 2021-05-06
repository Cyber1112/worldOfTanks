package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Application {
    private StackPane stackPane;
    private DataOutputStream toServer;
    public void init(){
        stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: white");
        stackPane.setPrefWidth(400);
        stackPane.setPrefHeight(400);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox(10);
        vBox.setMaxSize(200, 200);
        vBox.setAlignment(Pos.CENTER);
        TextField userName = new TextField();
        userName.setPromptText("username");

        HBox group = new HBox(5);
        TextField layoutX = new TextField();
        layoutX.setPromptText("layout X");
        TextField layoutY = new TextField();
        layoutY.setPromptText("layout Y");
        group.getChildren().addAll(layoutX, layoutY);

        Button btnJoin = new Button("Join");
        btnJoin.setOnAction(event -> {
            stage.setScene(new Scene(controls()));
            stage.show();
            try {
                double layoutTankX = Double.parseDouble(layoutX.getText());
                double layoutTankY = Double.parseDouble(layoutY.getText());
                String user = userName.getText();
                connectToServer(layoutTankX, layoutTankY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        vBox.getChildren().addAll(userName, group, btnJoin);
        stackPane.getChildren().add(vBox);

        stage.setScene(new Scene(stackPane));
        stage.setTitle("Player");
        stage.show();

    }
    public StackPane controls(){
        StackPane controlPane = new StackPane();

        VBox vBox = new VBox(5);
        HBox hBox = new HBox(5);

        vBox.setAlignment(Pos.CENTER);
        Button btnUp = new Button("Up");
        btnUp.setOnAction(e -> {
            try {
                toServer.writeUTF("U");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Button btnDown = new Button("Down");
        btnDown.setOnAction(e -> {
            try {
                toServer.writeUTF("D");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Button btnRight = new Button("Right");
        btnRight.setOnAction(e -> {
            try {
                toServer.writeUTF("R");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Button btnLeft = new Button("Left");
        btnLeft.setOnAction(e -> {
            try {
                toServer.writeUTF("L");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        Button btnShoot = new Button("Shoot");
        btnShoot.setOnAction(e -> {
            try {
                toServer.writeUTF("F");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(btnLeft, btnRight, btnShoot);
        vBox.getChildren().addAll(btnUp, hBox, btnDown);
        controlPane.setStyle("-fx-background-color: white");
        controlPane.setPrefWidth(400);
        controlPane.setPrefHeight(400);
        controlPane.getChildren().add(vBox);
        return controlPane;
    }
    public void connectToServer(double layoutTankX, double layoutTankY) throws IOException{
        Socket client = new Socket("localhost", 8000);
        toServer = new DataOutputStream(client.getOutputStream());
        toServer.writeDouble(layoutTankX);
        toServer.writeDouble(layoutTankY);
    }
}
