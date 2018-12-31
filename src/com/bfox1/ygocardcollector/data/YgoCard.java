package com.bfox1.ygocardcollector.data;

import com.bfox1.ygocardcollector.YugiohCardCollector;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import sample.Controller;
import sample.YgoOpenController;

import java.io.*;
import java.util.*;

/**
 * Created by bfox1 on 12/27/2016.
 */
public class YgoCard
{
    private LinkedHashMap<String, Object> cardData;

    private String fileSafeName;

    private transient static String pathDirCardData = "Ycc" + File.separator + "cardData";

    private LinkedList<YgoOpenController.YgoPrivateCard> privateCardList;

    private transient List<String> monster = new ArrayList<>();

    private transient List<String> spell = new ArrayList<>();

    private transient List<String> trap = new ArrayList<>();

    private transient List<String> extra = new ArrayList<>();

    public YgoCard(LinkedHashMap<String, Object> cardData, String fileSafeName)
    {
        this.cardData = cardData;
        this.fileSafeName = fileSafeName;
        this.privateCardList = new LinkedList<>();
    }


    @Deprecated
    public void addQuantity(String q)
    {
            this.cardData.put("CardQuantity", q);
    }
    @Deprecated
    public void addEdition(String edition)
    {
        this.cardData.put("CardEdition", edition);
    }
    @Deprecated
    public void addPrice(String price)
    {
        this.cardData.put("CardPrice", price);
    }

    /**
     * Get the FileSafe Name.
     * @return
     */
    public String getFileSafeName()
    {
        return fileSafeName;
    }

    /**
     * This method serializes the YgoPrivateList. This is for the individual cards.
     * The more inputs you put in the more it will go on.
     */
    public void serializeYgoPrivateList()
    {
        LinkedHashMap<Integer, HashMap<String, String>> ygoPrivateList = new LinkedHashMap<>();

        int i = 0;
        for(YgoOpenController.YgoPrivateCard card : this.privateCardList)
        {
            HashMap<String, String> map = new HashMap<>();

            map.put("Price", card.getPrice());
            map.put("Condition", card.getCardCondition());
            map.put("Quantity", card.getQuantity());
            map.put("Edition", card.getEdition());
            map.put("Location", card.getLocation());

            ygoPrivateList.put(i, map);
            i++;
        }
        this.cardData.put("CardData", ygoPrivateList);
    }

    public void deserialize()
    {
        HashMap<String, List<String>> map;
    }


    /**
     * This method is called when the card is to be saved to JSON files.
     */
    public void serializeToJson()
    {
        File file = new File(pathDirCardData);

        file.mkdirs();

        File card = new File(file, fileSafeName + ".JSON");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String serialized = gson.toJson(cardData);

        try
        {
            FileWriter writer = new FileWriter(card);

            writer.write(serialized);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method provides quick loading of the cards being searched. Depending on the index that gets passed in the
     * parameters.
     * If index = 0 then it would load the file from the local storage
     * if index = 1 then it will attempt to connect to the Server to find card list.
     * @param fileSafeName The File Name.
     * @param id The Index of which card to use.
     * @return returns the YGO card.
     * @throws FileNotFoundException
     */
    public static YgoCard loadCardData(String fileSafeName, int id) throws FileNotFoundException {
        YgoCard card = null;

        File file = new File(YgoCard.pathDirCardData);
        System.out.println(fileSafeName);
        File fCard = new File(file, fileSafeName + ".JSON");

        if(id == 0)
        {
            card = new YgoCard(null, fileSafeName);

            Gson gson = new Gson();

            card.cardData = gson.fromJson(new FileReader(fCard), new TypeToken<LinkedHashMap<String, Object>>(){}.getType());
        }
        else if(id == 1)
        {
            CardList list = new CardList(fileSafeName);
            list.scanLocalCards(fileSafeName, file);
                //card.scanLocalCards(fileSafeName, file);
                return card;
        }


        return card;
    }

    public LinkedHashMap<String, Object> getCardData()
    {
        return cardData;
    }

    /**
     * Please Use {@link CardList#scanLocalCards(String, File)} instead. It will be a new Object to hold that data.
     * @param fileName
     * @param F
     */
    @Deprecated
    public void scanLocalCards(String fileName, File F)
    {
        System.out.println(fileName);
        for(File cardFile : Objects.requireNonNull(F.listFiles()))
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
     * Please use {@link CardList#getPredictiveResults(String, String, File)}
     * @param name The name of the card in question.
     * @param search The Searching card user input.
     * @param file The file of the card. Parameter may not be needed.
     */
    @Deprecated
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

    /**
     * This method is mainly used to convert the YGO card into the private card.
     * @return
     */
    public YgoOpenController.YgoPrivateCard toPrivateCard()
    {
        return YgoOpenController.createPrivateCard(this);
    }
}
