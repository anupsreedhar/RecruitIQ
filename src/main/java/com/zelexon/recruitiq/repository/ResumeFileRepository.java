package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.FileType;
import com.zelexon.recruitiq.dao.ResumeFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResumeFileRepository extends JpaRepository<ResumeFile, UUID> {
    List<ResumeFile> findAllByCandidate_Id(UUID candidateId);

    Optional<ResumeFile> findFirstByCandidate_IdOrderByCreatedAtDesc(UUID candidateId);

    Optional<ResumeFile> findFirstByCandidate_IdAndFileTypeOrderByCreatedAtDesc(UUID candidateId, FileType fileType);
}
