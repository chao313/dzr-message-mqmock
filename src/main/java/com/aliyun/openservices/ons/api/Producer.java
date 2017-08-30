package com.aliyun.openservices.ons.api;

public interface Producer {
    void start();

    void shutdown();

    SendResult send(Message var1);

    void sendOneway(Message var1);

    void sendAsync(Message var1, SendCallback var2);
}
