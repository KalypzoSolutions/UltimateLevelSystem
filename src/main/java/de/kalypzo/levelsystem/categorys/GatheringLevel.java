package de.kalypzo.levelsystem.categorys;

public class GatheringLevel implements LevelCategory{
    private final int baseXp;
    private final double xpMultiplier;

    public GatheringLevel() {
        this.baseXp = 1000;
        this.xpMultiplier = 10;
    }

    @Override
    public String getName() {
        return "Gathering";
    }

    @Override
    public int getXpForNextLevel(int currentLevel) {
        return (int) (baseXp * Math.pow(1 + xpMultiplier, currentLevel - 1));
    }
}
