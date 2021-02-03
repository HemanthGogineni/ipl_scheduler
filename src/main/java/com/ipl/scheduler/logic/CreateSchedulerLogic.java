package com.ipl.scheduler.logic;

import com.ipl.scheduler.dao.IPLMatchDAO;
import com.ipl.scheduler.dao.TeamDAO;
import com.ipl.scheduler.models.IPLMatch;
import com.ipl.scheduler.models.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import static com.ipl.scheduler.util.SchedulerUtil.leftShiftByOne;

@Component
public class CreateSchedulerLogic {

    @Autowired
    TeamDAO teamDAO;

    @Autowired
    IPLMatchDAO iplMatchDAO;

    public List<IPLMatch> createMatches(List<Long> teamIds) {

        List<IPLMatch> roundOneMatches = new ArrayList<>();
        List<IPLMatch> roundTwoMatches = new ArrayList<>();
        List<Long> first = new ArrayList<>(teamIds.subList(0, (teamIds.size()) / 2));
        List<Long> second = new ArrayList<>(teamIds.subList((teamIds.size()) / 2, teamIds.size()));
        for (int i = 0; i < teamIds.size() / 2; i++) {
            for (int j = 0; j < teamIds.size() / 2; j++) {
                generateMatch(first.get(j), second.get(j), roundOneMatches);
                generateMatch(second.get(j), first.get(j), roundTwoMatches);

            }
            leftShiftByOne(first);
        }
        for (int i = 0; i < teamIds.size() / 2; i++) {
            for (int j = i + 1; j < teamIds.size() / 2; j++) {
                generateMatch(first.get(i), first.get(j), roundOneMatches);
                generateMatch(second.get(i), second.get(j), roundOneMatches);
                generateMatch(first.get(j), first.get(i), roundTwoMatches);
                generateMatch(second.get(j), second.get(i), roundTwoMatches);
            }
        }
        roundOneMatches.addAll(roundTwoMatches);

        return roundOneMatches;
    }

    public List<IPLMatch> addDateToMatches(List<IPLMatch> matches, LocalDate date) {
        AtomicLong i = new AtomicLong();
        matches.forEach(roundOneMatch -> roundOneMatch.setMatchDate(date.plusDays(i.getAndIncrement())));

        return matches;

    }

    private void generateMatch(Long teamAId, Long teamBId, List<IPLMatch> matches) {

        IPLMatch newMatch = new IPLMatch();

        Optional<Team> teamA = teamDAO.findById(teamAId);
        Optional<Team> teamB = teamDAO.findById(teamBId);
        Set<Team> teams = new HashSet<>();
        teamA.ifPresent(teams::add);
        teamB.ifPresent(teams::add);
        teamA.ifPresent(team -> newMatch.setLocation(team.getHomeLocation()));
        newMatch.setTeams(teams);
        matches.add(newMatch);

    }

}
