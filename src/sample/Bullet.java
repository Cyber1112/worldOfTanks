package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.awt.Rectangle;


public class Bullet{
    Map map;
    Pane bulletPane = new Pane();
    private double x, y;
    private double mX, mY;
    Circle bullet;
    int speed = 5;
    String bulletShootDir = "";


    public Bullet(Map map, double x, double y){
        this.x = x;
        this.y = y;
        this.map = map;
        this.map.pane.getChildren().add(bulletPane);
        if(bulletShootDir.equals("")){
            if(map.playerUp){
                bulletShootDir = "up";
            }else if(map.playerRight){
                bulletShootDir = "right";
            }else if(map.playerDown){
                bulletShootDir = "down";
            }
            else if(map.playerLeft){
                bulletShootDir = "left";
            }
        }
        bulletPane.getChildren().add(move(bulletShootDir));
    }


    public Node move(String dir){
        bullet = new Circle();
        bullet.setRadius(5);
        bullet.setCenterX(x+5);
        bullet.setCenterY(y+5);
        bullet.setFill(Color.YELLOW);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                if(dir.equals("up")){
                    for(int i = 0; i < map.numOfBricks(); i++){
                        for (int j = 0; j < map.numOfBricks(); j++){
                            if(new Rectangle((int)x, (int)y, 10, 10 ).intersects(
                                    new Rectangle(map.bricksX().get(j)*50, map.bricksY().get(i)*50, 50, 50)
                            )){
                                bullet.setVisible(false);
                            }
                        }
                    }
                    bullet.setCenterY(y -= speed);
                }
                if(dir.equals("right")){
                    for(int i = 0; i < map.numOfBricks(); i++){
                        for (int j = 0; j < map.numOfBricks(); j++){
                            if(new Rectangle((int)x, (int)y, 10, 10 ).intersects(
                                    new Rectangle(map.bricksX().get(j)*50, map.bricksY().get(i)*50, 50, 50)
                            )){
                                bullet.setVisible(false);
                            }
                        }
                    }
                    bullet.setCenterX(x += speed);
                }
                if(dir.equals("down")){
                    for(int i = 0; i < map.numOfBricks(); i++){
                        for (int j = 0; j < map.numOfBricks(); j++){
                            if(new Rectangle((int)x, (int)y, 10, 10 ).intersects(
                                    new Rectangle(map.bricksX().get(i)*50, map.bricksY().get(j)*50, 50, 50)
                            )){
                                bullet.setVisible(false);
                            }
                        }
                    }
                    bullet.setCenterY(y += speed);
                }
                if(dir.equals("left")){
                    for(int i = 0; i < map.numOfBricks(); i++){
                        for (int j = 0; j < map.numOfBricks(); j++){
                            if(new Rectangle((int)x, (int)y, 10, 10 ).intersects(
                                    new Rectangle(map.bricksX().get(i)*50, map.bricksY().get(j)*50, 50, 50)
                            )){
                                bullet.setVisible(false);
                            }
                        }
                    }
                    bullet.setCenterX(x -= speed);
                }
            }
        };
        timer.start();


        return bullet;
    }
    public int getBulletX(){
        return (int) x;
    }
    public int getBulletY(){
        return (int) y;
    }
}
