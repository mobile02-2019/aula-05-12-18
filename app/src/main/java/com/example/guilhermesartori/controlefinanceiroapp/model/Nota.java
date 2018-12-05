package com.example.guilhermesartori.controlefinanceiroapp.model;

public class Nota {
    private String title;
    private String content;

    public Nota(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Nota() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
