package com.bingbong.mywoowacoursepulls.dto;

import com.bingbong.mywoowacoursepulls.domain.PullRequest;
import java.util.List;
import java.util.stream.Collectors;

public class PullRequestAssembler {

    public static List<PullRequest> listAssemble(
        List<GithubPullRequestResponse> githubPullRequestResponses) {
        return githubPullRequestResponses.stream()
            .map(PullRequest::of)
            .collect(Collectors.toList());
    }
}
