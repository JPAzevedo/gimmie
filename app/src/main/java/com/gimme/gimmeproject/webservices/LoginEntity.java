package com.gimme.gimmeproject.webservices;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginEntity {

    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("user_id")
    private String user_id;
    @JsonProperty("app_id")
    private String app_id;

    public LoginEntity(@JsonProperty("access_token") String access_token,@JsonProperty("user_id") String user_id,@JsonProperty("app_id") String app_id) {
        this.access_token = access_token;
        this.user_id = user_id;
        this.app_id = app_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getApp_id() {
        return app_id;
    }
}
