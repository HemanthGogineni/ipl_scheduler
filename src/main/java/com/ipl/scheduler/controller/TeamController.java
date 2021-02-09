package com.ipl.scheduler.controller;

import com.ipl.scheduler.dao.PlayerDAO;
import com.ipl.scheduler.dao.TeamDAO;
import com.ipl.scheduler.dto.TeamAdditionRequest;
import com.ipl.scheduler.models.IPLMatch;
import com.ipl.scheduler.models.Player;
import com.ipl.scheduler.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    TeamDAO teamDAO;

    @Autowired
    PlayerDAO playerDAO;

    @PostMapping("/add-team")
    List<Team> addTeams(@RequestBody TeamAdditionRequest teamAdditionRequest) {
        return teamDAO.saveAll(teamAdditionRequest.getTeams());
    }

    @GetMapping("/{teamName}/get-matches")
    Set<IPLMatch> getMatchesForTeam(@PathVariable String teamName) {
        Team team = teamDAO.findByTeamName(teamName);
        return team.getMatches();
    }

    @PostMapping("/{teamName}/addPlayers")
    List<Team> addPlayers(@PathVariable String teamName, @RequestBody List<Long> playerIds) {
        Team team = teamDAO.findByTeamName(teamName);
        Set<Player> players = new HashSet<>();
        playerIds.forEach(playerId -> {
            Optional<Player> player = playerDAO.findById(playerId);
            player.ifPresent(players::add);
        });
        team.setPlayers(players);
        teamDAO.save(team);

        Optional<Player> player = playerDAO.findById(3L);
        return teamDAO.findAll();

    }

}
