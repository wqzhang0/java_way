package com.wqzhang.iwebserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IwebserverApplicationTests {

//    @Test
//    public void contextLoads() {
//    }

    @Test
    public void contextLoads() {
        DemoServiceImplService webServiceImpl = new DemoServiceImplService();
        String result = webServiceImpl.getDemoServiceImplPort().getName("wqzhang");
        System.out.println("===========================================");
        System.out.println(result);
        System.out.println("===========================================");
    }

}
