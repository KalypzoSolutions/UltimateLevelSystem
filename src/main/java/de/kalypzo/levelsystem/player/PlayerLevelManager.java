package de.kalypzo.levelsystem.player;

import de.kalypzo.levelsystem.LevelSystem;
import de.kalypzo.levelsystem.categorys.LevelCategory;
import de.kalypzo.levelsystem.database.RedisManager;

import java.util.UUID;

public class PlayerLevelManager {

    private final LevelSystem plugin;

    public PlayerLevelManager(LevelSystem plugin) {
        this.plugin = plugin;
    }

    private RedisManager getRedisManager() {
        return plugin.getRedisManager();
    }



    public int getXp(UUID uuid, LevelCategory levelCategory) {
        if (getRedisManager().getJedis().exists("player:" + uuid + ":" + levelCategory.getName() + ":xp")){
            return Integer.parseInt(getRedisManager().getDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":xp").join());
        } else {
            /*
            TODO: Add MongoDB support
             */
            return 0;
        }
    }

    public void addXp(UUID uuid, LevelCategory levelCategory, int xp) {
        int newXp = getXp(uuid, levelCategory) + xp;
        int currentLevel = getLevel(uuid, levelCategory);
        int xpForNextLevel = levelCategory.getXpForNextLevel(currentLevel);
        while (true) {

            if (newXp >= xpForNextLevel) {
                int remainingXp = newXp - xpForNextLevel;
                setXp(uuid, levelCategory, remainingXp);
                setLevel(uuid, levelCategory, currentLevel + 1);
                newXp = remainingXp;
            } else {
                setXp(uuid, levelCategory, newXp);
                break;
            }
        }
    }

    public void setXp(UUID uuid, LevelCategory levelCategory, int xp) {
        if (getRedisManager().getJedis().exists("player:" + uuid + ":" + levelCategory.getName() + ":xp")){
            getRedisManager().updateDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":xp", String.valueOf(xp));
        } else {
            getRedisManager().setDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":xp", String.valueOf(xp));
        }
    }

    public void removeXp(UUID uuid, LevelCategory levelCategory, int xp) {
        int newXp = getXp(uuid, levelCategory) - xp;
        if (newXp < 0) {
            newXp = 0;
        }
        setXp(uuid, levelCategory, newXp);
    }


    public int getLevel(UUID uuid, LevelCategory levelCategory) {
        if (getRedisManager().getJedis().exists("player:" + uuid + ":" + levelCategory.getName() + ":level")){
            return Integer.parseInt(getRedisManager().getDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":level").join());
        } else {
            /*
            TODO: Add MongoDB support
             */
            return 0;
        }
    }

    public void setLevel(UUID uuid, LevelCategory levelCategory, int level) {
        if (getRedisManager().getJedis().exists("player:" + uuid + ":" + levelCategory.getName() + ":level")){
            getRedisManager().updateDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":level", String.valueOf(level));
        } else {
            getRedisManager().setDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":level", String.valueOf(level));
        }

    }
}
