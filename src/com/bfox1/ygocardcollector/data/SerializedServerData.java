package com.bfox1.ygocardcollector.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by bfox1 on 12/23/2016.
 */
public class SerializedServerData implements Serializable
{
    private final Object[] objects;


    public SerializedServerData(Object[] objects)
    {
        if(objects.length > 4)
        {
            throw new IllegalArgumentException("Sorry but this cannot exist!");
        }

        this.objects = objects;
    }


    public byte[] getMonsterList()
    {
        return (byte[]) objects[0];
    }

    public byte[] getExtraMonsterList()
    {
        return (byte[]) objects[1];
    }

    public byte[] getTrapCardList()
    {
        return (byte[]) objects[2];
    }

    public byte[] getSpellCardList()
    {
        return (byte[]) objects[3];
    }


    public static byte[] serializeData(Object obj)
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
}
