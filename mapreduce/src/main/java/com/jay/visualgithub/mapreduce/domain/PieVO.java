package com.jay.visualgithub.mapreduce.domain;

public class PieVO {
    private String language;
    private int frequency;

    public PieVO(String language, int frequency) {
        this.language = language;
        this.frequency = frequency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "PieVO{" +
                "language='" + language + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
