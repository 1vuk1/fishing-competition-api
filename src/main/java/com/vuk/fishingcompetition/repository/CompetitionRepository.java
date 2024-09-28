package com.vuk.fishingcompetition.repository;

import com.vuk.fishingcompetition.model.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition,Long> {


    Optional<Competition> findCompetitionByName(String name);


}
