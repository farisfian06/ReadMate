package com.example.readmate;

public class Comment {
    private String nama;
    private String isi;
    String profile;
String a;
String b;
String c;

    public Comment(String isi, String nama, String profile) {
        this.nama = nama;
        this.isi = isi;
        this.profile = profile;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getProfile() {
        return profile;
    }
}
