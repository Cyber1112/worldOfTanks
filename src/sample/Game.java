package sample;

public class Game {
    private int x;
    private int y;
    Map map;
    Player player;
    BotPlayer botPlayer;
    public Game(Map map){
        this.map = map;
    }
    public void setMap(Map map){
        this.map=map;
    }
    public void addPlayer(Player player, BotPlayer botPlayer){
        this.player = player;
        this.botPlayer = botPlayer;
        this.player.setMap(map);
    }

}
