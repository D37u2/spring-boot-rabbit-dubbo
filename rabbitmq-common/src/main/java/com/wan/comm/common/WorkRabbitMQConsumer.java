package com.wan.comm.common;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkRabbitMQConsumer implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        String jsonString = new String(message.getBody(),"UTF-8");
        log.info("Received <" + jsonString + ">");
    }

}
