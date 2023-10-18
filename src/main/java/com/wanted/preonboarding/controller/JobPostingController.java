package com.wanted.preonboarding.controller;

import com.wanted.preonboarding.service.*;
import com.wanted.preonboarding.service.dto.*;
import java.util.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/jobPostings")
@RequiredArgsConstructor
@RestController
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody JobPostingCreateRequest request) {
        jobPostingService.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody JobPostingUpdateRequest request) {
        jobPostingService.update(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jobPostingService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<JobPostingResponse>> getJobPostings() {
        return ResponseEntity.ok(jobPostingService.getJobPostings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPostingResponse> getJobPostings(@PathVariable Long id) {
        return ResponseEntity.ok(jobPostingService.getJobPostingById(id));
    }
}
