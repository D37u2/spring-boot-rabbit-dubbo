package com.wan.comm.common;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.workQueueName}")
    private String workQueueName;

    @Value("${spring.rabbitmq.workRoutingKey}")
    private String workRoutingKey;

    @Value("${spring.rabbitmq.workExchange}")
    private String workExchange;

    @Value("${spring.rabbitmq.delayQueueName}")
    private String delayQueueName;

    @Value("${spring.rabbitmq.delayRoutingKey}")
    private String delayRoutingKey;

    @Value("${spring.rabbitmq.delayExchange}")
    private String delayExchange;

    @Value("${spring.rabbitmq.errorQueueName}")
    private String errorQueueName;

    @Value("${spring.rabbitmq.errorRoutingKey}")
    private String errorRoutingKey;

    @Value("${spring.rabbitmq.errorExchange}")
    private String errorExchange;

    /** 并发消费数量 */
    @Value("${spring.rabbitmq.concurrency}")
    private Integer consumeConcurrency;

    /** 队列超时时间 */
    @Value("${spring.rabbitmq.connection.timeout}")
    private Integer connectionTimeout;

    /** 设置线程核心线程数 **/
    @Value("${spring.rabbitmq.connection.min_threads}")
    private Integer connectionMinThreads;

    /** 设置线程最大线程数 **/
    @Value("${spring.rabbitmq.connection.max_threads}")
    private Integer connectionMaxThreads;

    /** 线程池所使用的缓冲队列 */
    @Value("${spring.rabbitmq.connection.max_queued}")
    private Integer connectionMaxQueued;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    /** 线程名称前缀 */
    private static final String CONNECTION_THREAD_PREFIX = "rabbitmq-connection-thread";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * rabbit 连接创建
     * @return
     */
    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setConnectionTimeout(connectionTimeout);

        //线程池设置
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(CONNECTION_THREAD_PREFIX);
        executor.setCorePoolSize(connectionMinThreads);
        executor.setMaxPoolSize(connectionMaxThreads);
        executor.setQueueCapacity(connectionMaxQueued);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        connectionFactory.setExecutor(executor);
        return connectionFactory;
    }

    /**
     * 设置发送队列时的数据格式，默认编码为UTF-8
     * @return
     */
    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 设置发送队列时的数据格式
     * @return
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    /**
     * 工作交换器
     * @returns
     */
    @Bean
    DirectExchange workExchange(){
        return new DirectExchange(workExchange);
    }

    /**
     * 失败交换器
     * @return
     */
    @Bean
    DirectExchange errorExchange(){return new DirectExchange(errorExchange);}

    /**
     * 延迟交换器
     * @return
     */
    @Bean
    DirectExchange delayExchange(){return new DirectExchange(delayExchange);}


    /**
     * 工作队列，默认正常情况下从该队列消费消息
     *
     * @return
     */
    @Bean
    Queue workQueue() {
        return QueueBuilder.durable(workQueueName).build();
    }

    /**
     * 失败队列，消息消费失败则入该队列
     *
     * @return
     */
    @Bean
    Queue errorQueue() {
        return QueueBuilder.durable(errorQueueName).build();
    }

    /**
     * 延迟列队
     * @return
     */
    @Bean
    Queue delayQueue() {
        //配置该队列中消息多久以后进入workQueue队列
        return QueueBuilder.durable(delayQueueName)
                .withArgument("x-dead-letter-exchange",workExchange)
                .withArgument("x-dead-letter-routing-key",workRoutingKey)
                //设置消息死亡时间，单位毫秒
                .withArgument("x-message-ttl",10000).build();
    }

    /**
     * 队列与交换器绑定
     * @param workQueue
     * @param workExchange
     * @return
     */
    @Bean
    Binding workQueuqBindingExchange(Queue workQueue,DirectExchange workExchange){
        return BindingBuilder.bind(workQueue).to(workExchange).with(workRoutingKey);
    }

    @Bean
    Binding errorQueuqBindingExchange(Queue errorQueue,DirectExchange errorExchange){
        return BindingBuilder.bind(errorQueue).to(errorExchange).with(errorRoutingKey);
    }

    @Bean
    Binding delayQueuqBindingExchange(Queue delayQueue,DirectExchange delayExchange){
        return BindingBuilder.bind(delayQueue).to(delayExchange).with(delayRoutingKey);
    }

    @Bean
    MessageListenerContainer workMessageListenerContainer(ConnectionFactory connectionFactory, WorkRabbitMQConsumer workRabbitMQConsumer) {
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory);

        //设置队列名
        messageListenerContainer.setQueueNames(workQueueName);

        //设置监听类
        messageListenerContainer.setMessageListener(new MessageListenerAdapter(workRabbitMQConsumer));
        // 并发消费信息的数量
        messageListenerContainer.setConcurrentConsumers(consumeConcurrency);
        //设置ack
        messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        //设置返回格式 在发送convertAndSend 中会将Obj转换成jsonString，默认编码为UTF-8
        //messageListenerContainer.setMessageConverter(new Jackson2JsonMessageConverter());
        return messageListenerContainer;
    }

}
