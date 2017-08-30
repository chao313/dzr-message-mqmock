package com.aliyun.openservices.ons.api;


public class Message {
    private com.alibaba.rocketmq.common.message.Message msg;

    public Message(String topic, String tags, byte[] body) {
        msg = new com.alibaba.rocketmq.common.message.Message(topic, tags, body);
    }

    public Message(com.alibaba.rocketmq.common.message.Message msg) {
        this.msg = msg;
    }

    public com.alibaba.rocketmq.common.message.Message getMessage(){
        return msg;
    }

    public String getTopic() {
        return msg.getTopic();
    }

    public String getTag() {
        return msg.getTags();
    }

    public String getKey() {
        return msg.getKeys();
    }

    public void setKey(String key) {
        msg.setKeys(key);
    }

    public String getMsgID() {
        if( msg instanceof  com.alibaba.rocketmq.common.message.MessageExt) {
            return ((com.alibaba.rocketmq.common.message.MessageExt)msg).getMsgId();
        }
        return "";
    }

    public void setMsgID(String msgID) {
        if( msg instanceof  com.alibaba.rocketmq.common.message.MessageExt) {
            ((com.alibaba.rocketmq.common.message.MessageExt)msg).setMsgId(msgID);
        }
    }


    public int getReconsumeTimes() {
        if( msg instanceof  com.alibaba.rocketmq.common.message.MessageExt) {
            return ((com.alibaba.rocketmq.common.message.MessageExt)msg).getReconsumeTimes();
        }

        return 0;
    }



    public byte[] getBody() {
        return msg.getBody();
    }

    public String toString() {
        return msg==null?"null":msg.toString();
    }


}
