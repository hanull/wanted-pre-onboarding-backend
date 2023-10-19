package com.wanted.preonboarding.service.dto;

import java.util.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class JobPostingDetailResponse {

    private Long id;
    private String companyName;
    private String position;
    private int reward;
    private String skill;
    private String jobDescription;
    private List<Long> otherJobPostingIds;

    public JobPostingDetailResponse(final Long id, final String companyName, final String position, final int reward,
                                    final String skill,
                                    final String jobDescription, final List<Long> otherJobPostingIds) {
        this.id = id;
        this.companyName = companyName;
        this.position = position;
        this.reward = reward;
        this.skill = skill;
        this.jobDescription = jobDescription;
        this.otherJobPostingIds = otherJobPostingIds;
    }
}
