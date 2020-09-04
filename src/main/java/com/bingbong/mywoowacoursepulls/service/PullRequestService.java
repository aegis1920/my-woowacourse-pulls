package com.bingbong.mywoowacoursepulls.service;

import com.bingbong.mywoowacoursepulls.domain.PullRequest;
import com.bingbong.mywoowacoursepulls.dto.GithubPullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.GithubRepositoryResponse;
import com.bingbong.mywoowacoursepulls.dto.PullRequestAssembler;
import com.bingbong.mywoowacoursepulls.dto.PullRequestRequest;
import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import com.bingbong.mywoowacoursepulls.dto.PullRequestResponseAssembler;
import com.bingbong.mywoowacoursepulls.repository.PullRequestRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:config/application-prod.properties")
public class PullRequestService {

    public static final String DEFAULT_ORG_NAME = "woowacourse";
    public static final String DEFAULT_PULL_REQUEST_STATE = "all";

    private final PullRequestRepository pullRequestRepository;
    private final GithubApiService githubApiService;

    public PullRequestService(
        PullRequestRepository pullRequestRepository, GithubApiService githubApiService) {
        this.pullRequestRepository = pullRequestRepository;
        this.githubApiService = githubApiService;
    }

    @Transactional(readOnly = true)
    public List<PullRequestResponse> findPullRequestsByNickname(
        PullRequestRequest pullRequestRequest) {
        List<PullRequest> pullRequests = pullRequestRepository
            .findAllByTitleContaining(pullRequestRequest.getNickname());
        return PullRequestResponseAssembler.listAssemble(pullRequests);
    }

    @Transactional
    public List<PullRequestResponse> savePullRequests() {
        List<GithubRepositoryResponse> githubRepositoryResponses = githubApiService
            .getAllRepositories(DEFAULT_ORG_NAME);

        List<GithubPullRequestResponse> githubPullRequestResponses = getGithubPullRequestResponses(
            githubRepositoryResponses);

        List<PullRequest> pullRequests = PullRequestAssembler
            .listAssemble(githubPullRequestResponses);

        pullRequestRepository.deleteAll();
        List<PullRequest> savedPullRequests = pullRequestRepository.saveAll(pullRequests);
        return PullRequestResponseAssembler.listAssemble(savedPullRequests);
    }

    private List<GithubPullRequestResponse> getGithubPullRequestResponses(
        List<GithubRepositoryResponse> githubRepositoryResponses) {
        List<GithubPullRequestResponse> githubPullRequestResponses = new ArrayList<>();

        githubRepositoryResponses.forEach(githubRepositoryResponse -> githubPullRequestResponses
            .addAll(githubApiService.getAllPullRequests(githubRepositoryResponse.getName(),
                DEFAULT_PULL_REQUEST_STATE))
        );
        return githubPullRequestResponses;
    }
}
