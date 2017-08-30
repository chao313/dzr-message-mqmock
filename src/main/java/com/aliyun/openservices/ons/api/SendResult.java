package com.aliyun.openservices.ons.api;

public class SendResult {
    private String messageId;
    private String topic;
    private com.alibaba.rocketmq.client.producer.SendResult sendResult;

    public SendResult(String messageId, String topic, com.alibaba.rocketmq.client.producer.SendResult sendResult) {
        this.messageId = messageId;
        this.topic = topic;
        this.sendResult = sendResult;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String toString() {
        if(sendResult!=null) return sendResult.toString();
        return "SendResult[topic=" + this.topic + ", messageId=" + this.messageId + ']';
    }


}
