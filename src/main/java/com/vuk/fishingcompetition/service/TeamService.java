package com.vuk.fishingcompetition.service;

import com.vuk.fishingcompetition.dto.TeamDTO;
import com.vuk.fishingcompetition.mapper.TeamDTOMapper;
import com.vuk.fishingcompetition.model.Team;
import com.vuk.fishingcompetition.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamDTOMapper teamDTOMapper;

    @Autowired
    public TeamService(TeamRepository teamRepository,TeamDTOMapper teamDTOMapper) {
        this.teamRepository = teamRepository;
        this.teamDTOMapper=teamDTOMapper;
    }




    public void insertNewTeam(Team team){

         teamRepository.findTeamByName(team.getName()).ifPresent(
                 thisTeam -> {
                     throw new IllegalStateException("Team with this name already exists!");
                 }
         );


        teamRepository.save(team);


    }

    public TeamDTO getTeam(Long id){
        return teamRepository.findById(id).map(teamDTOMapper).orElseThrow(() -> new EntityNotFoundException("Team not found!"));
    }

    public List<TeamDTO> getTeams(){

        List<TeamDTO> list = teamRepository.findAll().stream().map(teamDTOMapper).collect(Collectors.toList()) ;


        return list;

    }

    public void deleteTeam(Long id){

        Optional<Team> optional = teamRepository.findById(id);

        if(optional.isEmpty())
            throw new EntityNotFoundException("Team Not found!");

        teamRepository.delete(optional.get());

    }

    @Transactional
    public void updateTeam(Long id,Team newTeam){
        Team team = teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team Not Found!"));

        if(newTeam.getName()!=null && !newTeam.getName().isBlank() && !Objects.equals(newTeam.getName(),team.getName())){
          Optional<Team> optional = teamRepository.findTeamByName(newTeam.getName());
                  if(optional.isPresent()){
                      throw new IllegalStateException("Name is Taken!");
                  }
              team.setName(newTeam.getName());
        }

        if(newTeam.getNumberOfMembers()!=null && newTeam.getNumberOfMembers()>0&&
                newTeam.getNumberOfMembers()<3&& !Objects.equals(newTeam.getNumberOfMembers(),team.getNumberOfMembers())){
            team.setNumberOfMembers(newTeam.getNumberOfMembers());
        }

        if (newTeam.getCatchList() != null &&
                !newTeam.getCatchList().isEmpty() &&
                !newTeam.getCatchList().equals(team.getCatchList())) {

            team.setCatchList(newTeam.getCatchList());

        }



    }




}
