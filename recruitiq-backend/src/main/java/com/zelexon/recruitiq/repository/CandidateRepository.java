package com.zelexon.recruitiq.repository;


import com.zelexon.recruitiq.dao.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    Optional<Candidate> findByCandidateCode(String candidateCode);
    List<Candidate> findAllByLocation(String location);
    List<Candidate> findAllByTotalExperienceYearsGreaterThanEqual(Integer years);
    List<Candidate> findAllByPrimarySkillsContaining(String skill);
    List<Candidate> findAllByCandidateCodeStartingWith(String prefix);
    List<Candidate> findAllByLocationAndTotalExperienceYearsGreaterThanEqual(String location, Integer years);
}