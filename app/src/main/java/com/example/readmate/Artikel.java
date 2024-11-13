package com.example.readmate;

public class Artikel {
    public String judul;
    public String tanggal;
    public String topik;
    public String thumbnail;

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
        return thumbnail;}
    public void setThumbnail(String thumbnail) {
        this.thumbnail= thumbnail;}
}