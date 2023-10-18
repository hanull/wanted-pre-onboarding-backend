package com.wanted.preonboarding.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.wanted.preonboarding.domain.*;
import com.wanted.preonboarding.service.dto.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.jdbc.*;

@Sql(value = "classpath:init.sql")
@SpringBootTest
class JobPostingServiceTest {

    @Autowired
    private JobPostingService jobPostingService;
    @Autowired
    private JobPostingRepository jobPostingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @DisplayName("채용공고를 등록한다.")
    @Test
    void createJobPosting() {
        final Company savedCompany = companyRepository.save(new Company("원티드"));
        final JobPostingCreateRequest request = new JobPostingCreateRequest(savedCompany.getId(), "백엔드 주니어 개발자",
                1000000, "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python");

        assertThatNoException().isThrownBy(() -> jobPostingService.create(request));
    }

    @DisplayName("존재하지 않은 회사를 채용공고에 등록할 때, 예외가 발생한다.")
    @Test
    void nonExistCompanyCreate() {
        final JobPostingCreateRequest request = new JobPostingCreateRequest(-1L, "백엔드 주니어 개발자", 1000000,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python");

        assertThatThrownBy(() -> jobPostingService.create(request))
                .hasMessage("존재하지 않은 회사입니다.");
    }

    @DisplayName("채용 보상금이 0원이하인 경우, 예외가 발생한다.")
    @Test
    void lessThanMinimumReward() {
        final int reward = -1;
        final JobPostingCreateRequest request = new JobPostingCreateRequest(1L, "백엔드 주니어 개발자", reward,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python");

        assertThatThrownBy(() -> jobPostingService.create(request))
                .hasMessage("채용 보상금은 0원 이상이어야 합니다.");
    }

    @DisplayName("채용공고를 수정한다.")
    @Test
    void updateJobPosting() {
        createJobPostingOnce();
        final JobPostingUpdateRequest request = new JobPostingUpdateRequest("백엔드 시니어 개발자", 3000000,
                "백엔드 시니어를 적극 채용합니다.", "go");

        jobPostingService.update(1L, request);

        final JobPostingResponse response = jobPostingService.getJobPostingById(1L);

        assertAll(
                () -> assertThat(response.getId()).isEqualTo(1L),
                () -> assertThat(response.getSkill()).isEqualTo(request.getSkill())
        );
    }

    @DisplayName("존재하지 않은 채용공고를 수정할 때, 예외가 발생한다.")
    @Test
    void nonExistJobPostingUpdate() {
        final Long nonExistId = 0L;
        final JobPostingUpdateRequest request = new JobPostingUpdateRequest("백엔드 시니어 개발자", 3000000,
                "백엔드 시니어를 적극 채용합니다.", "Java");

        assertThatThrownBy(() -> jobPostingService.update(nonExistId, request))
                .hasMessage("존재하지 않은 채용공고입니다.");
    }

    @DisplayName("채용공고를 삭제한다.")
    @Test
    void deleteJobPosting() {
        createJobPostingOnce();
        final long jobPostingId = 1L;

        assertThatNoException().isThrownBy(() -> jobPostingService.delete(jobPostingId));
    }

    @DisplayName("존재하지 않는 채용공고를 삭제할 때, 예외가 발생한다.")
    @Test
    void nonExistJobPostingDelete() {
        final long nonExistId = 0L;

        assertThatThrownBy(() -> jobPostingService.delete(nonExistId))
                .hasMessage("존재하지 않은 채용공고입니다.");
    }

    @DisplayName("모든 채용공고를 조회한다.")
    @Test
    void getJobPostings() {
        createJobPostingOnce();
        final List<JobPostingResponse> jobPostings = jobPostingService.getJobPostings();

        assertThat(jobPostings.size()).isEqualTo(1);
    }

    @DisplayName("채용공고 상세 조회를 한다.")
    @Test
    void getJobPostingById() {
        createJobPostingOnce();
        final long id = 1L;

        final JobPostingResponse response = jobPostingService.getJobPostingById(id);

        assertThat(response.getId()).isEqualTo(id);
    }

    private void createJobPostingOnce() {
        jobPostingRepository.save(new JobPosting(1L, "백엔드 주니어", 1000000, "백엔드 주니어 개발자를 채용합니다.", "Java"));
    }
}
