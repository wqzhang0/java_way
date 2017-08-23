package com.wqzhang;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by com.wqzhang on 2017/7/6.
 */
@Component("boss")
public class Boss {


    @Autowired
    private BookingService bookingService;

    public void setBookingService(BookingService bookingService) {
        System.out.println("Boss:setBookingService");
        this.bookingService = bookingService;
    }

    public Boss() {
        System.out.println("Boss Init");
//        this.bookingService = new QunarBookingService();
    }

    @Test
    public void bookFlight(boolean rt) {
        bookingService.bookFlight(rt);
    }


}

