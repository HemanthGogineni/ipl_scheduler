package com.ipl.scheduler.service.impl;

import com.ipl.scheduler.dao.IPLMatchDAO;
import com.ipl.scheduler.dto.ScheduleRequest;
import com.ipl.scheduler.logic.CreateSchedulerLogic;
import com.ipl.scheduler.models.IPLMatch;
import com.ipl.scheduler.service.CreateScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateScheduleServiceImpl implements CreateScheduleService {

    @Autowired
    IPLMatchDAO iplMatchDAO;

    @Autowired
    CreateSchedulerLogic createSchedulerLogic;

    @Override
    public List<IPLMatch> createIPLLeagueSchedule(ScheduleRequest scheduleRequest) {

        List<IPLMatch> scheduledMatches = createSchedulerLogic.createMatches(scheduleRequest.getTeamIdList());
        createSchedulerLogic.addDateToMatches(scheduledMatches, scheduleRequest.getStartDate());
        iplMatchDAO.saveAll(scheduledMatches);

        return iplMatchDAO.findAll();
    }
}
