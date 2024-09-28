package com.vuk.fishingcompetition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_members")
    private Integer numberOfMembers;

    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name = "participation",joinColumns = @JoinColumn(name = "team_id"),inverseJoinColumns = @JoinColumn(name = "competition_id"))
    @JsonIgnore
    private List<Competition> competitionList;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "team",cascade = {CascadeType.ALL},orphanRemoval = true)
    private List<Catches>catchList;


    public Team() {
    }

    public Team(String name, Integer numberOfMembers, List<Competition> competitionList, List<Catches> catchList) {
        this.name = name;
        this.numberOfMembers = numberOfMembers;
        this.competitionList = competitionList;
        this.catchList = catchList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(Integer numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public List<Competition> getCompetitionList() {
        return competitionList;
    }

    public void setCompetitionList(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }

    public List<Catches> getCatchList() {
        return catchList;
    }

    public void setCatchList(List<Catches> catchList) {
        this.catchList = catchList;
    }


}
