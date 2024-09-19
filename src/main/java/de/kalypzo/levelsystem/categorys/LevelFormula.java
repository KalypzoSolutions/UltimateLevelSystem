package de.kalypzo.levelsystem.categorys;

/**
 * Provides a formula to calculate the xp needed for the next level.
 */
public interface LevelFormula {

    int getXpForNextLevel(int currentLevel);

}
