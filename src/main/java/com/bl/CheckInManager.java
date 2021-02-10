package com.bl;

import com.dal.CheckInRequest;
import com.dal.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CheckInManager {

    private static final Logger logger = LoggerFactory.getLogger(CheckInManager.class);


    private DBManager dbManager;

    @Autowired
    public CheckInManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean checkin(CheckInRequest checkInRequest) {
        logger.debug("Start check in for: {}", checkInRequest);
        Set<String> baggages = dbManager.getBaggages(checkInRequest.getDestinationId());
        if (null != baggages && baggages.contains(checkInRequest.getBaggageId())) {
            logger.debug("Baggage [{}] was already checked in for destination [{}]", checkInRequest.getBaggageId(), checkInRequest.getDestinationId());
            return false;
        }
        logger.debug("Add new check in for: {}", checkInRequest);
        dbManager.checkinBaggage(checkInRequest);
        logger.debug("Add new check in for: [{}] succeeded.", checkInRequest);
        return true;
    }
}
