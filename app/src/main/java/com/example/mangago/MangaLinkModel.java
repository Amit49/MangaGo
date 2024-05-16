package com.example.mangago;

public class MangaLinkModel {
    String name;
    String link;

    public MangaLinkModel(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }
}
