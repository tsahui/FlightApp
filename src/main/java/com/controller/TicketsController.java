package com.controller;

import com.bl.TicketManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/ticket" )
@EnableAutoConfiguration
public class TicketsController {

    private static final Logger logger = LoggerFactory.getLogger(TicketsController.class);

    private TicketManager ticketManager;

    @Autowired
    public TicketsController(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    public boolean getTicketStatus(@PathVariable UUID uuid){

        return ticketManager.getTicketStatus(uuid);
    }
}
