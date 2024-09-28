package com.vuk.fishingcompetition.dto;

import com.vuk.fishingcompetition.model.Catches;

import java.util.List;

public record TeamDTO(
        Long id,
        String name,
        Integer numberOfMembers,

        List<Catches> catches,

        Double totalCatchWeight,

        Integer numberOfCatches

) {
    public TeamDTO(Long id, String name, Integer numberOfMembers,List<Catches> catches) {
        this(id, name, numberOfMembers,catches, 0.0, 0);
    }
}
