package com.ipl.scheduler.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Set;

@Component
@Getter
@Setter
public class ScheduleRequest {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDate startDate;
    private Set<Long> teamIdList;
    private long repetitionCount;
}
