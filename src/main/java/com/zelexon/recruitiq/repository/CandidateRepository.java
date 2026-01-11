package com.zelexon.recruitiq.repository;


import com.zelexon.recruitiq.dao.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    Optional<Candidate> findByCandidateCode(String candidateCode);
    List<Candidate> findAllByLocation(String location);
    List<Candidate> findAllByTotalExperienceYearsGreaterThanEqual(Integer years);
    @Query(
            value = "SELECT * FROM candidate WHERE jsonb_exists(primary_skills::jsonb, ?1)",
            nativeQuery = true
    )
    List<Candidate> findAllByPrimarySkill(String skill);
    List<Candidate> findAllByCandidateCodeStartingWith(String prefix);
    List<Candidate> findAllByLocationAndTotalExperienceYearsGreaterThanEqual(String location, Integer years);
}