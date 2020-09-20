package com.bingbong.mywoowacoursepulls.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

public class GithubPullRequestResponse {

    private Long id;
    private String title;

    @JsonProperty("html_url")
    private String htmlUrl;

    private String state;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Seoul")
    @JsonProperty("created_at")
    private ZonedDateTime createdAt;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "Asia/Seoul")
    @JsonProperty("updated_at")
    private ZonedDateTime updatedAt;

    public GithubPullRequestResponse() {
    }

    public GithubPullRequestResponse(Long id, String title, String htmlUrl, String state,
        ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.htmlUrl = htmlUrl;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static GithubPullRequestResponse of(Long id, String title, String htmlUrl, String state,
        ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        return new GithubPullRequestResponse(id, title, htmlUrl, state, createdAt, updatedAt);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getState() {
        return state;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "GithubPullRequestResponse{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", htmlUrl='" + htmlUrl + '\'' +
            ", state='" + state + '\'' +
            ", createdAt=" + createdAt +
            ", updatedAt=" + updatedAt +
            '}';
    }
}
