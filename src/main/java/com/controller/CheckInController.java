package com.controller;

import com.bl.CheckInManager;
import com.dal.CheckInRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/checkin" )
@EnableAutoConfiguration
public class CheckInController {

    private static final Logger logger = LoggerFactory.getLogger(CheckInController.class);

    private CheckInManager checkinManager;

    @Autowired
    public CheckInController(CheckInManager checkinManager) {
        this.checkinManager = checkinManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    public boolean checkin(@RequestBody CheckInRequest checkInRequest){
        return checkinManager.checkin(checkInRequest);
    }

}
