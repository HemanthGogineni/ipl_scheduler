package com.ipl.scheduler.dao;

import com.ipl.scheduler.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDAO extends JpaRepository<Player, Long> {
}
