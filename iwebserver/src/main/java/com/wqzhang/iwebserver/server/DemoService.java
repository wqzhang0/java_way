
package com.wqzhang.iwebserver.server;

import javax.jws.WebService;
import java.util.Date;


@WebService
public interface DemoService {

    String getName(String name);
}
