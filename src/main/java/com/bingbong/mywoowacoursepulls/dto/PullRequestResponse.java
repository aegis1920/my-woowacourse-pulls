package com.bingbong.mywoowacoursepulls.dto;

public class PullRequestResponse {

    private Long id;
    private String nickname;
    private String repoName;
    private String state;
    private String htmlLink;

    public PullRequestResponse(Long id, String nickname, String repoName, String state,
        String htmlLink) {
        this.id = id;
        this.nickname = nickname;
        this.repoName = repoName;
        this.state = state;
        this.htmlLink = htmlLink;
    }

    public static PullRequestResponse of(Long id, String nickname, String repoName, String state,
        String htmlLink) {
        return new PullRequestResponse(id, nickname, repoName, state, htmlLink);
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getState() {
        return state;
    }

    public String getHtmlLink() {
        return htmlLink;
    }

    @Override
    public String toString() {
        return "PullRequestResponse{" +
            "id=" + id +
            ", nickname='" + nickname + '\'' +
            ", repoName='" + repoName + '\'' +
            ", state='" + state + '\'' +
            ", htmlLink='" + htmlLink + '\'' +
            '}';
    }
}
