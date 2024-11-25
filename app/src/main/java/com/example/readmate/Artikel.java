package com.example.readmate;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artikel_table")
public class Artikel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String judul;
    private String tanggal;
    private String topik;
    private int thumbnail; // Simpan sebagai resource ID

    public Artikel(String judul, String tanggal, String topik, int thumbnail) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.topik = topik;
        this.thumbnail = thumbnail;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public String getTopik() {
        return topik;
    }
    public void setTopik(String topik) {
        this.topik = topik;
    }
    public int getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
