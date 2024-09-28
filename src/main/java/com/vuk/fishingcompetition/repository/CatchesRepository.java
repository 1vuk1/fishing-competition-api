package com.vuk.fishingcompetition.repository;

import com.vuk.fishingcompetition.model.Catches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CatchesRepository extends JpaRepository<Catches,Long> {





}
