package com.ipl.scheduler.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "matches")
@Getter
@Setter
public class IPLMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private long id;

    @Column(name = "match_date", columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate matchDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "team_matches",
            joinColumns = {@JoinColumn(name = "match_id", referencedColumnName = "match_id")},
            inverseJoinColumns = {@JoinColumn(name = "team_id", referencedColumnName = "team_id")}
    )
    private Set<Team> teams = new HashSet<>();

    @Column(name = "location")
    private String location;
}
