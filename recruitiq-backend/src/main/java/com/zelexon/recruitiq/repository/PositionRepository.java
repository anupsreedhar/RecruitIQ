package com.zelexon.recruitiq.repository;

import com.zelexon.recruitiq.dao.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends JpaRepository<Position, UUID> {
    List<Position> findAllByVendor_Id(UUID vendorId);

    List<Position> findAllByVendor_IdAndStatus(UUID vendorId, String status);

    List<Position> findAllByStatus(String status);

    Optional<Position> findFirstByVendor_IdAndTitleIgnoreCase(UUID vendorId, String title);
}
