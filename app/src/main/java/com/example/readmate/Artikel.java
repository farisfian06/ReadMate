package com.example.readmate;

public class Artikel {
    private String judul;
    private String tanggal;
    private String topik;
    int thumbnail;

    public Artikel(String judul, String tanggal, String topik, int thumbnail) {
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
}
