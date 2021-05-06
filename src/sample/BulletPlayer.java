package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BulletPlayer extends Pane{
    private double mX, mY;
    String bulletShootDir = "";
    Circle bulletPlayer;
    public BulletPlayer(double playerX, double playerY, String dir) {
        this.mX = playerX;
        this.mY = playerY;
        if(bulletShootDir.equals("")){
//            if(dir.equals("up")){
//                bulletShootDir = "up";
//            }else if(dir.equals("right")){
//                bulletShootDir = "right";
//            }else if(dir.equals("down")){
//                bulletShootDir = "down";
//            }else{
//                bulletShootDir = "left";
//            }
            bulletShootDir = dir;
        }
        System.out.println(dir);
        getChildren().add(moveBulletPlayer(bulletShootDir));
    }

    public Circle moveBulletPlayer(String bulletDir){
        bulletPlayer = new Circle();
        bulletPlayer.setCenterX(mX+5);
        bulletPlayer.setCenterY(mY+5);
        bulletPlayer.setRadius(5);
        bulletPlayer.setFill(Color.YELLOW);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(bulletDir.equals("up")){
                    bulletPlayer.setCenterY(mY -= 5);
                }
                if(bulletDir.equals("right")){
                    bulletPlayer.setCenterX(mX += 5);
                }
                if(bulletDir.equals("down")){
                    bulletPlayer.setCenterY(mY += 5);
                }
                if(bulletDir.equals("left")){
                    bulletPlayer.setCenterX(mX -= 5);
                }
            }
        };
        timer.start();
        return bulletPlayer;
    }
}
