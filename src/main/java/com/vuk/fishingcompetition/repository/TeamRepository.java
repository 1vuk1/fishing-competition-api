package com.vuk.fishingcompetition.repository;

import com.vuk.fishingcompetition.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    Optional<Team> findTeamByName(String name);

}
