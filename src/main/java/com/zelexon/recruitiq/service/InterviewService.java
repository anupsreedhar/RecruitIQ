package com.zelexon.recruitiq.service;

import com.zelexon.recruitiq.dao.InterviewRequest;
import com.zelexon.recruitiq.dao.Vendor;
import com.zelexon.recruitiq.dao.Candidate;
import com.zelexon.recruitiq.dao.Position;
import com.zelexon.recruitiq.dto.InterviewRequestDTO;
import com.zelexon.recruitiq.repository.InterviewRequestRepository;
import com.zelexon.recruitiq.repository.VendorRepository;
import com.zelexon.recruitiq.repository.CandidateRepository;
import com.zelexon.recruitiq.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InterviewService {
    @Autowired
    private InterviewRequestRepository interviewRequestRepository;
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PositionRepository positionRepository;

    public List<InterviewRequestDTO> listInterviewRequests(UUID vendorId, String status) {
        List<InterviewRequest> requests;
        if (vendorId != null && status != null) {
            requests = interviewRequestRepository.findAllByVendor_IdAndStatus(vendorId, status);
        } else if (vendorId != null) {
            requests = interviewRequestRepository.findAllByVendor_Id(vendorId);
        } else {
            requests = interviewRequestRepository.findAll();
        }
        return requests.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public InterviewRequestDTO createInterviewRequest(InterviewRequestDTO request) {
        InterviewRequest entity = new InterviewRequest();
        entity.setId(UUID.randomUUID());
        entity.setVendor(getVendor(request.getVendorId()));
        entity.setCandidate(getCandidate(request.getCandidateId()));
        entity.setPosition(getPosition(request.getPositionId()));
        entity.setStatus(request.getStatus());
        entity.setRequestedAt(request.getRequestedAt() != null ? request.getRequestedAt() : java.time.LocalDateTime.now());
        InterviewRequest saved = interviewRequestRepository.save(entity);
        return toDTO(saved);
    }

    public InterviewRequestDTO updateInterviewStatus(UUID requestId, Map<String, String> body) {
        InterviewRequest entity = interviewRequestRepository.findById(requestId).orElseThrow();
        if (body.containsKey("status")) {
            entity.setStatus(body.get("status"));
        }
        InterviewRequest saved = interviewRequestRepository.save(entity);
        return toDTO(saved);
    }

    private InterviewRequestDTO toDTO(InterviewRequest entity) {
        InterviewRequestDTO dto = new InterviewRequestDTO();
        dto.setVendorId(entity.getVendor() != null ? entity.getVendor().getId() : null);
        dto.setCandidateId(entity.getCandidate() != null ? entity.getCandidate().getId() : null);
        dto.setPositionId(entity.getPosition() != null ? entity.getPosition().getId() : null);
        dto.setStatus(entity.getStatus());
        dto.setRequestedAt(entity.getRequestedAt());
        return dto;
    }

    private Vendor getVendor(UUID id) {
        return vendorRepository.findById(id).orElseThrow();
    }
    private Candidate getCandidate(UUID id) {
        return candidateRepository.findById(id).orElseThrow();
    }
    private Position getPosition(UUID id) {
        return positionRepository.findById(id).orElseThrow();
    }
}
