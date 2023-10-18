package com.wanted.preonboarding.acceptance;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wanted.preonboarding.service.dto.*;
import io.restassured.*;
import io.restassured.response.*;
import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.context.SpringBootTest.*;
import org.springframework.boot.test.web.server.*;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.*;

@Sql(value = "classpath:init.sql")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class JobPostingAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setPort() {
        RestAssured.port = port;
    }

    @DisplayName("채용공고 등록에 성공하면, 200을 반환한다.")
    @Test
    void createJobPosting() {
        final JobPostingCreateRequest request = new JobPostingCreateRequest(1L, "백엔드 주니어 개발자", 1000000,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python");

        final Response response = RestAssured.given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/jobPostings");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("채용공고 수정에 성공하면, 200을 반환한다.")
    @Test
    void updateJobPosting() {
        final JobPostingUpdateRequest request = new JobPostingUpdateRequest("백엔드 시니어 개발자", 3000000,
                "백엔드 시니어를 적극 채용합니다.", "Java");
        final long jobPostingId = 1L;

        final Response response = RestAssured.given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/api/jobPostings/" + jobPostingId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("채용공고 삭제에 성공하면, 200을 반환한다.")
    @Test
    void deleteJobPosting() {
        final long jobPostingId = 1L;
        final Response response = RestAssured.given().log().all()
                .when().delete("/api/jobPostings/" + jobPostingId);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("채용공고 조회에 성공하면, 상태코드 200과 채용공고 목록을 반환한다.")
    @Test
    void findAll() {
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/jobPostings")
                .then().log().all()
                .extract();

        final List<Long> jobPostingIds = response.jsonPath().getList(".", JobPostingResponse.class).stream()
                .map(JobPostingResponse::getId)
                .collect(Collectors.toList());

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(jobPostingIds.size()).isEqualTo(1)
        );
    }
}
