package com.bfox1.ygocardcollector;

/**
 * Created by bfox1 on 6/24/15.
 * In God we Trust.
 */
public enum CardRarity {

    NORMAL("Normal",null, null, null),
    BLACKRARE("BlackRare",null,null, null),
    RARE("Rare", null, null, null),
    SUPERRARE("SuperRare", null, null, null),
    ULTRARARE("UltraRare", null, null, null),
    ULTIMATERARE("UltimateRare", null, null, null),
    PLATINUMRARE("PlatinumRare", null, null, null),
    GOLDRARE("GoldRare", null, null, null),
    SECRETRARE("SecretRare", null, null, null);


    private final String rarity;
    private String packInitials;
    private String packID;
    private CardEdition cardEdition;


     CardRarity(String rarity, String packInitials, String packID, CardEdition edition)
     {
         this.rarity = rarity;
        this.packID = packID;
         this.packInitials = packInitials;
         this.cardEdition = edition;
     }


    public String getPackInitials() {
        return packInitials;
    }

    public void setPackInitials(String packInitials) {
        this.packInitials = packInitials;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public String getRarity() {
        return rarity;
    }

    public CardEdition getCardEdition() {
        return cardEdition;
    }

    public void setCardEdition(CardEdition cardEdition) {
        this.cardEdition = cardEdition;
    }
}
