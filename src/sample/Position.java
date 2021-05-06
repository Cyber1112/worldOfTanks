package sample;

public class Position {
    private int x;
    private int y;
    public Position(){}
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position pos){
        if(pos.getX() == this.x && pos.getY() == this.y){
            return true;
        }
        return false;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
}
