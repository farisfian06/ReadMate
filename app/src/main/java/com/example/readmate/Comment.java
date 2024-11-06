package com.example.readmate;

public class Comment {
    private String nama;
    private String isi;
    int profile;

    public Comment(String isi, String nama, int profile) {
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

    public int getProfile() {
        return profile;
    }
}
