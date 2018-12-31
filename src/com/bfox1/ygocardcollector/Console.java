package com.bfox1.ygocardcollector;

import com.bfox1.ygocardcollector.util.ConsolePrint;

import java.util.Scanner;

/**
 * Created by bfox1 on 6/24/15.
 * In God we Trust.
 */
public class Console {




    /**
     * Adds cards to the card database.
     */
    public void addCard()
    {
        Scanner scanner = new Scanner(System.in);

        ConsolePrint.print("Welcome to YGOCardCollector!");
        ConsolePrint.print("Please enter the listed info.");
        ConsolePrint.print("Enter in order, Card Name, Rarity, Edition, Pack initials, ID, quantity");




        //TODO Load card data to check if data already exists.

    }

    public void removeCard()
    {

    }

    /**
     * @return Yugioh card name
     */
    private String setCardName()
    {
        print("Insert Card Name:");
        return new Scanner(System.in).nextLine();
    }

    public static void print(String msg)
    {
        ConsolePrint.print(msg);
    }

    public String setPackInitials()
    {
        print("Insert Pack initials");
        String pi = new Scanner(System.in).next();
        if(pi.length() > 1)
        {
            return pi;
        }
        else
        {
            ConsolePrint.print("Invalid Pack Initials! Not enough or too much letter input! reinput the pack initials");
            setPackInitials();
        }
        throw new IllegalArgumentException("No pack init found!");
    }

    public String setPackID()
    {
        print("Insert Pack ID");
        String id = new Scanner(System.in).next();
        if(id.length() > 1) return id;
        else {
            ConsolePrint.print("Invalid Pack Id! Not enough or too much letter input! reinput the pack ID");
            setPackID();
        }
        throw new IllegalArgumentException("No pack ID found!");
    }

    public CardEdition setCardEdition()
    {
        print("Insert Card Edition");
        String edition = new Scanner(System.in).next();

        switch (edition)
        {
            case "first":
                return CardEdition.FIRSTEDITION;
            case "limited":
                return CardEdition.LMITEDEDITION;
            case "unlimited":
                return CardEdition.ULIMITEDEDITION;
            default:
                ConsolePrint.print("Invalid arguments! ie: <first, limited, unlimited> only!\n" +
                        "Please re-enter the card Edition.");
                setCardRarity();
                break;
        }
        return null;
    }

    public CardRarity setCardRarity()
    {
        print("Insert Card Rarity");
        String rarity = new Scanner(System.in).next();
        switch(rarity)
        {
            case "normal":
                return CardRarity.NORMAL;

            case "rare":
                return CardRarity.RARE;

            case "blackrare":
                return CardRarity.BLACKRARE;

            case "superrare":
                return CardRarity.SUPERRARE;

            case "ultrarare":
                return CardRarity.ULTRARARE;

            case "ultimaterare":
                return CardRarity.ULTIMATERARE;

            case "goldrare":
                return CardRarity.GOLDRARE;

            case "platinumrare":
                return CardRarity.PLATINUMRARE;

            case "secretrare":
                return  CardRarity.SECRETRARE;

            default:
                ConsolePrint.print("Invalid rarity type! IE: <normal, rare, goldrare, secretrare>\n" +
                        "Please re-enter the card rarity!");
                setCardRarity();
                break;
        }
        return null;
    }

    public String setCardType()
    {
        print("Insert a card type IE <monster, spell, trap>");
        String type = new Scanner(System.in).next();

        if(type.equalsIgnoreCase("monster")) return type;
        else if (type.equalsIgnoreCase("spell")) return type;
        else if (type.equalsIgnoreCase("trap")) return type;
        else
        {
            print("Invalid card type! IE: <monster, spell, trap>");
            setCardType();
        }
        return null;
    }




}
