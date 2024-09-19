package de.kalypzo.levelsystem.categorys;

public class DefaultLevelFormula implements LevelFormula {
    private final int baseXp;
    private final float xpMultiplier;

    public DefaultLevelFormula(int baseXp, float xpMultiplier) {
        this.baseXp = baseXp;
        this.xpMultiplier = xpMultiplier;
    }

    @Override
    public int getXpForNextLevel(int currentLevel) {
        return (int) (baseXp * Math.pow(1 + xpMultiplier, currentLevel - 1));
    }
}
