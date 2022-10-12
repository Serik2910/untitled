package models;

public class TaskService {
    private final ClanService clans;

    public TaskService(ClanService clans) {
        this.clans = clans;
    }

    void completeTask(long clanId, long taskId) {
        // ...

        // if (success)
        {
            Clan clan = clans.get(clanId);
            // clan.[gold] += gold;
            // как-то сохранить изменения
        }
    }
}
