
package com.example.nobdeneme.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Eczaneler {

    @SerializedName("EczaneAdi")
    @Expose
    private String eczaneAdi;
    @SerializedName("Adresi")
    @Expose
    private String adresi;
    @SerializedName("Semt")
    @Expose
    private String semt;
    @SerializedName("YolTarifi")
    @Expose
    private String yolTarifi;
    @SerializedName("Telefon")
    @Expose
    private String telefon;
    @SerializedName("Telefon2")
    @Expose
    private String telefon2;
    @SerializedName("Sehir")
    @Expose
    private String sehir;
    @SerializedName("ilce")
    @Expose
    private String ilce;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;

    public String getEczaneAdi() {
        return eczaneAdi;
    }

    public void setEczaneAdi(String eczaneAdi) {
        this.eczaneAdi = eczaneAdi;
    }

    public String getAdresi() {
        return adresi;
    }

    public void setAdresi(String adresi) {
        this.adresi = adresi;
    }

    public String getSemt() {
        return semt;
    }

    public void setSemt(String semt) {
        this.semt = semt;
    }

    public String getYolTarifi() {
        return yolTarifi;
    }

    public void setYolTarifi(String yolTarifi) {
        this.yolTarifi = yolTarifi;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getTelefon2() {
        return telefon2;
    }

    public void setTelefon2(String telefon2) {
        this.telefon2 = telefon2;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

}
