package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class MultiPlayer extends Pane {
    private ImageView tank;
    static String str = "";
    public MultiPlayer(){
        tank = new ImageView(new Image(getClass().getResource("playerUp.png").toExternalForm()));
        tank.setFitWidth(50);
        tank.setFitHeight(50);
        getChildren().add(tank);
        System.out.println(str);
    }
    public void moveRight(){
        tank.setRotate(90);
        str = "right";
            if (getLayoutX() == 450) {

            }else {
                Platform.runLater(() -> setLayoutX(getLayoutX() + 50));
            }

    }
    public void moveLeft(){
        tank.setRotate(270);
        str = "left";
            if(getLayoutX() == 0 ){

            } else{
                Platform.runLater(() -> setLayoutX(getLayoutX() - 50));
            }
    }
    public void moveUp(){
        str = "up";
        tank.setRotate(0);
        if(getLayoutY() == 0){

        }else{
            Platform.runLater(() -> setLayoutY(getLayoutY() - 50));
        }

    }
    public void moveDown(){
        str = "down";
        tank.setRotate(180);
        if(getLayoutY() == 450){

        }else {
            Platform.runLater(() -> setLayoutY(getLayoutY() + 50));
        }

    }
    public int getPlayerX(){
        return (int) getLayoutX();
    }
    public int getPlayerY(){
        return (int) getLayoutY();
    }
    public static String getDir(){
        return str;
    }
}
