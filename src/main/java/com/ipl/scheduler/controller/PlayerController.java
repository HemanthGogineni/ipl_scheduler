package com.ipl.scheduler.controller;

import com.ipl.scheduler.dao.PlayerDAO;
import com.ipl.scheduler.models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    PlayerDAO playerDAO;

    @RequestMapping("/add-player")
    List<Player> addPlayer(@RequestBody List<Player> players) {

        playerDAO.saveAll(players);
        return playerDAO.findAll();
    }
}
