package com.bfox1.ygocardcollector.data;

import java.io.Serializable;

/**
 * Created by bfox1 on 12/26/2016.
 */
public class ServerMessage implements Serializable
{
    private final String message;

    public ServerMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

}
