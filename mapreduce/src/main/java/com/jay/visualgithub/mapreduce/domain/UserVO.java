package com.jay.visualgithub.mapreduce.domain;

public class UserVO {
    private String avatar;
    private String name;

    public UserVO(String avatar, String name) {
        this.avatar = avatar;
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
