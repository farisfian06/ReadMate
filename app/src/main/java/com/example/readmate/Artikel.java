package com.example.readmate;

import java.io.Serializable;

public class Artikel implements Serializable {
    private String judul;
    private String tanggal;
    private String topik;
    private String isi;
    String thumbnail;

    public Artikel (){}
    public Artikel(String judul, String tanggal, String topik, String thumbnail) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.topik = topik;
        this.thumbnail = thumbnail;
    }

    public String getJudul() {
        return judul;}
    public void setJudul(String judul) {
        this.judul = judul;}

    public String getTanggal() {
        return tanggal;}
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;}

    public String getTopik() {
        return topik;}
    public void setTopik(String topik) {
        this.topik= topik;}

    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

}
