package de.kalypzo.levelsystem.categorys;

public class MiningLevel implements LevelCategory {
    private final LevelFormula levelFormula;

    public MiningLevel(LevelFormula levelFormula) {
        this.levelFormula = levelFormula;
    }

    @Override
    public String getName() {
        return "Mining";
    }

    @Override
    public int getXpForNextLevel(int currentLevel) {
        return levelFormula.getXpForNextLevel(currentLevel);
    }
}
