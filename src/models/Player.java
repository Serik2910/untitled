package models;

import java.util.logging.Level;

public class Player implements Runnable {
    private static long current_id = 1L;
    private static UserAddGoldService userAddGoldService = new UserAddGoldService(new ClanServiceImpl());
    private long id;
    private String name;
    private Clan clan;

    public long getId() {
        return id;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }
    public Player(String name) {
        this.id = current_id++;
        this.name = name;
        this.clan = null;

    }
    @Override
    public void run() {
        if(this.clan!=null){
            int minMoney = 10;
            int maxMoney = 100;

            //Generate random int value from minMoney to maxMoney

            int money = (int)(Math.random()*(maxMoney-minMoney+1)+minMoney);
            try {
                userAddGoldService.addGoldToClan(this.id,this.clan.getId(),money);
            } catch (Exception e) {
                clan.getLogger().log(Level.WARNING, e.getMessage());
                System.out.println(e.getMessage() );
            }
        }
    }
}
