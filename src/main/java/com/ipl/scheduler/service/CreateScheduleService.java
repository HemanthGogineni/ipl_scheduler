package com.ipl.scheduler.service;

import com.ipl.scheduler.dto.ScheduleRequest;
import com.ipl.scheduler.models.IPLMatch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CreateScheduleService {

    public List<IPLMatch> createIPLLeagueSchedule(ScheduleRequest scheduleRequest);
}
