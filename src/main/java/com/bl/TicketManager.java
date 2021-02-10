package com.bl;

import com.dal.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketManager {

    private static final Logger logger = LoggerFactory.getLogger(TicketManager.class);

    private DBManager dbManager;

    @Autowired
    public TicketManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean getTicketStatus(UUID uuid) {

        logger.debug("getting ticket status for id: {}", uuid);
        return dbManager.isTicketExists(uuid);
    }

}
