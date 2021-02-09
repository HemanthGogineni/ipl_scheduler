package com.ipl.scheduler.controller;

import com.ipl.scheduler.dto.ScheduleRequest;
import com.ipl.scheduler.models.IPLMatch;
import com.ipl.scheduler.service.CreateScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    CreateScheduleService createScheduleService;

    @PostMapping("/create-schedule")
    public List<IPLMatch> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        return createScheduleService.createIPLLeagueSchedule(scheduleRequest);
    }
}
