package com.vuk.fishingcompetition.service;

import com.vuk.fishingcompetition.model.Catches;
import com.vuk.fishingcompetition.model.Competition;
import com.vuk.fishingcompetition.model.Team;
import org.springframework.stereotype.Service;

@Service
public class CompetitionStatisticsService {

    public void calculateStatistics(Competition competition) {
        int count = 0;
        double sum = 0.0;
        double biggest = 0.0;
        String heavyestCatchTeam = "";

        for (Team team : competition.getTeamList()) {
            for (Catches itemCatch : team.getCatchList()) {

                if(biggest<itemCatch.getWeight()){
                    biggest = itemCatch.getWeight();
                    heavyestCatchTeam = team.getName();
                }

                sum += itemCatch.getWeight();
                count++;

            }
        }

        double avg = sum/count;
        avg = Math.round(avg*100);
        avg = avg/100;

        competition.setAverageWeight(avg);
        competition.setHeaviestCatchWeight(biggest);
        competition.setNumberOfCatches(count);
        competition.setHeaviestCatchTeamName(heavyestCatchTeam);

    }



    public void sortTeamsByCatchWeight(Competition competition) {

        competition.getTeamList().sort((team1, team2) ->{

                double totalWeigh1 =team1.getCatchList().stream()
                        .mapToDouble(Catches::getWeight)
                        .sum();

                double totalWeigh2 =team2.getCatchList().stream()
                        .mapToDouble(Catches::getWeight)
                        .sum();

                return Double.compare(totalWeigh2,totalWeigh1);
        }
        );

    }

}
