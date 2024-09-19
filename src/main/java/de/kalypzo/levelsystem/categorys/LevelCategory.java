package de.kalypzo.levelsystem.categorys;

public interface LevelCategory extends LevelFormula {

    MiningLevel MINING_LEVEL = new MiningLevel(new DefaultLevelFormula(1000, 10));

    String getName();


}
