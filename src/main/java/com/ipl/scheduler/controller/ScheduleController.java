package com.ipl.scheduler.controller;

import com.ipl.scheduler.dao.TeamDAO;
import com.ipl.scheduler.dto.ScheduleRequest;
import com.ipl.scheduler.dto.TeamAdditionRequest;
import com.ipl.scheduler.models.IPLMatch;
import com.ipl.scheduler.models.Team;
import com.ipl.scheduler.service.CreateScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ScheduleController {

    @Autowired
    CreateScheduleService createScheduleService;

    @Autowired
    TeamDAO teamDAO;

    @PostMapping("/add-teams")
    public @ResponseBody
    List<Team> addTeams(@RequestBody TeamAdditionRequest teamAdditionRequest) {
        return teamDAO.saveAll(teamAdditionRequest.getTeams());
    }

    @PostMapping("/create-schedule")
    public @ResponseBody
    List<IPLMatch> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        return createScheduleService.createIPLLeagueSchedule(scheduleRequest);
    }

}
