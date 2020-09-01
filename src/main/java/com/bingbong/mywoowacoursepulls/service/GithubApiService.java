package com.bingbong.mywoowacoursepulls.service;

import com.bingbong.mywoowacoursepulls.dto.RepositoryResponse;
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

    private final RestTemplate restTemplate;
    private final String REPOSITORY_URL = "https://api.github.com/orgs/woowacourse/repos?per_page=100";

    public GithubApiService(RestTemplateBuilder restTemplateBuilder) {
        // TODO: 2020/09/01 rootUri 설정
        this.restTemplate = restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(5000))
            .setReadTimeout(Duration.ofMillis(5000))
            .build();
    }

    public ResponseEntity<List<RepositoryResponse>> getAllRepositories(String orgName) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> httpEntity = new HttpEntity<>(new HttpHeaders());

        return restTemplate.exchange(REPOSITORY_URL, HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<List<RepositoryResponse>>() {
            });
    }
}
