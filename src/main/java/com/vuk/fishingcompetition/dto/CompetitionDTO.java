package com.vuk.fishingcompetition.dto;



import java.time.LocalDate;
import java.util.List;

public record CompetitionDTO(

        Long id,
        String name,

        LocalDate date,
        String type,
        List<TeamDTO> teamList,
        double heaviestCatchWeight,
        String heaviestCatchTeamName,

        Integer numberOfCatches,

        Double averageWeight



) {
    public CompetitionDTO(Long id, String name, LocalDate date, String type) {
        this(id,name,date,type,null,0.0,"",0,0.0);
    }
}
