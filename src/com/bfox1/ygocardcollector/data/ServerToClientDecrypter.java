package com.bfox1.ygocardcollector.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by bfox1 on 12/23/2016.
 */
public class ServerToClientDecrypter
{
    public Object[] getClientObj(SerializedServerData data)
    {
        Object[] objs = checkNotNull(data);
        return objs;
    }


    private Object deserialized(byte[] array)
    {
        ByteArrayInputStream b = new ByteArrayInputStream(array);
        try {
            ObjectInputStream o = new ObjectInputStream(b);
            return o.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object[] checkNotNull(SerializedServerData data)
    {
        Object[] objects = new Object[4];
        int i = 0;

        try
        {
            while(objects[i] == null)
            {
                try
                {
                    objects[i] = i == 0 ? data.getMonsterList() : (i == 1 ? data.getExtraMonsterList() : i == 2 ? data.getTrapCardList() : i == 3 ? data.getSpellCardList() : null);
                    objects[i] = deserialized((byte[])objects[i]);

                } catch (Exception e)
                {
                    //System.out.println("List of data = null, skipping");
                }
                finally {
                    i++;
                }

            }
        } catch (ArrayIndexOutOfBoundsException e)
        {

        }
        return objects;

    }
}
