package com.ipl.scheduler.controller;

import com.ipl.scheduler.dao.TeamDAO;
import com.ipl.scheduler.dto.ScheduleRequest;
import com.ipl.scheduler.dto.TeamAdditionRequest;
import com.ipl.scheduler.models.IPLMatch;
import com.ipl.scheduler.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Controller
public class ScheduleController {

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

        Long[] teamIds = scheduleRequest.getTeamIdList().toArray(new Long[0]);
        List<IPLMatch> matches = new ArrayList<>();
        long teamIdsLength = teamIds.length;
        for (long repeat = 1; repeat <= scheduleRequest.getRepetitionCount(); repeat++) {
            for (int i = 0; i < teamIdsLength; i++) {
                LocalDate date = scheduleRequest.getStartDate();
                for (int j = i + 1; j < teamIdsLength; j++) {
                    generateMatch(date.plusDays(i), teamIds[i], teamIds[j], matches, repeat);
                    date = date.plusDays(teamIdsLength - j);
                }
            }
        }
        return matches;
    }

    private void generateMatch(LocalDate date, Long teamAId, Long teamBId, List<IPLMatch> matches, long repeat) {

        Optional<Team> teamA = teamDAO.findById(teamAId);
        Optional<Team> teamB = teamDAO.findById(teamBId);
        IPLMatch newMatch = new IPLMatch();
        newMatch.setMatchDate(date);

        if (repeat % 2 != 0) {
            teamA.ifPresent(newMatch::setTeamA);
            teamB.ifPresent(newMatch::setTeamB);
            teamA.ifPresent(team -> newMatch.setLocation(team.getHomeLocation()));
        } else {
            teamA.ifPresent(newMatch::setTeamB);
            teamB.ifPresent(newMatch::setTeamA);
            teamB.ifPresent(team -> newMatch.setLocation(team.getHomeLocation()));
        }
        matches.add(newMatch);
    }


}
