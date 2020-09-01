package com.bingbong.mywoowacoursepulls.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

public class UriUtils {

    private static final String GITHUB_REPOSITORY_URL = "https://api.github.com/orgs/{orgName}/repos";
    private static final String GITHUB_PULL_REQUEST_URL = "https://api.github.com/repos/woowacourse/{repoName}/pulls";

    public static String getGithubRepositoryUri(String orgName) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("orgName", orgName);

        return UriComponentsBuilder.fromUriString(GITHUB_REPOSITORY_URL)
            .buildAndExpand(urlParams)
            .toUriString();
    }

    public static String getGithubPullRequestUri(String repoName, String state) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("repoName", repoName);

        return UriComponentsBuilder.fromUriString(GITHUB_PULL_REQUEST_URL)
            .queryParam("state", state)
            .buildAndExpand(urlParams)
            .toUriString();
    }
}
