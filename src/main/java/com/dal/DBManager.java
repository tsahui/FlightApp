package com.dal;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DBManager {

    private ConcurrentHashMap<UUID, Set<String>> checkIns = new ConcurrentHashMap<>();

    private Set<UUID> tickets = Set.of(
            UUID.fromString("284b7e43-580c-4fe2-9f93-7422d177be49"),
            UUID.fromString("71f07a99-02b2-4e77-a93b-a9751c26b5c8"));

    private Set<UUID> coupons = Set.of(
            UUID.fromString("99b7518a-dcc5-43f3-b676-148cb38f6974"),
            UUID.fromString("dd175a81-3cbe-4187-86db-92ab84608cd7"),
            UUID.fromString("2eddfed2-9810-4cdd-bcd5-d0fbd0cb0f94"),
            UUID.fromString("0b70bbd7-02dc-4e2a-8484-98238fc349b4"));


    public boolean isTicketExists(UUID uuid) {
        return tickets.contains(uuid);
    }

    public Set<String> getBaggages(UUID destinationId) {
        return checkIns.get(destinationId);
    }

    public void checkinBaggage(CheckInRequest checkInRequest) {
        if (checkIns.containsKey(checkInRequest.getDestinationId())){
            checkIns.get(checkInRequest.getDestinationId()).add(checkInRequest.getBaggageId());
        } else {
            Set<String> newSet = new HashSet<String>();
            newSet.add(checkInRequest.getBaggageId());
            checkIns.put(checkInRequest.getDestinationId(), newSet);
        }
    }

    public boolean isCouponExist(UUID couponId) {
        return coupons.contains(couponId);
    }
}
