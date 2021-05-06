package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Platform;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Map {
    private ArrayList<Integer> arrOfBricksX = new ArrayList<>();
    private ArrayList<Integer> arrOfBricksY = new ArrayList<>();
    Pane pane = new Pane();
    static boolean playerRight = false;
    static boolean playerLeft = false;
    static boolean playerDown = true;
    static boolean playerUp = false;
    private String[][] arr;
    private int size = 10;
    private int x, y;
    Position pos;
    Image tank;
    Image tree;
    ImageView treeBrickBrickMap;
    ImageView tankImage;
    public Map(Scanner readFile){
        pane.setStyle("-fx-background-color: black");
        pane.setPrefWidth(500);
        setRotation();
        arr = new String[size][size];
        for(int i = 0; i < size;  i++){
            for(int j = 0; j < size; j++){
                arr[i][j] = readFile.next();
                if(arr[i][j].equals("P") || arr[i][j].equals("0") || arr[i][j].equals("1")
                        || arr[i][j].equals("2") || arr[i][j].equals("3") || arr[i][j].equals("4")){
                    if(arr[i][j].equals("P")){
                        pos = new Position(j, i);
                        this.x = j*50;
                        this.y = i*50;
                        tankImage = new ImageView();
                        tankImage.setImage(tank);
                        tankImage.setX(j*50);
                        tankImage.setX(i*50);
                        pane.getChildren().add(tankImage);
                    }else if (arr[i][j].equals("0")){
                        Rectangle rect = new Rectangle();
                        rect.setStroke(Color.BLACK);
                        rect.setFill(Color.BLACK);
                        rect.setHeight(50);
                        rect.setWidth(50);
                        rect.setX(j*50);
                        rect.setY(i*50);
                        pane.getChildren().add(rect);
                    }else if(arr[i][j].equals("1")){
                        ImageView breakableBrickMap = new ImageView();
                        Image breakableBrick = new Image(getClass().getResource("bricks.png").toExternalForm());
                        breakableBrickMap.setFitHeight(50);
                        breakableBrickMap.setFitWidth(50);
                        breakableBrickMap.setX(j*50);
                        breakableBrickMap.setY(i*50);
                        arrOfBricksX.add(j);
                        arrOfBricksY.add(i);
                        breakableBrickMap.setImage(breakableBrick);
                        pane.getChildren().add(breakableBrickMap);
                    }else if(arr[i][j].equals("2")){
                        ImageView solidBrickMap = new ImageView();
                        Image solidBrick = new Image(getClass().getResource("wall.png").toExternalForm());
                        solidBrickMap.setFitHeight(50);
                        solidBrickMap.setFitWidth(50);
                        solidBrickMap.setX(j*50);
                        solidBrickMap.setY(i*50);
                        arrOfBricksX.add(j);
                        arrOfBricksY.add(i);
                        solidBrickMap.setImage(solidBrick);
                        pane.getChildren().add(solidBrickMap);
                    }else if(arr[i][j].equals("3")){
                        treeBrickBrickMap = new ImageView();
                        tree = new Image(getClass().getResource("tree.png").toExternalForm());
                        treeBrickBrickMap.setFitHeight(50);
                        treeBrickBrickMap.setFitWidth(50);
                        treeBrickBrickMap.setX(j*50);
                        treeBrickBrickMap.setY(i*50);
                        treeBrickBrickMap.setImage(tree);
                        pane.getChildren().add(treeBrickBrickMap);
                    }else if(arr[i][j].equals("4")){
                        ImageView waterBrickMap = new ImageView();
                        Image water = new Image(getClass().getResource("water.png").toExternalForm());
                        waterBrickMap.setFitHeight(50);
                        waterBrickMap.setFitWidth(50);
                        waterBrickMap.setX(j*50);
                        waterBrickMap.setY(i*50);
                        waterBrickMap.setImage(water);
                        pane.getChildren().add(waterBrickMap);
                    }
                }else{
                    System.out.println("Not Enough Map elemetns");
                }
            }
        }
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                int clientNum = 1;
                while (true) {
                    Socket socket = serverSocket.accept();
                    System.out.println(clientNum + " client connected");
                    clientNum++;
                    new Thread(() -> {
                        try {
                            DataInputStream fromClient = new DataInputStream(socket.getInputStream());
                            double layoutX = fromClient.readDouble();
                            double layoutY = fromClient.readDouble();
                            MultiPlayer tank = new MultiPlayer();
                            BulletPlayer bulletPlayer;
                            tank.setLayoutX(layoutX);
                            tank.setLayoutY(layoutY);
                            Platform.runLater(() -> {
                                pane.getChildren().add(tank);
                            });

                            while (true) {
                                String str = fromClient.readUTF();
                                switch (str){
                                    case "R":
                                        tank.moveRight();
                                        break;
                                    case "L":
                                        tank.moveLeft();
                                        break;
                                    case "U":
                                        tank.moveUp();
                                        break;
                                    case "D":
                                        tank.moveDown();
                                        break;
                                    case "F":
                                        if(tank.getDir().equals("up")){
                                            bulletPlayer = new BulletPlayer(tank.getPlayerX()+20, tank.getPlayerY(), tank.getDir());
                                        }else if(tank.getDir().equals("right")){
                                            bulletPlayer = new BulletPlayer(tank.getPlayerX()+40, tank.getPlayerY()+20, tank.getDir());
                                        }else if (tank.getDir().equals("down")){
                                            bulletPlayer = new BulletPlayer(tank.getPlayerX()+20, tank.getPlayerY()+40, tank.getDir());
                                        }else {
                                            bulletPlayer = new BulletPlayer(tank.getPlayerX(), tank.getPlayerY()+20, tank.getDir());
                                        }
                                        BulletPlayer finalBulletPlayer = bulletPlayer;
                                        Platform.runLater(() -> pane.getChildren().add(finalBulletPlayer));
                                        break;
                                }
                            }
                        } catch (IOException e) {

                        }
                    }).start();
                }
            }catch (IOException ex){

            }
        }).start();
    }
    public int getSize() {
        return this.size;
    }
    public Pane getMap(){
        return pane;
    }
    public char getValueAt(int x, int y) {
        this.x = x;
        this.y = y;
        return arr[y][x].charAt(0);
    }
    public ArrayList<Integer> bricksX(){
        return arrOfBricksX;
    }
    public ArrayList<Integer> bricksY(){
        return arrOfBricksY;
    }
    public int numOfBricks(){
        return arrOfBricksX.size();
    }
    public void setPositionTank(Position positionCurrent){
        setRotation();
        this.x = positionCurrent.getX();
        this.y = positionCurrent.getY();
        tankImage = new ImageView();
        tankImage.setImage(tank);
        tankImage.setLayoutX(x*50);
        tankImage.setLayoutY(y*50);
        pane.getChildren().add(tankImage);
        Rectangle tankClear = new Rectangle();
        tankClear.setWidth(50);
        tankClear.setHeight(50);
        tankClear.setStroke(Color.BLACK);
        tankClear.setFill(Color.BLACK);
        tankClear.setX(pos.getX()*50);
        tankClear.setY(pos.getY()*50);
        pane.getChildren().add(tankClear);
        pos = new Position(this.x, this.y);
    }
    public int getTankX(){
        return (int) x;
    }
    public int getTankY(){
        return (int) y;
    }
    public void setHideTank(Position position){
        setRotation();
        this.x = position.getX();
        this.y = position.getY();
        Rectangle rect = new Rectangle();
        rect.setStroke(Color.BLACK);
        rect.setFill(Color.BLACK);
        rect.setHeight(50);
        rect.setWidth(50);
        rect.setX(pos.getX()*50);
        rect.setY(pos.getY()*50);
        pane.getChildren().add(rect);
        treeBrickBrickMap = new ImageView();
        treeBrickBrickMap.setImage(tree);
        treeBrickBrickMap.setFitHeight(50);
        treeBrickBrickMap.setFitWidth(50);
        treeBrickBrickMap.setX(x*50);
        treeBrickBrickMap.setY(y*50);
        pane.getChildren().add(treeBrickBrickMap);
        pos = new Position(this.x, this.y);
    }
    public void setShowTankRight(Position position){
        setRotation();
        this.x = position.getX();
        this.y = position.getY();
        treeBrickBrickMap = new ImageView();
        treeBrickBrickMap.setImage(tree);
        treeBrickBrickMap.setFitHeight(50);
        treeBrickBrickMap.setFitWidth(50);
        treeBrickBrickMap.setX((x - 1)*50);
        treeBrickBrickMap.setY(y*50);
        pane.getChildren().add(treeBrickBrickMap);
        pos = new Position(this.x, this.y);
    }
    public void setShowTankLeft(Position position){
        setRotation();
        this.x = position.getX();
        this.y = position.getY();
        treeBrickBrickMap = new ImageView();
        treeBrickBrickMap.setImage(tree);
        treeBrickBrickMap.setFitHeight(50);
        treeBrickBrickMap.setFitWidth(50);
        treeBrickBrickMap.setX((x + 1)*50);
        treeBrickBrickMap.setY(y*50);
        pane.getChildren().add(treeBrickBrickMap);
        pos = new Position(this.x, this.y);
    }
    public void setShowTankUp(Position position){
        setRotation();
        this.x = position.getX();
        this.y = position.getY();
        treeBrickBrickMap = new ImageView();
        treeBrickBrickMap.setImage(tree);
        treeBrickBrickMap.setFitHeight(50);
        treeBrickBrickMap.setFitWidth(50);
        treeBrickBrickMap.setX(x*50);
        treeBrickBrickMap.setY((y + 1)*50);
        pane.getChildren().add(treeBrickBrickMap);
        pos = new Position(this.x, this.y);
    }
    public void setShowTankDown(Position position){
        setRotation();
        this.x = position.getX();
        this.y = position.getY();
        treeBrickBrickMap = new ImageView();
        treeBrickBrickMap.setImage(tree);
        treeBrickBrickMap.setFitHeight(50);
        treeBrickBrickMap.setFitWidth(50);
        treeBrickBrickMap.setX(x*50);
        treeBrickBrickMap.setY((y-1)*50);
        pane.getChildren().add(treeBrickBrickMap);
        pos = new Position(this.x, this.y);
    }
    public void setRotation(){
        if(playerRight){
            tank = new Image(getClass().getResource("playerRight.png").toExternalForm());
        } else if (playerLeft) {
            tank = new Image(getClass().getResource("playerLeft.png").toExternalForm());
        }else if (playerDown) {
            tank = new Image(getClass().getResource("playerDown.png").toExternalForm());
        }else if(playerUp){
            tank = new Image(getClass().getResource("playerUp.png").toExternalForm());
        }
    }

}


