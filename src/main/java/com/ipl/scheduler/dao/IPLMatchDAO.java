package com.ipl.scheduler.dao;

import com.ipl.scheduler.models.IPLMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface IPLMatchDAO extends JpaRepository<IPLMatch, Long> {
}
