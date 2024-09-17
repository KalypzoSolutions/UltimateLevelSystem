package de.kalypzo.levelsystem.player;

import de.kalypzo.levelsystem.categorys.LevelCategory;

import java.util.UUID;

public class PlayerLevelManager {

    public int getXp(UUID uuid, LevelCategory levelCategory) {
        return 0;
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

    }

    public void removeXp(UUID uuid, LevelCategory levelCategory, int xp) {
    }


    public int getLevel(UUID uuid, LevelCategory levelCategory) {
        return 0;
    }

    public void setLevel(UUID uuid, LevelCategory levelCategory, int level) {

    }
}
