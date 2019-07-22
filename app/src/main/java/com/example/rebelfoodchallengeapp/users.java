package com.example.rebelfoodchallengeapp;


public class users {


    private String id;



    private String name;
    private String username;
    private String email;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private String lat;
    private String lng;
    private int favStatus;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFavStatus() {
        if (favStatus==0)
        {
            return R.drawable.ic_favorite_border_black_24dp;
        }else
        {
            return  R.drawable.ic_favorite_black_24dp;
        }
    }

    public void setFavStatus(int favStatus) {
        this.favStatus = favStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public users(String id,String name, String username, String email, String street, String suite, String city, String zipcode, String lat, String lng,int favStatus) {
        this.id =id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.lat = lat;
        this.lng = lng;
        this.favStatus = favStatus;
    }


}
