package com.bingbong.mywoowacoursepulls.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.bingbong.mywoowacoursepulls.dto.RepositoryResponse;
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

    private final String REPOSITORY_URL = "https://api.github.com/orgs/woowacourse/repos?per_page=100";
    @Autowired
    private GithubApiService githubApiService;
    @Autowired
    private MockRestServiceServer mockRestServiceServer;
    private ObjectMapper mapper = new ObjectMapper();


    @DisplayName("orgName에 해당하는 모든 Repository 조회")
    @Test
    void getAllRepositories_SuccessToGet() throws JsonProcessingException {
        // given
        List<RepositoryResponse> repositoryResponses = Arrays.asList(
            RepositoryResponse
                .of(1L, "nextstep_test", "https://github.com/woowacourse/nextstep_test"),
            RepositoryResponse.of(2L, "javable", "https://github.com/woowacourse/javable")
        );
        mockRestServiceServer.expect(requestTo(REPOSITORY_URL))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(repositoryResponses))
            );

        // when
        List<RepositoryResponse> expectRepositoryResponses = githubApiService
            .getAllRepositories("woowacourse").getBody();

        // then
        assertThat(expectRepositoryResponses).isNotNull();
        assertThat(expectRepositoryResponses).hasSize(2);

        RepositoryResponse expectRepositoryResponse = expectRepositoryResponses.get(0);

        assertThat(expectRepositoryResponse.getId()).isEqualTo(1L);
        assertThat(expectRepositoryResponse.getName()).isEqualTo("nextstep_test");
        assertThat(expectRepositoryResponse.getHtmlUrl())
            .isEqualTo("https://github.com/woowacourse/nextstep_test");
    }
}
