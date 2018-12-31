package com.bfox1.ygocardcollector.data;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by bfox1 on 12/26/2016.
 */
public class SerializedJsonData implements Serializable
{
    private final LinkedHashMap<String, Object> dataMap;

    static final long serialVersionUID = 7928980448693010399L;

    public SerializedJsonData(LinkedHashMap<String, Object> dataMap)
    {
        this.dataMap = dataMap;
    }

    public LinkedHashMap<String, Object> getDataMap() {
        return dataMap;
    }
}
