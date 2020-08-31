package com.bingbong.mywoowacoursepulls.service;

import static com.bingbong.mywoowacoursepulls.acceptance.PullRequestAcceptanceTest.TEST_NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class PullRequestServiceTest {

    @Mock
    private PullRequestService pullRequestService;

    private List<PullRequestResponse> pullRequestResponses = Arrays.asList(
        PullRequestResponse.of(1L, "빙봉", "woowacourse", "open", "url"),
        PullRequestResponse.of(2L, "빙봉", "woowacourse", "open", "url"),
        PullRequestResponse.of(3L, "빙봉", "woowacourse", "open", "url")
    );

    @DisplayName("닉네임 키워드 조회 - 성공")
    @Test
    void findPullRequestsByNickname_SuccessToFind() {
        Mockito.when(pullRequestService.findPullRequestsByNickname(anyString()))
            .thenReturn(pullRequestResponses);
        List<PullRequestResponse> pullRequestResponses = pullRequestService
            .findPullRequestsByNickname(TEST_NICKNAME);

        assertThat(pullRequestResponses).hasSize(3);
    }
}
