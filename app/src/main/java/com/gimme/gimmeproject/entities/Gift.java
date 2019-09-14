package com.gimme.gimmeproject.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gift {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Float price;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("url")
    private String url;

    @JsonProperty("id")
    private String id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userName")
    private String userName;

    @JsonProperty("userPhotoUrl")
    private String userPhotoUrl;

    public Gift(@JsonProperty("name") String name,
                @JsonProperty("price") Float price,
                @JsonProperty("currency") String currency,
                @JsonProperty("url") String url,
                @JsonProperty("id") String id,
                @JsonProperty("description") String description,
                @JsonProperty("imageUrl") String imageUrl,
                @JsonProperty("userId") String userId,
                @JsonProperty("userName") String userName,
                @JsonProperty("userPhotoUrl") String userPhotoUrl) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.url = url;
        this.id = id;
        this.description = description;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.userName = userName;
        this.userPhotoUrl = userPhotoUrl;
    }

    /*public Gift(@JsonProperty("name") String name,
                @JsonProperty("price") Float price,
                @JsonProperty("currency") String currency,
                @JsonProperty("url") String url,
                @JsonProperty("description") String description) {
        this.name = name;
        this.price = price;
        this.currency = currency;
        this.url = url;
        this.description = description;
    }*/

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }
}
