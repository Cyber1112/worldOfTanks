package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.*;
import java.util.Random;

import static java.util.Arrays.fill;

public class BotPlayer extends Tank implements Player{
    Map map;
    Pane botPane;
    boolean playerRight;
    boolean playerLeft;
    boolean playerDown;
    boolean playerUp;
    Random random;
    int botX;
    int botY;
    Image tank;
    static ImageView enemyTankImage;
    private Position position;
    private int x;
    private int y;

    public BotPlayer(Map map){
        enemyTankImage = new ImageView();
        playerRight = false;
        playerLeft = false;
        playerDown = false;
        playerUp = true;
        position = new Position();
        this.map = map;
        botPane = new Pane();

        this.map.pane.getChildren().add(botPane);
        createTank();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Runnable updater = new Runnable() {
                            @Override
                            public void run() {
                                if(map.getTankX() < enemyTankImage.getX()){
                                    createUpdaterTank("moveLeft");
                                }else{
                                    createUpdaterTank("moveRight");
                                }
                                if(map.getTankY() < enemyTankImage.getY()){
                                    createUpdaterTank("moveUp");
                                }else{
                                    createUpdaterTank("moveDown");
                                }
                            }
                        };

                        while (true) {
                            try {

                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                            }

                            // UI update is run on the Application thread
                            Platform.runLater(updater);
                        }
                    }

                });
                thread.setDaemon(true);
                thread.start();
        Timeline createNewTank = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                createTank();
                            }
                        }));
        createNewTank.setCycleCount(Timeline.INDEFINITE);
        createNewTank.play();

    }
    public void createUpdaterTank(String str){
        tank = new Image(getClass().getResource("enemyTankUp.png").toExternalForm());
        enemyTankImage.setImage(tank);
        enemyTankImage.setFitWidth(50);
        enemyTankImage.setFitHeight(50);
        enemyTankImage.setViewOrder(9);
        if(str == "moveUp"){
            enemyTankImage.setY(y-=50);
        }else if(str == "moveLeft"){
            enemyTankImage.setX(x-=50);
        }else if(str == "moveRight"){
            enemyTankImage.setX(x+=50);
        }else if(str == "moveDown"){
            enemyTankImage.setY(y+=50);
        }
//        System.out.println(botX + " " + botY);
    }

    public void createTank(){
        Random rand = new Random();
        enemyTankImage = new ImageView();
        tank = new Image(getClass().getResource("enemyTankUp.png").toExternalForm());
        enemyTankImage.setImage(tank);
        enemyTankImage.setFitWidth(50);
        enemyTankImage.setFitHeight(50);
        do{
            botX = rand.nextInt(10)*50;
            botY = rand.nextInt(10)*50;
        }while (map.getValueAt((int) (botX / 50),(int) (botY / 50)) != '0');
        enemyTankImage.setX(botX);
        enemyTankImage.setY(botY);
//        System.out.println(botX + " " + botY);
        botPane.getChildren().add(enemyTankImage);
    }
    public void moveUp(){
        enemyTankImage.setY(y-=50);
        tank = new Image(getClass().getResource("enemyTankUp.png").toExternalForm());
        enemyTankImage.setImage(tank);
        enemyTankImage.setFitWidth(50);
        enemyTankImage.setFitHeight(50);
    }
    public void moveLeft(){
        enemyTankImage.setX(x-=50);
        tank = new Image(getClass().getResource("enemyTankLeft.png").toExternalForm());
        enemyTankImage.setImage(tank);
        enemyTankImage.setFitWidth(50);
        enemyTankImage.setFitHeight(50);
    }
    public void moveRight(){
        enemyTankImage.setX(x+=50);
        tank = new Image(getClass().getResource("enemyTankRight.png").toExternalForm());
        enemyTankImage.setImage(tank);
        enemyTankImage.setFitWidth(50);
        enemyTankImage.setFitHeight(50);
    }
    public void moveDown(){
        enemyTankImage.setY(y+=50);
        tank = new Image(getClass().getResource("enemyTankDown.png").toExternalForm());
        enemyTankImage.setImage(tank);
        enemyTankImage.setFitWidth(50);
        enemyTankImage.setFitHeight(50);
    }

}
