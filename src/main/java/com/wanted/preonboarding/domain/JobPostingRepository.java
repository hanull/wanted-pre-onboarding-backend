package com.wanted.preonboarding.domain;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    @Query("select j.id from JobPosting j where j.companyId = :companyId and j.id <> :currentId")
    List<Long> findIdsExceptForCurrentByCompanyId(@Param("companyId") Long companyId, @Param("currentId") Long currentId);
}
