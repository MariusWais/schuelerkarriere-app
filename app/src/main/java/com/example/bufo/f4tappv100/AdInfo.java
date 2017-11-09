package com.example.bufo.f4tappv100;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Bufo on 02.06.2017.
 */

public class AdInfo {


    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;


    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("company")
    @Expose
    private String com;

    @SerializedName("sector")
    @Expose
    private String cat;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("city")
    @Expose
    private String place;

    @SerializedName("mail")
    @Expose
    private String mail;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("logo")
    @Expose
    private String imageUrl;

    @SerializedName("imgbanner")
    @Expose
    private String imgbanner;



    public String getId(){return id;}
    public void setId(String id){this.id = id;}

    ///
    public String getUrl() {return url;}

    public void setUrl(String url) {
        this.url = url;
    }

    ///

    public String getCom() {return com;}

    public void setCom(String com) {
        this.com = com;
    }

    ///

    public String getCat() {return cat;}

    public void setCat(String dur) {
        this.cat = cat;
    }

    ///

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    ///


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    ///


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    ///

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getImgbanner() {
        return imgbanner;
    }

    public void setImgbanner(String imgbanner) {
        this.imgbanner = imgbanner;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMail(){return mail;}

    public void setMail(String mail) {this.mail = mail;}
}
