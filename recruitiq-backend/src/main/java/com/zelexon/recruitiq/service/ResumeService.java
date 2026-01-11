package com.zelexon.recruitiq.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.zelexon.recruitiq.dao.ResumeFile;
import com.zelexon.recruitiq.repository.ResumeFileRepository;
import com.zelexon.recruitiq.repository.CandidateRepository;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ResumeService {
    @Autowired
    private ResumeFileRepository resumeFileRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private MinioClient minioClient;
    @Value("${minio.bucket}")
    private String bucketName;

    public void uploadResume(UUID candidateId, MultipartFile resumeFile) {
        try {
            String objectName = "resume/" + candidateId + "/" + resumeFile.getOriginalFilename();
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(resumeFile.getInputStream(), resumeFile.getSize(), -1)
                    .contentType(resumeFile.getContentType())
                    .build()
            );

            ResumeFile file = new ResumeFile();
            file.setId(UUID.randomUUID());
            file.setCandidate(candidateRepository.findById(candidateId).orElseThrow());
            file.setStorageKey(objectName);
            file.setMimeType(resumeFile.getContentType());
            file.setFileSize(resumeFile.getSize());
            file.setCreatedAt(LocalDateTime.now());
            resumeFileRepository.save(file);
        } catch (Exception e) {
            throw new RuntimeException("Resume upload failed", e);
        }
    }
}
