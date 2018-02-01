import com.wqzhang.bean.PersonInfo;
import com.wqzhang.producer.QueueMessageProducer;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

/**
 * @author wqzhang
 * @version 1.0.0
 * @ClassName MessageProducerTest
 * @Description ${todo}
 * @Date 2018/1/26 14:25
 */

@ContextConfiguration(locations = "classpath:/activemq-queue.xml")
public class MessageProducerTest {

    @Resource(name = "messageProducer")
    QueueMessageProducer producer;

    @Test
    public void sendMessageTest() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setAddress("nanjing");
        personInfo.setPwd("Ypovbn0");
        personInfo.setUserName("wqzhang");
        producer.sendMessage(personInfo);

    }

}
