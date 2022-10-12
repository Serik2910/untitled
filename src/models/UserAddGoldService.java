package models;

import java.util.logging.Level;

public class UserAddGoldService {
    private final ClanService clans;

    public UserAddGoldService(ClanService clans) {
        this.clans = clans;
    }

    public void addGoldToClan(long userId, long clanId, int gold) throws Exception {
        Clan clan = clans.get(clanId);
        String preamble = "adding gold "+ gold;
        if (clan!=null) {
            Player player = clan.getPlayerById(userId);
            if (player!=null){
                synchronized (clan) {
                    clan.incGold(gold);
                    clan.getLogger().log(Level.INFO, preamble + " : added via user service by userId=" + userId + ", in clanId=" + clanId);
                    System.out.println(preamble + " : added via user service by userId=" + userId + ", in clanId=" + clanId +". Total gold:"+clan.getGold() +". Thread = "+Thread.currentThread().getName());
                }
            }
            else{
                throw new Exception(preamble +": fault due player not found at clan with userId=" + userId + ", in clanId=" + clanId);

            }
        }else{
            throw new Exception(preamble +": fault due clan not found with userId=" + userId + ", in clanId=" + clanId);
        }
    }
}
