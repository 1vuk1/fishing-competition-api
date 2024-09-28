package com.vuk.fishingcompetition.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Competition")
public class Competition {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "type")
    private String type;

    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    @JoinTable(name = "participation",joinColumns = @JoinColumn(name = "competition_id"),inverseJoinColumns = @JoinColumn(name = "team_id"))
    private List<Team> teamList;

    @OneToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Catches> catchList;
    @Transient
    private double heaviestCatchWeight;
    @Transient
    private String heaviestCatchTeamName;

    @Transient
    private Integer numberOfCatches;

    @Transient
    private Double averageWeight;

    public Competition() {
    }

    public Competition(String name, LocalDate date, String type, List<Team> teamList, double heaviestCatchWeight, String heaviestCatchTeamName) {
        this.name = name;
        this.date = date;
        this.type = type;
        this.teamList = teamList;
        this.heaviestCatchWeight = heaviestCatchWeight;
        this.heaviestCatchTeamName = heaviestCatchTeamName;
    }

    public List<Catches> getCatchList() {
        return catchList;
    }

    public void setCatchList(List<Catches> catchList) {
        this.catchList = catchList;
    }

    public void setNumberOfCatches(Integer numberOfCatches) {
        this.numberOfCatches = numberOfCatches;
    }

    public Double getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(Double averageWeight) {
        this.averageWeight = averageWeight;
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

    public LocalDate getDate() {
        return date;
    }

    public Integer getNumberOfCatches() {
        return numberOfCatches;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public double getHeaviestCatchWeight() {
        return heaviestCatchWeight;
    }

    public void setHeaviestCatchWeight(double heaviestCatchWeight) {
        this.heaviestCatchWeight = heaviestCatchWeight;
    }

    public String getHeaviestCatchTeamName() {
        return heaviestCatchTeamName;
    }

    public void setHeaviestCatchTeamName(String heaviestCatchTeamName) {
        this.heaviestCatchTeamName = heaviestCatchTeamName;
    }


}
