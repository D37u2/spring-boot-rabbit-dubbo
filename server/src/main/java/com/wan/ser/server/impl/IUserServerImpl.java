package com.wan.ser.server.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.wan.ent.iserver.IUserServer;
import com.wan.pub.publisher.UserRabbitMQPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhoujiong
 * @description:
 * @className: IUserServerImpl
 * @date: 2019/4/8 15:18
 */
@Service(interfaceClass = IUserServer.class)
public class IUserServerImpl implements IUserServer {

    @Autowired
    private UserRabbitMQPublisher userRabbitMQPublisher;

    @Value("${spring.rabbitmq.workRoutingKey}")
    private String workRoutingKey;

    @Value("${spring.rabbitmq.workExchange}")
    private String workExchange;

    @Override
    public void pushNoticeToWX(String userId) {

        Map map = new HashMap(2);
        map.put("userName","周冏");
        map.put("phone","111111111");

        //调用userRabbitMQPublisher发送消息队列，通过路由键和交换器去匹配对应的队列
        userRabbitMQPublisher.pushNoticeToWX(workExchange,workRoutingKey,map);
    }
}
