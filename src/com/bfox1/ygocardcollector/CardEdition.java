package com.bfox1.ygocardcollector;

/**
 * Created by bfox1 on 6/24/15.
 * In God we Trust.
 */
public enum CardEdition {

    FIRSTEDITION("1st Edition"),
    ULIMITEDEDITION("Unlimited Edition"),
    LMITEDEDITION("Limited Edition");

    private final String edition;
    CardEdition(String edition)
    {
        this.edition = edition;
    }

    public String getEdition() {
        return edition;
    }
}
