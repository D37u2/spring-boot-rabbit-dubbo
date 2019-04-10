package com.wan.pub.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRabbitMQPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void pushNoticeToWX(String exchange,String routingKey, Object obj) {

        //此处需要制定交换器名称，若不制定交换器名称，会通过routingKey去匹配对应的queueName找到对应的队列，若在初始化rabbitTemplate时通过setExchange()指定了默认的交换器，可不传入交换器名称
        rabbitTemplate.convertAndSend(exchange,routingKey,obj);

    }

}
