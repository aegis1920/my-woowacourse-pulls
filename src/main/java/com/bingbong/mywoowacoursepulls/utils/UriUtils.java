package com.bingbong.mywoowacoursepulls.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

public class UriUtils {

    private static final String GITHUB_REPOSITORY_URL = "https://api.github.com/orgs/{orgName}/repos";
    private static final String GITHUB_PULL_REQUEST_URL = "https://api.github.com/repos/woowacourse/{repoName}/pulls";
    private static final int MAX_PER_PAGE = 100;

    public static String getGithubRepositoryUri(String orgName, int pageCount) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("orgName", orgName);

        return UriComponentsBuilder.fromUriString(GITHUB_REPOSITORY_URL)
            .queryParam("per_page", MAX_PER_PAGE)
            .queryParam("page", pageCount)
            .buildAndExpand(urlParams)
            .toUriString();
    }

    public static String getGithubPullRequestUri(String repoName, String state, int pageCount) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("repoName", repoName);

        return UriComponentsBuilder.fromUriString(GITHUB_PULL_REQUEST_URL)
            .queryParam("state", state)
            .queryParam("per_page", MAX_PER_PAGE)
            .queryParam("page", pageCount)
            .buildAndExpand(urlParams)
            .toUriString();
    }
}
