package com.bingbong.mywoowacoursepulls.service;

import static com.bingbong.mywoowacoursepulls.utils.UriUtils.getGithubRepositoryUri;

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

    public GithubApiService(RestTemplateBuilder restTemplateBuilder) {
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

        return restTemplate.exchange(getGithubRepositoryUri(orgName), HttpMethod.GET, httpEntity,
            new ParameterizedTypeReference<List<RepositoryResponse>>() {
            });
    }
}
