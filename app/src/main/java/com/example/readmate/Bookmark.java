package com.example.readmate;

public class Bookmark {
    private String judul;
    private String waktu;
    int gambarArtikel;

    public Bookmark(String judul, String waktu, int gambarArtikel) {
        this.judul = judul;
        this.waktu = waktu;
        this.gambarArtikel = gambarArtikel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getGambarArtikel() {
        return gambarArtikel;
    }

    public void setGambarArtikel(int gambarArtikel) {
        this.gambarArtikel = gambarArtikel;
    }

}
