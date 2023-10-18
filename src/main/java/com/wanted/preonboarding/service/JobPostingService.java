package com.wanted.preonboarding.service;

import com.wanted.preonboarding.domain.*;
import com.wanted.preonboarding.service.dto.*;
import java.util.*;
import java.util.stream.*;
import lombok.*;
import org.springframework.stereotype.*;

@RequiredArgsConstructor
@Service
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    public void create(final JobPostingCreateRequest request) {
        final Company company = findCompanyById(request.getCompanyId());

        jobPostingRepository.save(new JobPosting(company.getId(), request.getPosition(), request.getReward(),
                request.getJobDescription(), request.getSkill()));
    }

    public void update(final Long id, final JobPostingUpdateRequest request) {
        final JobPosting jobPosting = findJobPostingById(id);

        jobPosting.update(request.getPosition(), request.getReward(), request.getJobDescription(), request.getSkill());
    }

    public void delete(final Long id) {
        final JobPosting jobPosting = findJobPostingById(id);

        jobPostingRepository.delete(jobPosting);
    }

    private JobPosting findJobPostingById(final Long id) {
        return jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 채용공고입니다."));
    }

    public List<JobPostingResponse> getJobPostings() {
        return jobPostingRepository.findAll().stream()
                .map(jobPosting -> new JobPostingResponse(jobPosting.getId(),
                        findCompanyById(jobPosting.getCompanyId()).getName(), jobPosting.getPosition(),
                        jobPosting.getReward(), jobPosting.getSkill()))
                .collect(Collectors.toList());
    }

    private Company findCompanyById(final Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않은 회사입니다."));
    }

    public JobPostingResponse getJobPostingById(final long id) {
        final JobPosting jobPosting = findJobPostingById(id);
        return new JobPostingResponse(jobPosting.getId(), findCompanyById(jobPosting.getCompanyId()).getName(),
                jobPosting.getPosition(), jobPosting.getReward(), jobPosting.getSkill());
    }
}
