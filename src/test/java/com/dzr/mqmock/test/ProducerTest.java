
package com.dzr.mqmock.test;

import com.aliyun.openservices.ons.api.*;
import org.junit.Test;

import java.util.Properties;

public class ProducerTest extends BaseTest {
    private Producer producer;

    @Test
    public void testSend() {
        Properties properties = new Properties();
        properties.setProperty("ProducerId", "PID_COMP_AUTH");
        properties.setProperty("ONSAddr", "10.253.15.39:9876;10.253.14.218:9876");

        producer = ONSFactory.createProducer(properties);
        producer.start();

        for (int i = 1; i <= 3; i++)
            try {
                {
                    Message msg = null;

                        msg = new Message("TOPIC_AUTH",// topic
                                "TAG_LOAN_COMPLETED",// tag
                                ("Hello MetaQ").getBytes());

//                    else if (m == 1) {
//                        msg = new Message("TOPIC_AUTH",// topic
//                                "TAG_REPAY_DEDUCT_NORMAL",// tag
//                                ("Hello MetaQ").getBytes());
//                    } else if (m == 2) {
//                        msg = new Message("TOPIC_AUTH",// topic
//                                "TAG_REPAY_DEDUCT_OVERDUE",// tag
//                                ("Hello MetaQ").getBytes());
//                    }

                    producer.sendAsync(msg, new SendCallback() {
                        @Override
                        public void onSuccess(SendResult sendResult) {
                            System.out.println(sendResult);
                        }

                        @Override
                        public void onException(OnExceptionContext context) {

                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        try {
            Thread.sleep(999999999999L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}

