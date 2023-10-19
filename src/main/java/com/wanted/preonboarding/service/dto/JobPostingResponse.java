package com.wanted.preonboarding.service.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class JobPostingResponse {

    private Long id;
    private String companyName;
    private String position;
    private int reward;
    private String skill;
}
