package com.aliyun.openservices.ons.api;

public interface MessageListener {
    Action consume(Message message, ConsumeContext context);
}
