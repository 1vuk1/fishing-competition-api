package com.vuk.fishingcompetition.service;

import com.vuk.fishingcompetition.model.Catches;
import com.vuk.fishingcompetition.model.Competition;
import com.vuk.fishingcompetition.model.Team;
import com.vuk.fishingcompetition.repository.CatchesRepository;
import com.vuk.fishingcompetition.repository.CompetitionRepository;
import com.vuk.fishingcompetition.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class CatchesService {


    private final CatchesRepository catchesRepository;
    private final TeamRepository teamRepository;
    private final CompetitionRepository competitionRepository;

    @Autowired
    public CatchesService(CatchesRepository catchesRepository, TeamRepository teamRepository, CompetitionRepository competitionRepository) {
        this.catchesRepository = catchesRepository;
        this.teamRepository = teamRepository;
        this.competitionRepository = competitionRepository;
    }

    public void insertNewCatch(Catches catches, Long teamId, Long competitionId){

        Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
        Competition competition = competitionRepository.findById(competitionId).orElseThrow(() -> new RuntimeException("Competition not found"));
        catches.setCompetition(competition);
        catches.setTeam(team);

        catchesRepository.save(catches);

    }

    @Transactional
    public void updateCatch(Catches newcatch, Long competitionId, Long teamid){

       Catches catches = catchesRepository.findById(newcatch.getId()).orElseThrow(() -> new RuntimeException("Catch not found!"));

        if(newcatch.getWeight()!= null && newcatch.getWeight()>0.0 && !Objects.equals(newcatch.getWeight(),catches.getWeight())){
            catches.setWeight(newcatch.getWeight());
        }

        if(newcatch.getSpecies() != null && !Objects.equals(newcatch.getSpecies(),catches.getSpecies())){
            catches.setSpecies(newcatch.getSpecies());
        }



    }

    public void deleteCatch(Long id){

        Catches catches = catchesRepository.findById(id).orElseThrow(() -> new RuntimeException("Catch not found!"));

        catchesRepository.delete(catches);


    }





}
