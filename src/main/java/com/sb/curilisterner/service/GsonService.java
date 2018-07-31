package com.sb.curilisterner.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sb.curilisterner.domain.resources.Counter;
import com.sb.curilisterner.hybernatefactory.HibernateProxyTypeAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GsonService {

    @Autowired
    Gson gson;

    public String returnGson(Object o) {
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        gson = gb.create();
        String result = gson.toJson(o);
        return result;
    }
}
