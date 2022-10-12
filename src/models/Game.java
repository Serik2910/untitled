package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Game {
    private static final int MAX_PLAYERS = 30;
    private static final int MAX_CLANS = 3;
    private static Logger gameLogger = Logger.getLogger("Game");
    private static FileHandler fh;
    private static List<Player> players;
    private static List<Clan> clans;

    public static List<Player> getPlayers() {
        return players;
    }
    static {
        try {
            fh = new FileHandler("GameLog.log");
            gameLogger.addHandler(fh);;
            gameLogger.setLevel(Level.ALL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clans = new ArrayList<>();
        // симулятор регистрации Кланов
        for (int i=1; i<=MAX_CLANS;i++){
            try {
                clans.add(new Clan("Clan_"+i));
            } catch (IOException e) {
                gameLogger.log(Level.WARNING,"Error on creating logging file", e);
            }
        }
        players = new ArrayList<>();
        // симулятор регистрации Игроков
        for (int i=1; i<=MAX_PLAYERS;i++){
            players.add(new Player("Player_"+i));
        }


    }
    public static List<Clan> getClans() {
        return clans;
    }


    public static void main(String[] args) { // симулятор распределения по кланам
        int min = 0;
        int max = 3;

        //Generate random int value from 0 to max
        for (var player:
             players) {
            int clanIndex = (int)(Math.random()*(max-min+1)+min);
            if(clanIndex<(MAX_CLANS-1)) { //b == 3, то игрок без клана
                clans.get(clanIndex).addPlayer(player);
            }
        }
        for (var player:
                players){
            Thread t = new Thread(player);
            t.setName("Thread "+ player.getId());
            t.start();
        }

    }
}
