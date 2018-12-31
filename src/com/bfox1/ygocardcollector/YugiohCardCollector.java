package com.bfox1.ygocardcollector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sample.Controller;
import com.bfox1.ygocardcollector.jframe.uithread.YgoccCardFiller;
import com.bfox1.ygocardcollector.jframe.uithread.YgoccJFrame;
import com.bfox1.ygocardcollector.data.*;

import javax.swing.*;
import javax.swing.text.html.ListView;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.UUID;

/**
 * Created by bfox1 on 6/24/15.
 * In God we Trust.
 */
public class YugiohCardCollector
{

    private ObjectInputStream fromServer = null;
    private ObjectOutputStream toServer = null;
    private final Controller controller;
    private static final String pathToUUID = "Ycc" + File.separator + "clearance";
    private final UUID clientID;
    Socket serverSocket = null;

    public YugiohCardCollector(Controller controller)
    {
        this.controller = controller;
        new File(pathToUUID).mkdirs();

        File clearanceUUID = new File(pathToUUID, "ClientUUID.txt");

        if(clearanceUUID.exists())
        {
            this.clientID = readClearanceUUID(clearanceUUID);
        }
        else
        {
            this.clientID = UUID.randomUUID();
            this.writeClientUUID(clearanceUUID);
        }
    }

    /**
     * Upon Registering Client UUID, it will get written in to the File.
     * @param file
     */
    private void writeClientUUID(File file)
    {
        try
        {
            FileWriter writer = new FileWriter(file);
            BufferedWriter w = new BufferedWriter(writer);

            w.write(this.clientID.toString());
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    /**
     * Basic searching action to interact with the Server. All Queries MUST go through this first.
     * @param scounter The Card Scouter Object to look for information
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SocketTimeoutException
     */
    public void beginSearchQuery(CardScouter scounter) throws IOException, ClassNotFoundException, SocketTimeoutException
    {

        this.serverSocket = new Socket();
        this.serverSocket.connect(new InetSocketAddress("68.105.118.194", 8889), 2000);

        //this.serverSocket = new Socket("192.168.0.2", 8889);

        this.toServer = new ObjectOutputStream(serverSocket.getOutputStream());
        this.fromServer = new ObjectInputStream(serverSocket.getInputStream());
        SerializedYgoCardData data = new SerializedYgoCardData(scounter.getName(), this.clientID, this.parseType(scounter.getType()));

        this.toServer.writeObject(data);


        this.readServerObject(fromServer.readObject());


    }

    /**
     * Tells the server what kind of command to execute and return.
     * 0 = Monster, 1 = Extra, 2 = Spell, 3 = Trap, -1 = All, -2 = Pop(Pulling card info)
     * @param type type of card to pull
     * @return an integer
     */
    private int parseType(String type)
    {
        if(type.equalsIgnoreCase("monster")) return 0;
        else if(type.equalsIgnoreCase("extra")) return 1;
        else if(type.equalsIgnoreCase("trap")) return 2;
        else if(type.equalsIgnoreCase("spell")) return 3;
        else if(type.equalsIgnoreCase("all")) return -1;
        else if(type.equalsIgnoreCase("pop")) return -2;

        return -1;
    }

    /**
     * Self Explainitory Method for reading the UUID File for the Client.
     * @param file The UUID File
     * @return the UUID.
     */
    private UUID readClearanceUUID(File file)
    {
        BufferedReader read = null;

        try
        {
            FileReader reader = new FileReader(file);
            read = new BufferedReader(reader);

            return UUID.fromString(read.readLine());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("UUID failed to be read.... this is a technical issue. ");
        System.out.println("Please Resolve this issue with Server admins.");
        throw new IllegalArgumentException("Stack trace here");
    }


    /**
     * Getting Server Information. 3 different connections can be received.
     * A Server Message to display to Client
     * An Object containing the different List of Cards for each category.
     * An Object containing the card information of the selected card.
     * @param object
     * @throws IOException
     */
    public void readServerObject(Object object) throws IOException
    {
        ServerToClientDecrypter decrypter = new ServerToClientDecrypter();

        /**
         * This Checks if the incoming object is a {@link SerializedServerData} if so,
         * This Object currently contains card list information to display to the Screen.
         * This works in tandem with {@link sample.YgoOpenController} to display the information on the screen.
         */
        if(object instanceof SerializedServerData)
        {
            Object[] objects = decrypter.getClientObj((SerializedServerData) object);

            int i = 0;
            clearListItems();
            for(Object obj : objects)
            {

                if(obj instanceof List) // Receiving Card Names for Listing.
                {

                    List<String> list = (List<String>)obj;
                    addCards(list, i);

                    i++;


                }
            }
        }
        /*
         * This is for displaying any information the Client should receive. Such as warning messages or any type of dialog.
         */
        else if(object instanceof ServerMessage) //Some Server Message
        {
            ServerMessage message = (ServerMessage) object;

            System.out.println(message.getMessage());
            //TODO: Display Pop-up message to let Client know.
        }
        /*
         * This check for {@link SerializedJsonData} which gets card data if found.
         */
        else if(object instanceof SerializedJsonData) //Receiving card Data.
        {

            SerializedJsonData data = (SerializedJsonData) object;

            controller.displayData(data.getDataMap());

        }

        this.serverSocket.close();
    }

    /**
     * This method is intended to look to see if the card exists within Local database, if not, it will return false.
     * @param fullCardName
     * @return
     */
    public boolean loadClientData(String fullCardName)
    {

        YgoCard card = this.getCard(fullCardName);

        if(card != null)
        {
            controller.displayData(card.getCardData());
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * This adds cards to the Lists. 0 = Monsters, 1 = Extra Deck, 2 = Spells, 3 = Traps
     * @param list
     * @param i
     */
    public void addCards(List<String> list, int i)
    {
        for(String s : list)
        {
            Text card = new Text(s);
            card.setFill(Color.BLACK);

            switch (i)
            {
                case 0 : this.controller.getMonsterList().getItems().add(card); break;
                case 1 : this.controller.getExtraMonsterList().getItems().add(card); break;
                case 2 : this.controller.getSpellList().getItems().add(card); break;
                case 3 : this.controller.getTrapList().getItems().add(card); break;
                default: throw new IllegalArgumentException("For Some reason, i couldnt match a category!");
            }

        }
    }

    /**
     * Simple Method to be ran everytime a new list item is clicked on.
     */
    private void clearListItems()
    {
        this.controller.getMonsterList().getItems().clear();
        this.controller.getExtraMonsterList().getItems().clear();
        this.controller.getSpellList().getItems().clear();
        this.controller.getTrapList().getItems().clear();
    }


    /**
     * This method is used to load the card data Locally.
     * @param name The File Safe Name of the Card Excluding the .JSON extension
     * @return the YgoCard Object if found, otherwise, Null.
     */
    private YgoCard getCard(String name)
    {
        File file = new File("Ycc" + File.separator + "cardData");

        file.mkdirs();

        File card = new File(file, name + ".JSON");

        if(card.exists())
        {
            try
            {
                return YgoCard.loadCardData(name,0);
            } catch (FileNotFoundException e) {

            }
        }

        return null;
    }


}
