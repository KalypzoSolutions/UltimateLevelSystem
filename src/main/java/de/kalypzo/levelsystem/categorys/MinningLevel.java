package de.kalypzo.levelsystem.categorys;

public class MinningLevel implements LevelCategory{
    private final int baseXp;
    private final double xpMultiplier;

    public MinningLevel() {
        this.baseXp = 1000;
        this.xpMultiplier = 10;
    }

    @Override
    public String getName() {
        return "Minning";
    }

    @Override
    public int getXpForNextLevel(int currentLevel) {
        return (int) (baseXp * Math.pow(1 + xpMultiplier, currentLevel - 1));
    }
}
