package com.aliyun.openservices.ons.api;

public interface SendCallback{
    void onSuccess(SendResult sendResult);

    void onException(OnExceptionContext context);
}
