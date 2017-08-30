package com.aliyun.openservices.ons.api.bean;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.aliyun.openservices.ons.api.ConsumerListener;
import com.aliyun.openservices.ons.api.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ConsumerBean implements Consumer {
    private Logger logger = LoggerFactory.getLogger(ConsumerBean.class);
    private Properties properties;
    private Map<Subscription, MessageListener> subscriptionTable;
    private DefaultMQPushConsumer consumer;

    public ConsumerBean() {

    }

    @Override
    public void start() {
        if (null == this.properties) {
            throw new RuntimeException("properties not set");
        } else if (null == this.subscriptionTable) {
            throw new RuntimeException("subscriptionTable not set");
        } else {
            try {
                consumer = new DefaultMQPushConsumer(properties.getProperty("ConsumerId"));
                consumer.setNamesrvAddr(properties.getProperty("ONSAddr"));
                consumer.setMessageModel(MessageModel.CLUSTERING); //集群模式

                int threadNums = 5;
                if(properties.containsKey("ConsumeThreadNums")) {
                    threadNums = Integer.valueOf(properties.getProperty("ConsumeThreadNums"));
                }
                consumer.setConsumeThreadMin(threadNums);
                consumer.setConsumeThreadMax(threadNums);

                //一次只消费1条消息
                consumer.setConsumeMessageBatchMaxSize(1);
                //TODO 参数优化
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

                Iterator<Map.Entry<Subscription, MessageListener>> it = this.subscriptionTable.entrySet().iterator();
                Map<String, MessageListener> topicListeners = new HashMap<>();
                ConsumerListener consumerListener = new ConsumerListener();
                consumer.registerMessageListener(consumerListener);

                while (it.hasNext()) {
                    Map.Entry<Subscription, MessageListener> next = it.next();
                    Subscription subscription = next.getKey();
                    consumer.subscribe(subscription.getTopic(), subscription.getExpression());
                    topicListeners.put(subscription.getTopic(), next.getValue());
                }

                consumerListener.setTopicListeners(topicListeners);
                this.consumer.start();
                logger.info("启动消息队列消费者成功");

            } catch (Exception e) {
                throw new RuntimeException("启动消息队列Consumer出错", e);
            }

        }
    }


    @Override
    public void shutdown() {
        logger.info("准备关闭消息队列消费者");
        if (consumer != null) consumer.shutdown();
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setSubscriptionTable(Map<Subscription, MessageListener> subscriptionTable) {
        this.subscriptionTable = subscriptionTable;
    }
}
