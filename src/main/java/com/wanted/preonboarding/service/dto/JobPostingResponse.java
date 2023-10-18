package com.wanted.preonboarding.service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class JobPostingResponse {

    private Long id;
    private String companyName;
    private String position;
    private int reward;
    private String skill;
}
