package com.vuk.fishingcompetition.service;

import com.vuk.fishingcompetition.dto.CompetitionDTO;
import com.vuk.fishingcompetition.mapper.CompetitionDTOMapper;
import com.vuk.fishingcompetition.model.Catches;
import com.vuk.fishingcompetition.model.Competition;
import com.vuk.fishingcompetition.model.Team;
import com.vuk.fishingcompetition.repository.CompetitionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionService {


        private final CompetitionRepository competitionRepository;

        private final CompetitionDTOMapper competitionDTOMapper;

        private final CompetitionStatisticsService competitionStatisticsService;

    @Autowired
    public CompetitionService(CompetitionRepository competitionRepository, CompetitionDTOMapper competitionDTOMapper, CompetitionStatisticsService competitionStatisticsService) {
        this.competitionRepository = competitionRepository;
        this.competitionDTOMapper = competitionDTOMapper;
        this.competitionStatisticsService = competitionStatisticsService;
    }

    public void insertNewCompetition(Competition competition) {
        System.out.println(competition);
        competitionRepository.findCompetitionByName(competition.getName())
                .ifPresent(existingCompetition -> {
                    throw new IllegalStateException("Competition with this name already exists!");
                });

        competitionRepository.save(competition);
    }



        public CompetitionDTO getCompetition(Long id){

            Competition competition = competitionRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Competition not found!"));

            for (Team team : competition.getTeamList()) {
                List<Catches> catchesList = team.getCatchList().stream()
                        .filter(catch1 -> catch1.getCompetition().getId()
                                .equals(id)).collect(Collectors.toList());

                team.setCatchList(catchesList);
            }


            competitionStatisticsService.calculateStatistics(competition);
            competitionStatisticsService.sortTeamsByCatchWeight(competition);


            return competitionDTOMapper.apply(competition);
        }

        public List<CompetitionDTO> getCompetitions(){

            List<CompetitionDTO> list = competitionRepository.findAll().stream().map(competitionDTOMapper)
                    .collect(Collectors.toList());

            return list;

        }


        public void deleteCompetition(Long id){

            Optional<Competition> optional =  competitionRepository.findById(id);

            if(optional.isEmpty()){
                throw new EntityNotFoundException("Competition not found!");
            }

            competitionRepository.delete(optional.get());

        }

    @Transactional
    public void updateCompetition(Long id, Competition newCompetition) {

            Competition competition = competitionRepository.findById(id).orElseThrow(() ->
                    new EntityNotFoundException("Competition not found! "));

        if (newCompetition.getName() != null && newCompetition.getName().length() > 0 &&
                !Objects.equals(newCompetition.getName(), competition.getName())) {

            competition.setName(newCompetition.getName());

        }

        if (newCompetition.getDate() != null && !Objects.equals(newCompetition.getDate(), competition.getDate())) {

            competition.setDate(newCompetition.getDate());

        }

        if (newCompetition.getType() != null && newCompetition.getType().length() > 0 &&
                !Objects.equals(newCompetition.getType(), competition.getType())) {

            competition.setType(newCompetition.getType());

        }

        if (newCompetition.getTeamList() != null &&
                !newCompetition.getTeamList().isEmpty() &&
                !newCompetition.getTeamList().equals(competition.getTeamList())) {

            competition.setTeamList(newCompetition.getTeamList());

        }



    }
}
