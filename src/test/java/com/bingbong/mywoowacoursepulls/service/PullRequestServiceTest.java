package com.bingbong.mywoowacoursepulls.service;

import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.PULL_REQUEST_RESPONSES;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
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

    @DisplayName("닉네임 키워드 조회 - 성공")
    @Test
    void findPullRequestsByNickname_SuccessToFind() {
        Mockito.when(pullRequestService.findPullRequestsByNickname(any()))
            .thenReturn(PULL_REQUEST_RESPONSES);

        List<PullRequestResponse> pullRequestResponses = pullRequestService
            .findPullRequestsByNickname(TEST_NICKNAME);

        assertThat(pullRequestResponses).hasSize(2);
    }

    @DisplayName("모든 PullRequest 저장 - 성공")
    @Test
    void savePullRequests_SuccessToSave() {
        Mockito.when(pullRequestService.savePullRequests()).thenReturn(PULL_REQUEST_RESPONSES);

        List<PullRequestResponse> pullRequests = pullRequestService.savePullRequests();

        assertThat(pullRequests).isNotNull();
    }
}
