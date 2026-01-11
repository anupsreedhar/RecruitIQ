package com.zelexon.recruitiq.dto;

import org.springframework.web.multipart.MultipartFile;

public class CandidateResumeUploadDTO {
    private Long candidateId;
    private MultipartFile resumeFile;

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }
    public MultipartFile getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(MultipartFile resumeFile) {
        this.resumeFile = resumeFile;
    }
}
