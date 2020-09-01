package com.bingbong.mywoowacoursepulls.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.bingbong.mywoowacoursepulls.dto.GithubPullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.GithubRepositoryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

@RestClientTest(GithubApiService.class)
public class GithubApiServiceTest {

    public static final String TEST_REPO_NAME = "woowa-writing-2";
    public static final String TEST_REPO_URL = "https://github.com/woowacourse/woowa-writing-2";
    public static final String TEST_ORG_NAME = "woowacourse";
    public static final String TEST_PULL_REQUEST_TITLE = "[빙봉] 레벨 3 - 회고 글쓰기 미션 제출합니다.";
    public static final String TEST_PULL_REQUEST_URL = "https://github.com/woowacourse/woowa-writing-2";
    public static final String TEST_PULL_REQUEST_DEFAULT_STATE = "all";
    public static final String TEST_PULL_REQUEST_STATE = "open";
    public static final String TEST_PULL_REQUEST_CREATED_AT = "2020-09-01T00:00:00Z";
    public static final String TEST_PULL_REQUEST_UPDATED_AT = "2020-09-01T00:00:00Z";

    private final String MOCK_REPOSITORY_URL = "https://api.github.com/orgs/woowacourse/repos";
    private final String MOCK_PULL_REQUEST_URL = "https://api.github.com/repos/woowacourse/woowa-writing-2/pulls?state=all";

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private GithubApiService githubApiService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;


    @DisplayName("orgName에 해당하는 모든 Repository 조회")
    @Test
    void getAllRepositories_SuccessToGet() throws JsonProcessingException {
        // given
        List<GithubRepositoryResponse> githubRepositoryRespons = Arrays.asList(
            GithubRepositoryResponse.of(1L, TEST_REPO_NAME, TEST_REPO_URL),
            GithubRepositoryResponse.of(2L, TEST_REPO_NAME, TEST_REPO_URL)
        );
        mockRestServiceServer.expect(requestTo(MOCK_REPOSITORY_URL))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(githubRepositoryRespons))
            );

        // when
        List<GithubRepositoryResponse> expectGithubRepositoryResponses = githubApiService
            .getAllRepositories(TEST_ORG_NAME);

        // then
        assertThat(expectGithubRepositoryResponses).isNotNull();
        assertThat(expectGithubRepositoryResponses).hasSize(2);

        GithubRepositoryResponse expectGithubRepositoryResponse = expectGithubRepositoryResponses
            .get(0);

        assertThat(expectGithubRepositoryResponse.getId()).isEqualTo(1L);
        assertThat(expectGithubRepositoryResponse.getName()).isEqualTo(TEST_REPO_NAME);
        assertThat(expectGithubRepositoryResponse.getHtmlUrl()).isEqualTo(TEST_REPO_URL);
    }

    @DisplayName("repoName에 해당하는 모든 PullRequest 조회")
    @Test
    void getAllPullRequests_SuccessToGet() throws JsonProcessingException {
        // given
        List<ExpectGithubPullRequestResponse> expectGithubPullRequestResponses = Arrays.asList(
            new ExpectGithubPullRequestResponse(1L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_URL,
                TEST_PULL_REQUEST_STATE,
                TEST_PULL_REQUEST_CREATED_AT, TEST_PULL_REQUEST_UPDATED_AT),
            new ExpectGithubPullRequestResponse(2L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_URL,
                TEST_PULL_REQUEST_STATE,
                TEST_PULL_REQUEST_CREATED_AT, TEST_PULL_REQUEST_UPDATED_AT));
        mockRestServiceServer.expect(requestTo(MOCK_PULL_REQUEST_URL))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(expectGithubPullRequestResponses))
            );

        // when
        List<GithubPullRequestResponse> responses = githubApiService
            .getAllPullRequests(TEST_REPO_NAME, TEST_PULL_REQUEST_DEFAULT_STATE);

        // then
        assertThat(responses).isNotNull();
        assertThat(responses).hasSize(2);

        GithubPullRequestResponse response = responses.get(0);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getTitle()).isEqualTo(TEST_PULL_REQUEST_TITLE);
        assertThat(response.getHtmlUrl()).isEqualTo(TEST_PULL_REQUEST_URL);
        assertThat(response.getState()).isEqualTo(TEST_PULL_REQUEST_STATE);
        assertThat(response.getCreatedAt()).isNotNull();
        assertThat(response.getUpdatedAt()).isNotNull();
    }

    static class ExpectGithubPullRequestResponse {

        private Long id;
        private String title;
        private String html_url;
        private String state;
        private String created_at;
        private String updated_at;

        ExpectGithubPullRequestResponse() {
        }

        public ExpectGithubPullRequestResponse(Long id, String title, String html_url,
            String state, String created_at, String updated_at) {
            this.id = id;
            this.title = title;
            this.html_url = html_url;
            this.state = state;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getHtml_url() {
            return html_url;
        }

        public String getState() {
            return state;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }

}
