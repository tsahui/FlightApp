package com.dal;

import java.util.UUID;

public class Coupon {
    private UUID couponId;
    private double price;

    public Coupon() {
    }

    public Coupon(UUID couponId, double price) {
        this.couponId = couponId;
        this.price = price;
    }

    public UUID getCouponId() {
        return couponId;
    }

    public void setCouponId(UUID couponId) {
        this.couponId = couponId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponId=" + couponId +
                ", price=" + price +
                '}';
    }
}
