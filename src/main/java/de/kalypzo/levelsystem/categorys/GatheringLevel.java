package de.kalypzo.levelsystem.categorys;

public class GatheringLevel implements LevelCategory {
    private final LevelFormula levelFormula;

    public GatheringLevel(LevelFormula levelFormula) {
        this.levelFormula = levelFormula;
    }

    @Override
    public String getName() {
        return "Gathering";
    }

    @Override
    public int getXpForNextLevel(int currentLevel) {
        return levelFormula.getXpForNextLevel(currentLevel);
    }
}
