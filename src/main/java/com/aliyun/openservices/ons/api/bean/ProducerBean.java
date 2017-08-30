package com.aliyun.openservices.ons.api.bean;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerBean implements Producer {
    private Logger log = LoggerFactory.getLogger(ProducerBean.class);
    private Properties properties;
    private DefaultMQProducer producer;

    public ProducerBean() {

    }

    public void start() {
        if (null == this.properties) {
            throw new RuntimeException("properties not set");
        } else {
            producer = new DefaultMQProducer(properties.getProperty("ProducerId"));
            producer.setNamesrvAddr(properties.getProperty("ONSAddr"));
            //TODO 参数优化
//            producer.setRetryTimesWhenSendAsyncFailed(0);
            try {
                producer.start();
                log.info("启动消息队列生产者成功, {}, {}", producer.getProducerGroup(), producer.getNamesrvAddr());
            } catch (MQClientException e) {
                log.error("启动消息队列生产者失败", e);
//                throw new RuntimeException("启动消息队列生产者失败", e);
            }
        }
    }

    public void shutdown() {
        if (this.producer != null) this.producer.shutdown();
    }

    @Override
    public SendResult send(Message message) {
        try {
            return new SendResult(message.getMsgID(), message.getTopic(), this.producer.send(message.getMessage()));
        } catch (Exception e) {
            throw new ProducerException("发送消息失败:", e);
        }
    }

    @Override
    public void sendOneway(Message var1) {

    }

    @Override
    public void sendAsync(Message message, SendCallback sendCallback) {
        try {
            producer.send(message.getMessage(), new com.alibaba.rocketmq.client.producer.SendCallback(){
                @Override
                public void onSuccess(com.alibaba.rocketmq.client.producer.SendResult sendResult) {
                    sendCallback.onSuccess(new SendResult(sendResult.getMsgId(), sendResult.getMessageQueue().getTopic(), sendResult));
                }

                @Override
                public void onException(Throwable throwable) {
                    OnExceptionContext context = new OnExceptionContext();
                    context.setException(new ONSClientException(throwable));
                    context.setTopic(message.getTopic());
                    sendCallback.onException(context);
                }
            });
        } catch (Exception e) {
            throw new ProducerException("发送消息失败:", e);
        }
    }



    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


}
