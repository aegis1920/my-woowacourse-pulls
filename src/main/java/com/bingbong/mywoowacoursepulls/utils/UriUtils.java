package com.bingbong.mywoowacoursepulls.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.UriComponentsBuilder;

public class UriUtils {

    private static final String GITHUB_ORG_REPOSITORY_URL = "https://api.github.com/orgs/{orgName}/repos";

    public static String getGithubRepositoryUri(String orgName) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("orgName", orgName);

        return UriComponentsBuilder.fromUriString(GITHUB_ORG_REPOSITORY_URL)
            .buildAndExpand(urlParams)
            .toUriString();
    }
}
