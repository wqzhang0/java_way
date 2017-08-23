package com.wqzhang;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by com.wqzhang on 2017/7/6.
 */
@Component("qunarBookingService")
public class QunarBookingService implements BookingService {

    public QunarBookingService() {
        System.out.println("QunarBookingService init");
    }

    public boolean bookFlight(boolean rt) {
        System.out.println("Boss book flight ");
        return rt;
    }
}
