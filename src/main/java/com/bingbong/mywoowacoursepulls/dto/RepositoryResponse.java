package com.bingbong.mywoowacoursepulls.dto;

public class RepositoryResponse {

    private Long id;
    private String name;
    private String htmlUrl;

    public RepositoryResponse(Long id, String name, String htmlUrl) {
        this.id = id;
        this.name = name;
        this.htmlUrl = htmlUrl;
    }

    public static RepositoryResponse of(Long id, String name, String htmlLink) {
        return new RepositoryResponse(id, name, htmlLink);
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
