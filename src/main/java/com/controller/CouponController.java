package com.controller;

import com.bl.CouponManager;
import com.bl.Exception.InvalidCouponException;
import com.dal.Coupon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/coupon" )
@EnableAutoConfiguration
public class CouponController {

    private static final Logger logger = LoggerFactory.getLogger(CouponController.class);

    private CouponManager couponManager;

    @Autowired
    public CouponController(CouponManager couponManager) {
        this.couponManager = couponManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object useCoupon(@RequestBody Coupon coupon) {//throws InvalidCouponException {
        try {
            return couponManager.useCoupon(coupon);
        } catch (InvalidCouponException e) {
            return new ResponseEntity<>(coupon.getPrice(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
