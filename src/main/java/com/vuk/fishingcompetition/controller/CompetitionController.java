package com.vuk.fishingcompetition.controller;

import com.vuk.fishingcompetition.dto.CompetitionDTO;
import com.vuk.fishingcompetition.model.Competition;
import com.vuk.fishingcompetition.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/competition")
public class CompetitionController {


    private final CompetitionService competitionService;

    @Autowired
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @PostMapping
    public ResponseEntity<Void> createCompetition(@RequestBody Competition competition ){
        competitionService.insertNewCompetition(competition);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionDTO> getCompetition(@PathVariable("id") Long id){
        return ResponseEntity.ok(competitionService.getCompetition(id));
    }

    @GetMapping()
    public ResponseEntity<List<CompetitionDTO>>getCompetitions(){
        return ResponseEntity.ok(competitionService.getCompetitions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void>updateCompetition(@PathVariable("id")Long id,@RequestBody Competition competition){
            competitionService.updateCompetition(id,competition);
            return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCopmetition(@PathVariable("id")Long id){
        competitionService.deleteCompetition(id);
        return ResponseEntity.noContent().build();
    }




}
