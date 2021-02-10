package com.bl.Exception;

//@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="Invalid Coupon")
public class InvalidCouponException extends Exception {
    private double price;

    public InvalidCouponException(double price) {
        this.price = price;
    }
}
