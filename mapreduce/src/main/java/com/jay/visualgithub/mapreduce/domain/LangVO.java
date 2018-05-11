package com.jay.visualgithub.mapreduce.domain;

public class LangVO {
    private String name;
    private String language;

    public LangVO(String name, String language) {
        this.name = name;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "LangVO{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
