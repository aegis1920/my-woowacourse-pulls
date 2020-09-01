package com.bingbong.mywoowacoursepulls.service;

import static com.bingbong.mywoowacoursepulls.acceptance.PullRequestAcceptanceTest.TEST_NICKNAME;
import static com.bingbong.mywoowacoursepulls.service.GithubApiServiceTest.TEST_PULL_REQUEST_STATE;
import static com.bingbong.mywoowacoursepulls.service.GithubApiServiceTest.TEST_PULL_REQUEST_TITLE;
import static com.bingbong.mywoowacoursepulls.service.GithubApiServiceTest.TEST_PULL_REQUEST_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PullRequestServiceTest {

    @Mock
    private PullRequestService pullRequestService;

    private final List<PullRequestResponse> pullRequestResponses = Arrays.asList(
        PullRequestResponse.of(
            1L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_STATE, TEST_PULL_REQUEST_URL,
            ZonedDateTime.now(), ZonedDateTime.now()
        ),
        PullRequestResponse.of(
            1L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_STATE, TEST_PULL_REQUEST_URL,
            ZonedDateTime.now(), ZonedDateTime.now()
        )
    );

    @DisplayName("닉네임 키워드 조회 - 성공")
    @Test
    void findPullRequestsByNickname_SuccessToFind() {
        Mockito.when(pullRequestService.findPullRequestsByNickname(anyString()))
            .thenReturn(pullRequestResponses);

        List<PullRequestResponse> pullRequestResponses = pullRequestService
            .findPullRequestsByNickname(TEST_NICKNAME);

        assertThat(pullRequestResponses).hasSize(2);
    }

    @DisplayName("모든 PullRequest 저장 - 성공")
    @Test
    void savePullRequests_SuccessToSave() {
        Mockito.when(pullRequestService.savePullRequests()).thenReturn(pullRequestResponses);

        List<PullRequestResponse> pullRequests = pullRequestService.savePullRequests();

        assertThat(pullRequests).isNotNull();
    }
}
