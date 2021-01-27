package com.ipl.scheduler.dao;

import com.ipl.scheduler.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TeamDAO extends JpaRepository<Team, Long> {
}
