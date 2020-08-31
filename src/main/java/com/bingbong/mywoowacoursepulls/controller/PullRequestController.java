package com.bingbong.mywoowacoursepulls.controller;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/pull-requests")
public class PullRequestController {

    @GetMapping
    public ResponseEntity<List<PullRequestResponse>> findAllPullRequests(
        @RequestBody String nickname) {
        List<PullRequestResponse> pullRequestResponses = Arrays.asList(
            PullRequestResponse.of(1L, "빙봉", "woowacourse", "open", "url"),
            PullRequestResponse.of(2L, "빙봉", "woowacourse", "open", "url"),
            PullRequestResponse.of(3L, "빙봉", "woowacourse", "open", "url")
        );

        return ResponseEntity.status(HttpStatus.OK)
            .body(pullRequestResponses);
    }
}
