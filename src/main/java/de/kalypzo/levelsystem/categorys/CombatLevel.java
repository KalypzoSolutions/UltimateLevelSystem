package de.kalypzo.levelsystem.categorys;

public class CombatLevel implements LevelCategory{
    private final int baseXp;
    private final double xpMultiplier;

    public CombatLevel() {
        this.baseXp = 1000;
        this.xpMultiplier = 10;
    }

    @Override
    public String getName() {
        return "Combat";
    }

    @Override
    public int getXpForNextLevel(int currentLevel) {
        return (int) (baseXp * Math.pow(1 + xpMultiplier, currentLevel - 1));
    }
}

