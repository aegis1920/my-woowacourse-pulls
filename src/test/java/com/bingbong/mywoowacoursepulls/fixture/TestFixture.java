package com.bingbong.mywoowacoursepulls.fixture;

import com.bingbong.mywoowacoursepulls.dto.GithubRepositoryResponse;
import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class TestFixture {

    public static final String TEST_NICKNAME = "빙봉";
    public static final String TEST_REPO_NAME = "woowa-writing-2";
    public static final String TEST_REPO_URL = "https://github.com/woowacourse/woowa-writing-2";
    public static final String TEST_ORG_NAME = "woowacourse";
    public static final String TEST_PULL_REQUEST_TITLE = "[빙봉] 레벨 3 - 회고 글쓰기 미션 제출합니다.";
    public static final String TEST_PULL_REQUEST_URL = "https://github.com/woowacourse/woowa-writing-2";
    public static final String TEST_PULL_REQUEST_DEFAULT_STATE = "all";
    public static final String TEST_PULL_REQUEST_STATE = "open";
    public static final String TEST_PULL_REQUEST_CREATED_AT = "2020-09-01T00:00:00Z";
    public static final String TEST_PULL_REQUEST_UPDATED_AT = "2020-09-01T00:00:00Z";

    public static final String REAL_REPOSITORY_URL = "https://api.github.com/orgs/woowacourse/repos";
    public static final String REAL_PULL_REQUEST_URL = "https://api.github.com/repos/woowacourse/woowa-writing-2/pulls?state=all";

    public static final List<PullRequestResponse> PULL_REQUEST_RESPONSES = Arrays.asList(
        PullRequestResponse.of(
            1L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_STATE, TEST_PULL_REQUEST_URL,
            ZonedDateTime.now(), ZonedDateTime.now()
        ),
        PullRequestResponse.of(
            2L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_STATE, TEST_PULL_REQUEST_URL,
            ZonedDateTime.now(), ZonedDateTime.now()
        )
    );

    public static final List<GithubRepositoryResponse> GITHUB_REPOSITORY_RESPONSES = Arrays.asList(
        GithubRepositoryResponse.of(1L, TEST_REPO_NAME, TEST_REPO_URL),
        GithubRepositoryResponse.of(2L, TEST_REPO_NAME, TEST_REPO_URL)
    );

    public static final List<ExpectGithubPullRequestResponse> EXPECT_GITHUB_PULL_REQUEST_RESPONSES = Arrays
        .asList(
            new ExpectGithubPullRequestResponse(1L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_URL,
                TEST_PULL_REQUEST_STATE,
                TEST_PULL_REQUEST_CREATED_AT, TEST_PULL_REQUEST_UPDATED_AT),
            new ExpectGithubPullRequestResponse(2L, TEST_PULL_REQUEST_TITLE, TEST_PULL_REQUEST_URL,
                TEST_PULL_REQUEST_STATE,
                TEST_PULL_REQUEST_CREATED_AT, TEST_PULL_REQUEST_UPDATED_AT));

    static class ExpectGithubPullRequestResponse {

        private Long id;
        private String title;
        private String html_url;
        private String state;
        private String created_at;
        private String updated_at;

        ExpectGithubPullRequestResponse() {
        }

        public ExpectGithubPullRequestResponse(Long id, String title, String html_url,
            String state, String created_at, String updated_at) {
            this.id = id;
            this.title = title;
            this.html_url = html_url;
            this.state = state;
            this.created_at = created_at;
            this.updated_at = updated_at;
        }

        public Long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getHtml_url() {
            return html_url;
        }

        public String getState() {
            return state;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
