package com.wanted.preonboarding.controller;

import com.wanted.preonboarding.service.*;
import com.wanted.preonboarding.service.dto.*;
import javax.websocket.server.*;
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
}
