package com.bingbong.mywoowacoursepulls.dto;

import com.bingbong.mywoowacoursepulls.domain.PullRequest;
import java.util.List;
import java.util.stream.Collectors;

public class PullRequestResponseAssembler {

    public static PullRequestResponse assemble(PullRequest pullRequest) {
        return new PullRequestResponse(
            pullRequest.getPullRequestId(),
            pullRequest.getTitle(),
            pullRequest.getState(),
            pullRequest.getHtmlUrl(),
            pullRequest.getPullRequestCreatedAt(),
            pullRequest.getPullRequestUpdatedAt()
        );
    }

    public static List<PullRequestResponse> listAssemble(List<PullRequest> pullRequests) {
        return pullRequests.stream()
            .map(PullRequestResponseAssembler::assemble)
            .collect(Collectors.toList());
    }
}
