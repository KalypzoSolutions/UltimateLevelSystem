package de.kalypzo.levelsystem.categorys;

public interface LevelCategory {
    String getName();
    int getXpForNextLevel(int currentLevel);
}
