package com.wanted.preonboarding.service;

import static org.assertj.core.api.Assertions.*;

import com.wanted.preonboarding.domain.*;
import com.wanted.preonboarding.service.dto.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

@SpringBootTest
class JobPostingServiceTest {

    @Autowired
    private JobPostingService jobPostingService;

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
    void notFoundCompany() {
        final JobPostingCreateRequest request = new JobPostingCreateRequest(-1L, "백엔드 주니어 개발자", 1000000,
                "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python");

        assertThatThrownBy(() -> jobPostingService.create(request))
                .hasMessage("존재하지 않은 회사입니다.");
    }
}
