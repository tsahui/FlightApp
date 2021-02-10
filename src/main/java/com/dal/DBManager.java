package com.dal;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DBManager {

    private ConcurrentHashMap<UUID, Ticket> tickets = new ConcurrentHashMap<>();
    private ConcurrentHashMap<UUID, Set<String>> checkIns = new ConcurrentHashMap<>();
    private Set<UUID> coupons = Set.of(UUID.fromString("99b7518a-dcc5-43f3-b676-148cb38f6974"),
            UUID.fromString("dd175a81-3cbe-4187-86db-92ab84608cd7"),
            UUID.fromString("2eddfed2-9810-4cdd-bcd5-d0fbd0cb0f94"),
            UUID.fromString("0b70bbd7-02dc-4e2a-8484-98238fc349b4"));


    public Ticket getTicket(UUID uuid) {
        return tickets.get(uuid);
    }

    public Set<String> getBaggages(UUID destinationId) {
        return checkIns.get(destinationId);
    }

    public void checkinBaggage(CheckInRequest checkInRequest) {
        if (checkIns.containsKey(checkInRequest.getDestinationId())){
            checkIns.get(checkInRequest.getDestinationId()).add(checkInRequest.getBaggageId());
        } else {
            checkIns.put(checkInRequest.getDestinationId(), Collections.singleton(checkInRequest.getBaggageId()));
        }
    }

    public boolean isCouponExist(UUID couponId) {
        return coupons.contains(couponId);
    }
}
