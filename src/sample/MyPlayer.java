package sample;

import javafx.scene.image.Image;

public class MyPlayer implements Player{
    Map map;
    Bullet playerBullet;
    Position position = new Position();
    @Override
    public void setMap(Map map){
        this.map = map;
        for(int i = 0; i < map.getSize(); i++){
            for(int j = 0; j < map.getSize(); j++){
                if(map.getValueAt(j, i) == 'P'){
                    position.setX(j);
                    position.setY(i);
                    break;
                }
            }
        }
    }

    @Override
    public void moveBullet() {
        if(map.playerUp){
            playerBullet = new Bullet(map, position.getX()*50+20, position.getY()*50);
        }else if(map.playerRight){
            playerBullet = new Bullet(map,position.getX()*50+40, position.getY()*50+20);
        }else if(map.playerLeft){
            playerBullet = new Bullet(map,position.getX()*50, position.getY()*50+20);
        }else if(map.playerDown){
            playerBullet = new Bullet(map, position.getX()*50+20, position.getY()*50+40);
        }
    }

    @Override
    public void moveUp(){
        map.playerUp = true;
        map.playerRight = false;
        map.playerLeft = false;
        map.playerDown = false;
        if(position.getY() == 0){

        }else if(map.getValueAt(position.getX(), position.getY() - 1) == '3'){
            position.setY(getPosition().getY() - 1);
            this.map.setHideTank(getPosition());
        }else if(map.getValueAt(position.getX(), position.getY() - 1) != '1' &&
                map.getValueAt(position.getX(), position.getY() - 1) != '2'
                && map.getValueAt(position.getX(), position.getY() - 1) != '4'){
            position.setY(getPosition().getY() - 1);
            this.map.setPositionTank(getPosition());
        }
        if(map.getValueAt(position.getX(), position.getY() + 1) == '3'){
            this.map.setShowTankUp(getPosition());
        }
        System.out.println(map.getTankX() + " " + map.getTankY());
    }
    @Override
    public void moveLeft(){
        map.playerUp = false;
        map.playerRight = false;
        map.playerLeft = true;
        map.playerDown = false;
        if(position.getX() == 0){

        }else if(map.getValueAt(position.getX() - 1, position.getY()) == '3'){
            position.setX(getPosition().getX() - 1);
            this.map.setHideTank(getPosition());
        }else if(map.getValueAt(position.getX() - 1, position.getY()) != '1' &&
                map.getValueAt(position.getX() - 1, position.getY()) != '2' &&
                map.getValueAt(position.getX() - 1, position.getY()) != '4'){
            position.setX(getPosition().getX() - 1);
            this.map.setPositionTank(getPosition());
        }
        if(map.getValueAt(position.getX() + 1, position.getY()) == '3'){
            this.map.setShowTankLeft(getPosition());
        }
    }
    @Override
    public void moveRight(){
        map.playerUp = false;
        map.playerRight = true;
        map.playerLeft = false;
        map.playerDown = false;
        if(position.getX() == map.getSize() - 1){

        }else if(map.getValueAt(position.getX() + 1, position.getY()) == '3'){
            position.setX(getPosition().getX() + 1);
            this.map.setHideTank(getPosition());
        }else if(map.getValueAt(position.getX() + 1, position.getY()) != '1' &&
                map.getValueAt(position.getX() + 1, position.getY()) != '2' &&
                map.getValueAt(position.getX() + 1, position.getY()) != '4'){
            position.setX(getPosition().getX() + 1);
            this.map.setPositionTank(getPosition());
        }
        if(map.getValueAt(position.getX() - 1, position.getY()) == '3'){
            this.map.setShowTankRight(getPosition());
        }
    }
    @Override
    public void moveDown(){
        map.playerUp = false;
        map.playerRight = false;
        map.playerLeft = false;
        map.playerDown = true;
        if(position.getY() == map.getSize() - 1){

        }else if(map.getValueAt(position.getX(), position.getY() + 1) == '3') {
            position.setY(getPosition().getY() + 1);
            this.map.setHideTank(getPosition());
        }else if(map.getValueAt(position.getX(), position.getY() + 1) != '1' &&
                map.getValueAt(position.getX(), position.getY() + 1) != '2' &&
                map.getValueAt(position.getX(), position.getY() + 1) != '4'){
            position.setY(getPosition().getY() + 1);
            this.map.setPositionTank(getPosition());
        }
        if(map.getValueAt(position.getX(), position.getY() - 1) == '3'){
            this.map.setShowTankDown(getPosition());
        }

    }
    @Override
    public Position getPosition(){
        return position;
    }

}
