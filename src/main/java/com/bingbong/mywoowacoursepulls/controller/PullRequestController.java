package com.bingbong.mywoowacoursepulls.controller;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import com.bingbong.mywoowacoursepulls.service.PullRequestService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/pull-requests")
public class PullRequestController {

    private final PullRequestService pullRequestService;

    public PullRequestController(PullRequestService pullRequestService) {
        this.pullRequestService = pullRequestService;
    }

    @GetMapping
    public ResponseEntity<List<PullRequestResponse>> findPullRequests(@RequestParam String nickname) {
        List<PullRequestResponse> pullRequests = pullRequestService.findPullRequestsByNickname(nickname);

        return ResponseEntity.status(HttpStatus.OK)
            .body(pullRequests);
    }
}
