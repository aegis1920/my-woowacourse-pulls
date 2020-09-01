package com.bingbong.mywoowacoursepulls.service;

import static com.bingbong.mywoowacoursepulls.utils.UriUtils.getGithubPullRequestUri;
import static com.bingbong.mywoowacoursepulls.utils.UriUtils.getGithubRepositoryUri;

import com.bingbong.mywoowacoursepulls.dto.GithubPullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.GithubRepositoryResponse;
import java.time.Duration;
import java.util.List;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubApiService {

    public static final String OAUTH_TOKEN = "token MOCK";
    private final RestTemplate restTemplate;

    public GithubApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(5000))
            .setReadTimeout(Duration.ofMillis(5000))
            .build();
    }

    public List<GithubRepositoryResponse> getAllRepositories(String orgName) {
        HttpEntity<String> httpEntity = getHttpEntity();

        ResponseEntity<List<GithubRepositoryResponse>> responseEntity = restTemplate
            .exchange(getGithubRepositoryUri(orgName), HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<List<GithubRepositoryResponse>>() {
                });

        return responseEntity.getBody();
    }

    public List<GithubPullRequestResponse> getAllPullRequests(String repoName, String state) {
        HttpEntity<String> httpEntity = getHttpEntity();

        ResponseEntity<List<GithubPullRequestResponse>> responseEntity = restTemplate
            .exchange(getGithubPullRequestUri(repoName, state), HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<List<GithubPullRequestResponse>>() {
                });

        return responseEntity.getBody();
    }

    private HttpEntity<String> getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, OAUTH_TOKEN);
        return new HttpEntity<>(httpHeaders);
    }
}
