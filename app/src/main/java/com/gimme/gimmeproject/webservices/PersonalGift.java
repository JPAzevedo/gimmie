package com.gimme.gimmeproject.webservices;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimme.gimmeproject.Configuration;
import com.gimme.gimmeproject.entities.Gift;

import java.io.IOException;
import java.util.List;

public class PersonalGift extends HTTPRequest{
    private static PersonalGift instance;

    private static String GET_GIFTS = Configuration.APP_LOCAL_URL + "/getPersonalGiftList";

    private PersonalGift() {
        super();
    }

    public static PersonalGift getInstance() {
        if(instance == null) {
            synchronized (AppLogin.class) {
                if (instance == null) {
                    instance = new PersonalGift();
                }
            }
        }
        return instance;
    }

    public String post(String url, String json) throws IOException {
        return super.post(url,json);
    }

    public List<Gift> getGifts(String userId){
        try {
            String json = get(GET_GIFTS);
            ObjectMapper mapper = new ObjectMapper();
            List<Gift> gifts = mapper.readValue(json, new TypeReference<List<Gift>>(){});
            return gifts;
        } catch (IOException e) {
            Log.e("Get Gifts Error",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
