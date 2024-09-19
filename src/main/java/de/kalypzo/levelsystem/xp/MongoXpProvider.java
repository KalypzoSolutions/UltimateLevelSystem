package de.kalypzo.levelsystem.xp;


import java.util.HashMap;

public class MongoXpProvider implements XpProvider {

    private final HashMap<String, Integer> example = new HashMap<>();


    @Override
    public Integer getXpAmountByType(String type) {
        return example.get(type);
    }
}
