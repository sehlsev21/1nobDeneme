
package com.example.nobdeneme.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ilceler {

    @SerializedName("ilceAd")
    @Expose
    private String ilceAd;
    @SerializedName("ilceSlug")
    @Expose
    private String ilceSlug;

    public String getIlceAd() {
        return ilceAd;
    }

    public void setIlceAd(String ilceAd) {
        this.ilceAd = ilceAd;
    }

    public String getIlceSlug() {
        return ilceSlug;
    }

    public void setIlceSlug(String ilceSlug) {
        this.ilceSlug = ilceSlug;
    }

}
