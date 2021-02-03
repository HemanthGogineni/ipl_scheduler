package com.ipl.scheduler.dto;

import com.ipl.scheduler.models.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamAdditionRequest {


    private List<Team> teams;
}
