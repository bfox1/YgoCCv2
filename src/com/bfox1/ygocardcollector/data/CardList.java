package com.bfox1.ygocardcollector.data;

import com.bfox1.ygocardcollector.YugiohCardCollector;
import sample.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CardList
{

    private transient List<String> monster = new ArrayList<>();

    private transient List<String> spell = new ArrayList<>();

    private transient List<String> trap = new ArrayList<>();

    private transient List<String> extra = new ArrayList<>();

    public CardList(String fileSafeName)
    {

    }

    public void scanLocalCards(String fileName, File F)
    {

        for(File cardFile : F.listFiles())
        {
            getPredictiveResults(cardFile.getName(), fileName, cardFile);
        }

        YugiohCardCollector collector = new YugiohCardCollector(Controller.getInstance());

        collector.addCards(monster, 0);
        collector.addCards(extra, 1);
        collector.addCards(spell,2);
        collector.addCards(trap, 3);

    }


    /**
     * Special method that searches for cards locally for card names that match the text field that you input.
     * @param name The name of the card in question.
     * @param search The Searching card user input.
     * @param file The file of the card. Parameter may not be needed.
     */
    public void getPredictiveResults(String name, String search, File file)
    {

        char[] c = name.toLowerCase().toCharArray();

        char[] query = search.toCharArray();

        boolean flag = false;


        for(int i = 0; i < c.length; i++)
        {
            if(query.length > i)
            {

                if(String.valueOf(query[i]).equalsIgnoreCase(String.valueOf(c[i])))
                {
                    flag = true;
                    continue;
                }
                else
                {
                    flag = false;
                    break;
                }
            }
        }

        if(flag)
        {

            try
            {
                YgoCard card = YgoCard.loadCardData(name.replace(".JSON", ""), 0);
                if(card.getCardData().get("ExtraType") == null)
                {
                    String att = (String) card.getCardData().get("CardAttribute");
                    if(att.toLowerCase().equalsIgnoreCase("spell"))
                    {
                        this.spell.add(name.replace(".JSON", ""));
                    }
                    else if(att.toLowerCase().equalsIgnoreCase("trap"))
                    {
                        this.trap.add(name.replace(".JSON", ""));
                    }
                    else
                    {
                        this.monster.add(name.replace(".JSON", ""));
                    }
                }
                else
                {
                    this.extra.add(name.replace(".JSON", ""));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}
