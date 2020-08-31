package com.bingbong.mywoowacoursepulls.acceptance;


import static org.assertj.core.api.Assertions.assertThat;

import com.bingbong.mywoowacoursepulls.dto.PullRequestResponse;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PullRequestAcceptanceTest {

    private static final String TEST_NICKNAME = "빙봉";
    @LocalServerPort
    private int port;

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
     * When 사용자가 "빙봉"이라는 닉네임으로 조회한다. Then 0 보다 큰 여러 건이 조회된다.
     * <p>
     * When 사용자가 "없는 닉네임"이라는 닉네임으로 조회한다. Then 0건이 조회된다.
     * <p>
     */
    @DisplayName("닉네임 키워드 조회")
    @Test
    void findPullRequestsByNickname() {
        List<PullRequestResponse> pullRequestResponses = findPullRequests();

        assertThat(pullRequestResponses).hasSize(3);
    }

    private List<PullRequestResponse> findPullRequests() {
        HashMap<String, String> params = new HashMap<>();
        params.put("nickname", TEST_NICKNAME);

        return given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .body(params)
            .when()
            .get("/api/pull-requests")
            .then()
            .log().all()
            .statusCode(HttpStatus.OK.value())
            .extract()
            .jsonPath()
            .getList(".", PullRequestResponse.class);
    }


}
