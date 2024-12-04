package com.example.readmate;

public class Bookmark {
    private Artikel artikel;
    private String id;
    public Bookmark(){}
    public Bookmark(Artikel artikel) {
        this.artikel = artikel;
    }
    public Bookmark(String id, Artikel artikel) {
        this.id = id;
        this.artikel = artikel;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
