package com.bfox1.ygocardcollector.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by bfox1 on 12/22/2016.
 */
public class SerializedYgoCardData implements Serializable
{
    static final long serialVersionUID = 7928980448693010399L;
    private byte[] searchingName;

    private byte[] clientID;

    private boolean verified;

    private int cardIndex;
    public SerializedYgoCardData(String searched, UUID clientID, int cardIndex)
    {
        this.searchingName = serializeData(searched);
        this.clientID = serializeData(clientID);
        this.cardIndex = cardIndex;
        this.verified = true;
    }


    public byte[] getSearchingName()
    {
        return searchingName;
    }

    public byte[] getClientID()
    {
        return clientID;
    }

    public boolean isVerified()
    {
        return verified;
    }

    public int getCardIndex() { return cardIndex;}

    private byte[] serializeData(Object obj)
    {
        ByteArrayOutputStream b = new ByteArrayOutputStream();

        ObjectOutputStream o = null;
        try
        {
            o = new ObjectOutputStream(b);
            o.writeObject(obj);
            return b.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void hello()
    {

    }
}
