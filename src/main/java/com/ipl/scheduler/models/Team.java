package com.ipl.scheduler.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="teams")
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id", unique = true)
    private long id;

    @Column(name = "team_name")
    private String teamName;

    @Column
    private String homeLocation;
}
