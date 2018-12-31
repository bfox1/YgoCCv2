package com.bfox1.ygocardcollector;

/**
 * Created by bfox1 on 12/23/2016.
 */
public class CardScouter
{
    private final String name;

    private final String rarity;

    private final String type;


    public CardScouter(String name, String rarity, String type)
    {
        this.name = name;
        this.rarity = rarity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getType() {
        return type;
    }
}
