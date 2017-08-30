package com.dzr.mqmock.test.blog;


import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.junit.Test;

public class Producer {
    @Test
    public void produce() {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();

            Message msg = new Message("TOPIC_AUTH",
                    "push",
                    "1",
                    "Just for test.".getBytes());

            SendResult result = producer.send(msg);

            System.out.println("id:" + result.getMsgId() +
                    " result:" + result.getSendStatus());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }
}

