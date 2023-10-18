package com.wanted.preonboarding.domain;

import javax.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class JobPosting {

    private static final int MINIMUM_REWARD = 0;

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
        validateReward(reward);

        this.id = id;
        this.companyId = companyId;
        this.position = position;
        this.reward = reward;
        this.jobDescription = jobDescription;
        this.skill = skill;
    }

    private void validateReward(final int reward) {
        if (reward < MINIMUM_REWARD) {
            throw new RuntimeException(String.format("채용 보상금은 %d원 이상이어야 합니다.", MINIMUM_REWARD));
        }
    }

    public void update(final String position, final int reward, final String jobDescription, final String skill) {
        this.position = position;
        this.reward = reward;
        this.jobDescription = jobDescription;
        this.skill = skill;
    }
}
