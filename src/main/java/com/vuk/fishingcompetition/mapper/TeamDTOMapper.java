package com.vuk.fishingcompetition.mapper;

import com.vuk.fishingcompetition.dto.TeamDTO;
import com.vuk.fishingcompetition.model.Catches;
import com.vuk.fishingcompetition.model.Team;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class TeamDTOMapper implements Function<Team, TeamDTO> {


    @Override
    public TeamDTO apply(Team team) {

        double totalWeight =calculateTotalCatchWeight(team);

        int totalCatches = calculateTotalCatches(team);

        return new TeamDTO(team.getId(), team.getName(), team.getNumberOfMembers(),team.getCatchList(), totalWeight, totalCatches);
    }

    public TeamDTO toBasic(Team team){
        return  new TeamDTO(team.getId(),team.getName(), team.getNumberOfMembers(),team.getCatchList());
    }

    private double calculateTotalCatchWeight(Team team) {
        return team.getCatchList().stream().mapToDouble(Catches::getWeight).sum();
    }

    private int calculateTotalCatches(Team team) {
        return team.getCatchList().size();
    }
}
