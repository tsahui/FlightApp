package com;

import com.bl.CheckInManager;
import com.bl.CouponManager;
import com.bl.Exception.InvalidCouponException;
import com.bl.TicketManager;
import com.dal.CheckInRequest;
import com.dal.Coupon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FlightApplication.class)
@WebAppConfiguration
public class SystemTest {

    @Autowired
    private TicketManager ticketManager;

    @Autowired
    private CouponManager couponManager;

    @Autowired
    private CheckInManager checkInManager;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetExistingTicket(){
        UUID ticketId = UUID.fromString("284b7e43-580c-4fe2-9f93-7422d177be49");

        boolean exist = ticketManager.getTicketStatus(ticketId);

        Assert.assertTrue(exist);
    }

    @Test
    public void testGetNonExistingTicket(){
        UUID ticketId = UUID.fromString("71f07a99-02b2-4e77-a93b-a9751c26b5c6");

        boolean exist = ticketManager.getTicketStatus(ticketId);

        Assert.assertFalse(exist);
    }


    @Test
    public void testGoodCheckin(){
        CheckInRequest request = new CheckInRequest(UUID.fromString("71f07a99-02b2-4e77-a93b-a9751c26b5c7"), "zsdfzfddzsfgdsfszre5t4fzsd");

        boolean exist = checkInManager.checkin(request);

        Assert.assertTrue(exist);
    }

    @Test
    public void testExistingCheckin(){
        CheckInRequest request = new CheckInRequest(UUID.fromString("71f07a99-02b2-4e77-a93b-a9751c26b5c7"), "zsdfzfddzsfgdsfszre5t4fzsd");

        boolean existing = checkInManager.checkin(request);

        Assert.assertFalse(existing);
    }

    @Test
    public void testValidCoupon() throws InvalidCouponException {
        Coupon coupon = new Coupon(UUID.fromString("0b70bbd7-02dc-4e2a-8484-98238fc349b4"), 10.8);

        double price = couponManager.useCoupon(coupon);

        Assert.assertTrue(price < 10.8 );
    }

    @Test
    public void testNonValidCoupon() {
        Coupon coupon = new Coupon(UUID.fromString("0b70bbd7-02dc-4e2a-8484-98238fc349b5"), 10.8);

        try {
            double price = couponManager.useCoupon(coupon);
        } catch (InvalidCouponException e) {
            Assert.assertTrue(true);
            return;
        }
        Assert.fail();
    }

}
