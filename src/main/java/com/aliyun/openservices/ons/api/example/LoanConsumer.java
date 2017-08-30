package com.aliyun.openservices.ons.api.example;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoanConsumer implements MessageListener {
    private Logger log = LoggerFactory.getLogger(LoanConsumer.class);
    @Override
    public Action consume(Message message, ConsumeContext context) {
        log.info(message.toString());
        return Action.CommitMessage;
    }
}
