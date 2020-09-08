package com.bingbong.mywoowacoursepulls.service;

import static com.bingbong.mywoowacoursepulls.utils.UriUtils.getGithubPullRequestUri;
import static com.bingbong.mywoowacoursepulls.utils.UriUtils.getGithubRepositoryUri;

import com.bingbong.mywoowacoursepulls.domain.GithubApiFunction;
import com.bingbong.mywoowacoursepulls.dto.GithubPullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.GithubRepositoryResponse;
import com.bingbong.mywoowacoursepulls.exception.InvalidPageLinkSizeException;
import com.bingbong.mywoowacoursepulls.utils.PageParser;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:config/application-prod.properties")
public class GithubApiService {

    private static final Logger log = LoggerFactory.getLogger(GithubApiService.class);

    private static final int DEFAULT_PAGE_COUNT = 1;
    private static final int DEFAULT_LAST_PAGE_COUNT = Integer.MAX_VALUE;
    private static final int INVALID_PAGE_COUNT = -1;

    private final RestTemplate restTemplate;

    @Value("${github.api.oauth-token}")
    private String githubApiOauthToken;

    public GithubApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
            .setConnectTimeout(Duration.ofMillis(100000))
            .setReadTimeout(Duration.ofMillis(100000))
            .build();
    }

    public List<GithubRepositoryResponse> getAllRepositories(String orgName) {
        HttpEntity<String> httpEntity = getHttpEntityWithHeaders();

        return getAllResponses((pageCount) -> restTemplate
            .exchange(getGithubRepositoryUri(orgName, pageCount), HttpMethod.GET, httpEntity,
                new ParameterizedTypeReference<List<GithubRepositoryResponse>>() {
                }));
    }

    public List<GithubPullRequestResponse> getAllPullRequests(String repoName, String state) {
        log.info("repoName : {}'s all pullRequests api called", repoName);

        HttpEntity<String> httpEntity = getHttpEntityWithHeaders();

        return getAllResponses((pageCount) -> restTemplate
            .exchange(getGithubPullRequestUri(repoName, state, pageCount), HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<GithubPullRequestResponse>>() {
                }));
    }

    private <T> List<T> getAllResponses(GithubApiFunction<Integer, T> githubApiFunction) {
        List<T> allResponses = new ArrayList<>();

        int pageCount = DEFAULT_PAGE_COUNT;
        int lastPageCount = DEFAULT_LAST_PAGE_COUNT;

        while (pageCount <= lastPageCount) {
            ResponseEntity<List<T>> responseEntity = githubApiFunction.apply(pageCount);

            if (Objects.isNull(responseEntity.getBody())) {
                break;
            }

            allResponses.addAll(responseEntity.getBody());

            if (isFirstPage(pageCount)) {
                lastPageCount = getLastPage(responseEntity.getHeaders());
            }

            if (isInvalidLastPageCount(lastPageCount)) {
                break;
            }
            pageCount++;
        }
        return allResponses;
    }

    private int getLastPage(HttpHeaders httpHeaders) {
        List<String> link = httpHeaders.get("Link");

        if (Objects.isNull(link) || link.isEmpty()) {
            return INVALID_PAGE_COUNT;
        }
        if (isPageLinkSize(link)) {
            throw new InvalidPageLinkSizeException("PageLink의 사이즈가 유효하지 않습니다!");
        }
        return PageParser.getLastPageIndex(link.get(0));
    }

    private HttpEntity<String> getHttpEntityWithHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, githubApiOauthToken);
        return new HttpEntity<>(httpHeaders);
    }

    private boolean isInvalidLastPageCount(int lastPageCount) {
        return lastPageCount == INVALID_PAGE_COUNT;
    }

    private boolean isFirstPage(int pageCount) {
        return pageCount == DEFAULT_PAGE_COUNT;
    }

    private boolean isPageLinkSize(List<String> link) {
        return link.size() > 1;
    }
}
