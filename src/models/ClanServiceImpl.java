package models;

public class ClanServiceImpl implements ClanService{

    @Override
    public Clan get(long clanId) {
       return Game.getClans().stream().filter(p->p.getId()==clanId).findFirst().orElse(null);
    }
}
