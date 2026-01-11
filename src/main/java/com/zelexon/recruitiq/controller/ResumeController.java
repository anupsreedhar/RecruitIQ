package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dto.ApiResponseWrapper;
import com.zelexon.recruitiq.service.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping(path = "/{candidateId}/upload", consumes = "multipart/form-data")
    @Operation(
            summary = "Upload candidate resume",
            description = "Uploads a resume file for the specified candidate."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resume uploaded successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Candidate not found")
    })
    public ResponseEntity<ApiResponseWrapper<String>> uploadResume(
            @PathVariable UUID candidateId,
            @RequestPart("resume") MultipartFile resumeFile
    ) {
        resumeService.uploadResume(candidateId, resumeFile);
        return ResponseEntity.ok(ApiResponseWrapper.ok(null, "Resume uploaded successfully"));
    }
}
