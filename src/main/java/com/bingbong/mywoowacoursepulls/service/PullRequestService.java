package com.bingbong.mywoowacoursepulls.service;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PullRequestService {

    public List<PullRequestResponse> findPullRequestsByNickname(String testNickname) {
        List<PullRequestResponse> pullRequestResponses = Arrays.asList(
            PullRequestResponse.of(1L, "빙봉", "woowacourse", "open", "url"),
            PullRequestResponse.of(2L, "빙봉", "woowacourse", "open", "url"),
            PullRequestResponse.of(3L, "빙봉", "woowacourse", "open", "url")
        );

        return pullRequestResponses;
    }
}
