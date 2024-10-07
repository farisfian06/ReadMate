package com.example.readmate;

public class Comment {
    private String nama;
    private String isi;

    public Comment(String isi, String nama) {
        this.isi = isi;
        this.nama = nama;
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
}
