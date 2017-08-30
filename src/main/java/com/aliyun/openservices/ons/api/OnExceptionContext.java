package com.aliyun.openservices.ons.api;

import com.aliyun.openservices.ons.api.exception.ONSClientException;

public class OnExceptionContext {
    private String messageId;
    private String topic;
    private ONSClientException exception;

    public OnExceptionContext() {
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

    public ONSClientException getException() {
        return this.exception;
    }

    public void setException(ONSClientException exception) {
        this.exception = exception;
    }
}
