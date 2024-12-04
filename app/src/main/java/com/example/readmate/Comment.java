package com.example.readmate;
public class Comment {
    private String id;
    private String nama;
    private String isi;
    private String profile;
    private int artikelId;


    public Comment(){ }
    public Comment(String isi, String nama, String profile, int artikelId) {
        this.nama = nama;
        this.isi = isi;
        this.profile = profile;
        this.artikelId = artikelId;
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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(int artikelId) {
        this.artikelId = artikelId;
    }
}
