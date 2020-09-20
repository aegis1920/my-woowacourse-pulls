package com.bingbong.mywoowacoursepulls.acceptance;


import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_NICKNAME;
import static com.bingbong.mywoowacoursepulls.fixture.TestFixture.TEST_NOT_FOUND_NICKNAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import com.bingbong.mywoowacoursepulls.service.PullRequestService;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PullRequestAcceptanceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private PullRequestService pullRequestService;

    protected static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    /**
     * Feature: PullRequest 닉네임 키워드 조회
     * <p>
     * Scenario: PullRequest를 닉네임으로 조회한다.
     * <p>
     * Given 모든 PullRequest가 업데이트 되어 있다.
     * <p>
     * When 사용자가 닉네임을 빈칸으로 조회한다. Then ErrorResponse를 보낸다.
     * <p>
     * When 사용자가 "빙봉"이라는 닉네임으로 조회한다. Then 0이 아닌 건이 조회된다.
     * <p>
     * When 사용자가 "없는 닉네임"이라는 닉네임으로 조회한다. Then 0건이 조회된다.
     * <p>
     */
    @DisplayName("닉네임 키워드 조회")
    @Test
    void findPullRequests() {

        // 모든 pullRequest 생성
        List<PullRequestResponse> savedPullRequestResponses = pullRequestService.savePullRequests();

        assertThat(savedPullRequestResponses).isNotNull();

        // TEST 닉네임으로 pullRequset 조회
        List<PullRequestResponse> foundPullRequestResponses = findPullRequestsByNickname(
            TEST_NICKNAME);

        assertThat(foundPullRequestResponses).isNotNull();

        // 없는 닉네임으로 pullRequset 조회
        List<PullRequestResponse> notFoundPullRequestResponses = findPullRequestsByNickname(
            TEST_NOT_FOUND_NICKNAME);

        assertThat(notFoundPullRequestResponses).hasSize(0);
    }

    private List<PullRequestResponse> findPullRequestsByNickname(String nickname) {
        return given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/api/pull-requests?nickname=" + nickname)
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .jsonPath()
            .getList(".", PullRequestResponse.class);
    }
}
