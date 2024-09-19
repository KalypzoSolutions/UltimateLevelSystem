package de.kalypzo.levelsystem.xp;

import org.jetbrains.annotations.Nullable;

public interface XpProvider {

    @Nullable Integer getXpAmountByType(String type);
}
