package com.jay.visualgithub.search.domain;

import java.util.Arrays;

public class RepoVO {
    private String githubId;
    private Object[] repoData;

    public RepoVO(String githubId) {
        this.githubId = githubId;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public Object[] getRepoData() {
        return repoData;
    }

    public void setRepoData(Object[] repoData) {
        this.repoData = repoData;
    }

    @Override
    public String toString() {
        return "RepoVO{" +
                "githubId='" + githubId + '\'' +
                ", repoData=" + Arrays.toString(repoData) +
                '}';
    }
}
