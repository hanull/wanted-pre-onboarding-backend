package com.wanted.preonboarding.domain;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;
    private String position;
    private int reward;
    private String jobDescription;
    private String skill;

    public JobPosting(final Long companyId, final String position, final int reward, final String jobDescription,
                      final String skill) {
        this(null, companyId, position, reward, jobDescription, skill);
    }

    public JobPosting(final Long id, final Long companyId, final String position, final int reward,
                      final String jobDescription, final String skill) {
        this.id = id;
        this.companyId = companyId;
        this.position = position;
        this.reward = reward;
        this.jobDescription = jobDescription;
        this.skill = skill;
    }
}
