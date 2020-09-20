package com.bingbong.mywoowacoursepulls.dto;

import java.time.ZonedDateTime;

public class PullRequestResponse {

    private Long id;
    private String title;
    private String state;
    private String htmlUrl;
    private ZonedDateTime pullRequestCreatedAt;
    private ZonedDateTime pullRequestUpdatedAt;

    public PullRequestResponse() {
    }

    public PullRequestResponse(Long id, String title, String state, String htmlUrl,
        ZonedDateTime pullRequestCreatedAt, ZonedDateTime pullRequestUpdatedAt) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.htmlUrl = htmlUrl;
        this.pullRequestCreatedAt = pullRequestCreatedAt;
        this.pullRequestUpdatedAt = pullRequestUpdatedAt;
    }

    public static PullRequestResponse of(Long id, String title, String state, String htmlUrl,
        ZonedDateTime pullRequestCreatedAt, ZonedDateTime pullRequestUpdatedAt) {
        return new PullRequestResponse(id, title, state, htmlUrl, pullRequestCreatedAt,
            pullRequestUpdatedAt);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {
        return state;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public ZonedDateTime getPullRequestCreatedAt() {
        return pullRequestCreatedAt;
    }

    public ZonedDateTime getPullRequestUpdatedAt() {
        return pullRequestUpdatedAt;
    }

    @Override
    public String toString() {
        return "PullRequestResponse{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", state='" + state + '\'' +
            ", htmlUrl='" + htmlUrl + '\'' +
            ", pullRequestCreatedAt=" + pullRequestCreatedAt +
            ", pullRequestUpdatedAt=" + pullRequestUpdatedAt +
            '}';
    }
}
