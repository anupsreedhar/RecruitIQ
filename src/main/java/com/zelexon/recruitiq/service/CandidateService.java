package com.zelexon.recruitiq.service;

import com.zelexon.recruitiq.dao.Candidate;
import com.zelexon.recruitiq.dto.CandidateDTO;
import com.zelexon.recruitiq.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public void uploadResume(UUID candidateId, MultipartFile resumeFile) {
        // TODO: Implement resume upload logic
    }

    public List<CandidateDTO> listCandidates() {
        return candidateRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CandidateDTO getCandidateById(UUID candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        return toDTO(candidate);
    }

    public CandidateDTO getCandidateByCode(String candidateCode) {
        Candidate candidate = candidateRepository.findByCandidateCode(candidateCode).orElseThrow();
        return toDTO(candidate);
    }

    public CandidateDTO createCandidate(CandidateDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID());
        candidate.setCandidateCode(dto.getCandidateCode());
        candidate.setTotalExperienceYears(dto.getTotalExperienceYears());
        candidate.setPrimarySkills(dto.getPrimarySkills());
        candidate.setLocation(dto.getLocation());
        candidate.setCreatedAt(java.time.LocalDateTime.now());
        Candidate saved = candidateRepository.save(candidate);
        return toDTO(saved);
    }

    public CandidateDTO updateCandidateSkills(UUID candidateId, Map<String, Object> primarySkills) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow();
        candidate.setPrimarySkills(primarySkills);
        Candidate saved = candidateRepository.save(candidate);
        return toDTO(saved);
    }

    private CandidateDTO toDTO(Candidate candidate) {
        CandidateDTO dto = new CandidateDTO();
        dto.setId(candidate.getId());
        dto.setCandidateCode(candidate.getCandidateCode());
        dto.setTotalExperienceYears(candidate.getTotalExperienceYears());
        dto.setPrimarySkills(candidate.getPrimarySkills());
        dto.setLocation(candidate.getLocation());
        return dto;
    }
}
