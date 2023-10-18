package com.wanted.preonboarding.service;

import com.wanted.preonboarding.domain.*;
import com.wanted.preonboarding.service.dto.*;
import lombok.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public void create(final JobPostingCreateRequest request) {
        final Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("존재하지 않은 회사입니다."));

        jobPostingRepository.save(new JobPosting(company.getId(), request.getPosition(), request.getReward(),
                request.getJobDescription(), request.getSkill()));
    }

    public void update(final Long id, final JobPostingUpdateRequest request) {
        final JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 채용공고입니다."));

        jobPosting.update(request.getPosition(), request.getReward(), request.getJobDescription(), request.getSkill());
    }
}
