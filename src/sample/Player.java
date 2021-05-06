package sample;

public interface Player {
    void moveUp();
    void moveLeft();
    void moveRight();
    void moveDown();
    Position getPosition();
    void setMap(Map map);

    void moveBullet();
}
