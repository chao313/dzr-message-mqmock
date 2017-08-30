package com.aliyun.openservices.ons.api;

import com.aliyun.openservices.ons.api.bean.ProducerBean;

import java.util.Properties;

public class ONSFactory {
    public static Producer createProducer(Properties properties) {
        ProducerBean prouducerBean = new ProducerBean();
        prouducerBean.setProperties(properties);
        return prouducerBean;
    }


}

