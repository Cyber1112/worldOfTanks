package sample;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static java.util.Arrays.fill;

class Tank extends MyPlayer{
    private int[] moveDir = {0, 0, 0, 1};
    private ImageView tank;
    public Tank(){
        tank = new ImageView(new Image(getClass().getResource("playerUp.png").toExternalForm()));
        tank.setFitWidth(50);
        tank.setFitHeight(50);
    }
    private void defaultArr(){
        fill(moveDir, 0);
    }
    public void moveRight() {
        if(moveDir[0] == 1){
            super.moveRight();
        }
        else{
            defaultArr();
            Platform.runLater(() -> moveDir[0] = 1);
        }
    }
    public void moveLeft() {
        if(moveDir[1] == 1){
            super.moveLeft();
        }
        else{
            defaultArr();
            Platform.runLater(() -> moveDir[1] = 1);
//            moveDir[1] = 1;
        }
    }
    public void moveDown() {
        if(moveDir[2] == 1){
            super.moveDown();
        }
        else{
            defaultArr();
            Platform.runLater(() -> moveDir[2] = 1);
//            moveDir[2] = 1;
        }

    }

    public void moveUp() {
        if(moveDir[3] == 1){
            super.moveUp();
        }
        else{
            defaultArr();
            Platform.runLater(() -> moveDir[3] = 1);
//            moveDir[3] = 1;
        }
    }
}
