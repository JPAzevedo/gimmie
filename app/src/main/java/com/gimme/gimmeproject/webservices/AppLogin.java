package com.gimme.gimmeproject.webservices;

import java.io.IOException;

public class AppLogin extends HTTPRequest{

    private static AppLogin appLoginInstance;

    private AppLogin() {
        super();
    }

    public static AppLogin getInstance() {
        if(appLoginInstance == null) {
            synchronized (AppLogin.class) {
                if (appLoginInstance == null) {
                    appLoginInstance = new AppLogin();
                }
            }
        }
        return appLoginInstance;
    }

    public String post(String url, String json) throws IOException {
        return super.post(url,json);
    }

}
