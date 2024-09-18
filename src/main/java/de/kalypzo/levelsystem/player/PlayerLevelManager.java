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


    /**
     * Gets the XP of the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @return the XP of the player
     */
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


    /**
     * Adds XP to the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @param xp is the XP to add
     */
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


    /**
     * Sets the XP of the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @param xp is the XP to set
     */
    public void setXp(UUID uuid, LevelCategory levelCategory, int xp) {
        if (getRedisManager().getJedis().exists("player:" + uuid + ":" + levelCategory.getName() + ":xp")){
            getRedisManager().updateDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":xp", String.valueOf(xp));
        } else {
            getRedisManager().setDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":xp", String.valueOf(xp));
        }
    }


    /**
     * Removes XP from the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @param xp is the XP to remove
     */
    public void removeXp(UUID uuid, LevelCategory levelCategory, int xp) {
        int newXp = getXp(uuid, levelCategory) - xp;
        if (newXp < 0) {
            newXp = 0;
        }
        setXp(uuid, levelCategory, newXp);
    }


    /**
     * Gets the level of the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @return the level of the player
     */
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


    /**
     * Sets the level of the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @param level is the level to set
     */
    public void setLevel(UUID uuid, LevelCategory levelCategory, int level) {
        if (getRedisManager().getJedis().exists("player:" + uuid + ":" + levelCategory.getName() + ":level")){
            getRedisManager().updateDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":level", String.valueOf(level));
        } else {
            getRedisManager().setDataAsync("player:" + uuid + ":" + levelCategory.getName() + ":level", String.valueOf(level));
        }
    }


    /**
     * Removes a level from the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @param level is the level to remove
     */
    public void removeLevel(UUID uuid, LevelCategory levelCategory, int level) {
        int newLevel = getLevel(uuid, levelCategory) - level;
        if (newLevel < 0) {
            newLevel = 0;
        }
        setLevel(uuid, levelCategory, newLevel);
    }


    /**
     * Adds a level to the player
     * @param uuid is the UUID of the player
     * @param levelCategory is the category of the level
     * @param level is the level to add
     */
    public void addLevel(UUID uuid, LevelCategory levelCategory, int level) {
        int newLevel = getLevel(uuid, levelCategory) + level;
        setLevel(uuid, levelCategory, newLevel);
    }
}