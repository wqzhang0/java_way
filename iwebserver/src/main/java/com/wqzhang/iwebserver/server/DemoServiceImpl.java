package com.wqzhang.iwebserver.server;

import java.util.Date;

public class DemoServiceImpl implements DemoService {
    @Override
    public String getName(String name) {
        return "hello :" + name + new Date() + ")";
    }
}
