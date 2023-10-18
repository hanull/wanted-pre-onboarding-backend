package com.wanted.preonboarding.service.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class JobPostingUpdateRequest {

    private String position;
    private int reward;
    private String jobDescription;
    private String skill;
}
