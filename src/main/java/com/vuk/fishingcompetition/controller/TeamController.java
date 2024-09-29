package com.vuk.fishingcompetition.controller;

import com.vuk.fishingcompetition.dto.TeamDTO;
import com.vuk.fishingcompetition.model.Team;
import com.vuk.fishingcompetition.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teams")
public class TeamController {


    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }



    @PostMapping
    public ResponseEntity<Void> registerTeam(@RequestBody Team team){
        teamService.insertNewTeam(team);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeam(@PathVariable(name = "id") Long id) {

      return  ResponseEntity.ok(teamService.getTeam(id));

    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getTeams(){
        return ResponseEntity.ok(teamService.getTeams());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTeam(@PathVariable(name = "id") Long id,@RequestBody Team team){
        teamService.updateTeam(id,team);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable(name = "id") Long id) {

        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }




}
