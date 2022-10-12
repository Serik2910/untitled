package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Clan {
    private static int current_id = 1;

    private Logger logger;
    private long id;     // id клана
    private String name; // имя клана
    private int gold;    // текущее количество золота в казне клана
    private List<Player> players;

    public Player getPlayerById(long id) {
        return players.stream().filter(p->p.getId()==id).findFirst().orElse(null);
    }

    public long getId() {
        return id;
    }

    public void addPlayer(Player player){
        player.setClan(this);
        this.players.add(player);
    }

    public int getGold() {
        return gold;
    }

    public Clan(String name) throws IOException {
        this.id = current_id++;
        this.name = name;
        this.logger =  Logger.getLogger(this.name+"_"+this.id);
        FileHandler fh = new FileHandler(this.name+"_"+this.id+".log");
        this.logger.addHandler(fh);
        this.logger.setLevel(Level.ALL);
        this.gold = 0;
        this.players = new ArrayList<>();
    }

    public Logger getLogger() {
        return logger;
    }

    public void incGold(int gold){
        this.gold += gold;
    }
}
