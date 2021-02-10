package com.bl;

import com.bl.Exception.InvalidCouponException;
import com.dal.Coupon;
import com.dal.DBManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class CouponManager {

    private static final Logger logger = LoggerFactory.getLogger(CouponManager.class);

    private static final List<Double> DISCOUNTS = List.of(0.1, 0.5, 0.6);

    private DBManager dbManager;

    @Autowired
    public CouponManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public double useCoupon(Coupon coupon) throws InvalidCouponException {
        logger.debug("Using coupon [{}]", coupon);
        if (!couponIsValid(coupon.getCouponId())){
            logger.error("User attempted to use an invalid coupon [{}]", coupon);
            throw new InvalidCouponException(coupon.getPrice());
        }
        double discount = getDiscountForCoupon();
        logger.debug("User used a valid coupon, got {}% discount", discount*100);
        return coupon.getPrice() * discount;
    }

    private boolean couponIsValid(UUID couponId) {
        return dbManager.isCouponExist(couponId);
    }

    private double getDiscountForCoupon() {
        Random random = new Random();
        return 1 - DISCOUNTS.get(random.nextInt(3));
    }
}
