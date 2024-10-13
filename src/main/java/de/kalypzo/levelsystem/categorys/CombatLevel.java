package de.kalypzo.levelsystem.categorys;

public class CombatLevel implements LevelCategory {
    private final LevelFormula levelFormula;

    public CombatLevel(LevelFormula levelFormula) {
        this.levelFormula = levelFormula;
    }

    @Override
    public String getName() {
        return "Combat";
    }

    @Override
    public int getXpForNextLevel(int currentLevel) {
        return levelFormula.getXpForNextLevel(currentLevel);
    }
}




