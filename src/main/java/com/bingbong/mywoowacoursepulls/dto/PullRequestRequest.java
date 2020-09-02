package com.bingbong.mywoowacoursepulls.dto;

public class PullRequestRequest {

    private String nickname;

    public PullRequestRequest() {
    }

    public PullRequestRequest(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return "PullRequestRequest{" +
            "nickname='" + nickname + '\'' +
            '}';
    }
}
