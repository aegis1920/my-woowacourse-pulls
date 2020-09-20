package com.bingbong.mywoowacoursepulls.domain;

import com.bingbong.mywoowacoursepulls.dto.GithubPullRequestResponse;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PullRequest extends BaseEntity {

    @Column(nullable = false)
    private Long pullRequestId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String htmlUrl;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private ZonedDateTime pullRequestCreatedAt;

    @Column(nullable = false)
    private ZonedDateTime pullRequestUpdatedAt;

    protected PullRequest() {
    }

    public PullRequest(Long pullRequestId, String title, String htmlUrl, String state,
        ZonedDateTime pullRequestCreatedAt, ZonedDateTime pullRequestUpdatedAt) {
        this.pullRequestId = pullRequestId;
        this.title = title;
        this.htmlUrl = htmlUrl;
        this.state = state;
        this.pullRequestCreatedAt = pullRequestCreatedAt;
        this.pullRequestUpdatedAt = pullRequestUpdatedAt;
    }

    public static PullRequest of(GithubPullRequestResponse response) {
        return new PullRequest(
            response.getId(),
            response.getTitle(),
            response.getHtmlUrl(),
            response.getState(),
            response.getCreatedAt(),
            response.getUpdatedAt()
        );
    }

    public Long getPullRequestId() {
        return pullRequestId;
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

    public ZonedDateTime getPullRequestCreatedAt() {
        return pullRequestCreatedAt;
    }

    public ZonedDateTime getPullRequestUpdatedAt() {
        return pullRequestUpdatedAt;
    }

    @Override
    public String toString() {
        return "PullRequest{" +
            "pullRequestId=" + pullRequestId +
            ", title='" + title + '\'' +
            ", htmlUrl='" + htmlUrl + '\'' +
            ", state='" + state + '\'' +
            ", pullRequestCreatedAt=" + pullRequestCreatedAt +
            ", pullRequestUpdatedAt=" + pullRequestUpdatedAt +
            '}';
    }
}
