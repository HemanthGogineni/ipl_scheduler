package com.ipl.scheduler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ipl.scheduler.models.Team;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class TeamAddRequest {

    private List<Team> teamList;
}
