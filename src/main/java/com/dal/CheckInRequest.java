package com.dal;

import java.util.UUID;

public class CheckInRequest {
    private UUID destinationId;
    private String baggageId;

    public CheckInRequest() {
    }

    public CheckInRequest(UUID destinationId, String baggageId) {
        this.destinationId = destinationId;
        this.baggageId = baggageId;
    }

    public UUID getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(UUID destinationId) {
        this.destinationId = destinationId;
    }

    public String getBaggageId() {
        return baggageId;
    }

    public void setBaggageId(String baggageId) {
        this.baggageId = baggageId;
    }

    @Override
    public String toString() {
        return "CheckInRequest{" +
                "destinationId=" + destinationId +
                ", baggageId='" + baggageId + '\'' +
                '}';
    }
}
