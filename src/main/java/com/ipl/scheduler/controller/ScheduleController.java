package com.ipl.scheduler.controller;

import com.ipl.scheduler.dao.TeamDAO;
import com.ipl.scheduler.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@Controller
public class ScheduleController {

    @Autowired
    TeamDAO teamDAO;

    @PostMapping("/add-teams")
    public @ResponseBody List<Team> addTeams (@RequestBody List<Team> inputList) {

        List<Team> teams = inputList;
        teams.forEach(team -> {
            teamDAO.save(team);
        });
        return teamDAO.findAll();

    }


}
