package com.aliyun.openservices.ons.api.bean;

public class ProducerException extends RuntimeException {
    public ProducerException(String msg) {
        super(msg);
    }

    public ProducerException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
