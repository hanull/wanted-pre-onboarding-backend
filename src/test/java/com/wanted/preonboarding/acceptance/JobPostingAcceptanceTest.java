package com.wanted.preonboarding.acceptance;

import static org.assertj.core.api.Assertions.*;

import com.wanted.preonboarding.service.dto.*;
import io.restassured.*;
import io.restassured.response.*;
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
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/jobPostings");

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}