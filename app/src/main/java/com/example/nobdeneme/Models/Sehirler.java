
package com.example.nobdeneme.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sehirler {

    @SerializedName("SehirAd")
    @Expose
    private String sehirAd;
    @SerializedName("SehirSlug")
    @Expose
    private String sehirSlug;

    public String getSehirAd() {
        return sehirAd;
    }

    public void setSehirAd(String sehirAd) {
        this.sehirAd = sehirAd;
    }

    public String getSehirSlug() {
        return sehirSlug;
    }

    public void setSehirSlug(String sehirSlug) {
        this.sehirSlug = sehirSlug;
    }

}
