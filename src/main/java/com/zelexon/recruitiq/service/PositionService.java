package com.zelexon.recruitiq.service;

import com.zelexon.recruitiq.dao.Position;
import com.zelexon.recruitiq.dao.Vendor;
import com.zelexon.recruitiq.dto.PositionDTO;
import com.zelexon.recruitiq.repository.PositionRepository;
import com.zelexon.recruitiq.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private VendorRepository vendorRepository;

    public List<PositionDTO> listAllPositions() {
        return positionRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PositionDTO> listPositionsByVendor(UUID vendorId) {
        return positionRepository.findAllByVendor_Id(vendorId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PositionDTO> listPositionsByVendorAndStatus(UUID vendorId, String status) {
        return positionRepository.findAllByVendor_IdAndStatus(vendorId, status).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PositionDTO> listPositionsByStatus(String status) {
        return positionRepository.findAllByStatus(status).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<PositionDTO> getPositionByVendorAndTitle(UUID vendorId, String title) {
        return positionRepository.findFirstByVendor_IdAndTitleIgnoreCase(vendorId, title).map(this::toDTO);
    }

    public PositionDTO getPositionById(UUID positionId) {
        Position position = positionRepository.findById(positionId).orElseThrow();
        return toDTO(position);
    }

    public PositionDTO createPosition(PositionDTO dto, UUID vendorId) {
        Position position = new Position();
        position.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID());
        position.setVendor(vendorRepository.findById(vendorId).orElseThrow());
        position.setTitle(dto.getTitle());
        position.setJdText(dto.getJdText());
        position.setJdStructured(dto.getJdStructured());
        position.setStatus(dto.getStatus());
        position.setCreatedAt(java.time.LocalDateTime.now());
        Position saved = positionRepository.save(position);
        return toDTO(saved);
    }

    public PositionDTO updatePosition(UUID positionId, PositionDTO dto) {
        Position position = positionRepository.findById(positionId).orElseThrow();
        position.setTitle(dto.getTitle());
        position.setJdText(dto.getJdText());
        position.setJdStructured(dto.getJdStructured());
        position.setStatus(dto.getStatus());
        Position saved = positionRepository.save(position);
        return toDTO(saved);
    }

    private PositionDTO toDTO(Position position) {
        PositionDTO dto = new PositionDTO();
        dto.setId(position.getId());
        dto.setTitle(position.getTitle());
        dto.setJdText(position.getJdText());
        dto.setJdStructured(position.getJdStructured());
        dto.setStatus(position.getStatus());
        return dto;
    }
}
