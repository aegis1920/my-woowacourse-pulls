package com.bingbong.mywoowacoursepulls.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubRepositoryResponse {

    private Long id;
    private String name;

    @JsonProperty("html_url")
    private String htmlUrl;

    public GithubRepositoryResponse(Long id, String name, String htmlUrl) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
    }

    public static GithubRepositoryResponse of(Long id, String name, String htmlUrl) {
        return new GithubRepositoryResponse(id, name, htmlUrl);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public String toString() {
        return "RepositoryResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", html_url='" + htmlUrl + '\'' +
            '}';
    }
}
