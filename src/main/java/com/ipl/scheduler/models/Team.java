package com.ipl.scheduler.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="teams")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", unique = true)
    private long id;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "home_location")
    private String homeLocation;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "team_matches",
            joinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "team_id")},
            inverseJoinColumns = {@JoinColumn(name = "match_id", referencedColumnName = "match_id")}
    )
    @JsonIgnore
    private Set<IPLMatch> matches = new HashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<Player> players;
}
