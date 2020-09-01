package com.bingbong.mywoowacoursepulls.service;

import com.bingbong.mywoowacoursepulls.domain.PullRequest;
import com.bingbong.mywoowacoursepulls.dto.GithubPullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.GithubRepositoryResponse;
import com.bingbong.mywoowacoursepulls.dto.PullRequestAssembler;
import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.PullRequestResponseAssembler;
import com.bingbong.mywoowacoursepulls.repository.PullRequestRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PullRequestService {

    private final PullRequestRepository pullRequestRepository;
    private final GithubApiService githubApiService;

    public PullRequestService(
        PullRequestRepository pullRequestRepository,
        GithubApiService githubApiService) {
        this.pullRequestRepository = pullRequestRepository;
        this.githubApiService = githubApiService;
    }

    public List<PullRequestResponse> findPullRequestsByNickname(String nickname) {
        return null;
    }

    @Transactional
    public List<PullRequestResponse> savePullRequests() {
        List<GithubRepositoryResponse> githubRepositoryResponses = githubApiService
            .getAllRepositories("woowacourse");

        List<GithubPullRequestResponse> githubPullRequestResponses = new ArrayList<>();

        githubRepositoryResponses.forEach(githubRepositoryResponse -> githubPullRequestResponses
            .addAll(githubApiService.getAllPullRequests(githubRepositoryResponse.getName(), "all"))
        );

        List<PullRequest> pullRequests = PullRequestAssembler
            .listAssemble(githubPullRequestResponses);

        List<PullRequest> savedPullRequests = pullRequestRepository.saveAll(pullRequests);
        return PullRequestResponseAssembler.listAssemble(savedPullRequests);
    }
}
