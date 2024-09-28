package com.vuk.fishingcompetition.mapper;

import com.vuk.fishingcompetition.dto.CompetitionDTO;
import com.vuk.fishingcompetition.dto.TeamDTO;
import com.vuk.fishingcompetition.model.Competition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CompetitionDTOMapper implements Function<Competition, CompetitionDTO> {

    private final TeamDTOMapper teamDTOMapper;


    public CompetitionDTOMapper(TeamDTOMapper teamDTOMapper) {
        this.teamDTOMapper = teamDTOMapper;
    }

    @Override
    public CompetitionDTO apply(Competition competition) {

        List<TeamDTO> teamDTO = competition.getTeamList().stream().map(teamDTOMapper).collect(Collectors.toList());

        return new CompetitionDTO(competition.getId(),

                competition.getName(),
                competition.getDate(),
                competition.getType(),
                teamDTO,
                competition.getHeaviestCatchWeight(),
                competition.getHeaviestCatchTeamName(),
                competition.getNumberOfCatches(),
                competition.getAverageWeight()

                );
    }
    public CompetitionDTO toBasic(Competition competition){
        return new CompetitionDTO(competition.getId(),competition.getName(),competition.getDate(), competition.getType());
    }
}
