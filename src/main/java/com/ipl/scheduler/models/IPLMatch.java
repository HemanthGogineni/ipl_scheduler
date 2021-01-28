package com.ipl.scheduler.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "ipl_match")
@Getter
@Setter
public class IPLMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "match_id")
    private long id;

    @Column(name = "dateTime", columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate matchDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team teamA;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team teamB;

    @Column(name = "location")
    private String location;
}
