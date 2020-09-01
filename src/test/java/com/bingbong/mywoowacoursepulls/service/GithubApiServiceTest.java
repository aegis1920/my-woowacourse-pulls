package com.bingbong.mywoowacoursepulls.service;

import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.EXPECT_GITHUB_PULL_REQUEST_RESPONSES;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.GITHUB_REPOSITORY_RESPONSES;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.REAL_PULL_REQUEST_URL;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.REAL_REPOSITORY_URL;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_ORG_NAME;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_PULL_REQUEST_DEFAULT_STATE;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_PULL_REQUEST_STATE;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_PULL_REQUEST_TITLE;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_PULL_REQUEST_URL;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_REPO_NAME;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_REPO_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.bingbong.mywoowacoursepulls.dto.GithubPullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.GithubRepositoryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private GithubApiService githubApiService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;


    @DisplayName("orgName에 해당하는 모든 Repository 조회")
    @Test
    void getAllRepositories_SuccessToGet() throws JsonProcessingException {
        // given
        mockRestServiceServer.expect(requestTo(REAL_REPOSITORY_URL))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(GITHUB_REPOSITORY_RESPONSES))
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
        mockRestServiceServer.expect(requestTo(REAL_PULL_REQUEST_URL))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(EXPECT_GITHUB_PULL_REQUEST_RESPONSES))
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
}
