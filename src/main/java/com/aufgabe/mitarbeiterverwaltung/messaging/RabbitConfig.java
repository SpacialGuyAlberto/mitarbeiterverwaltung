package com.aufgabe.mitarbeiterverwaltung.messaging;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "employee.exchange";
    public static final String QUEUE_EMP_CREATED = "employee.created.queue";
    public static final String RK_EMP_CREATED = "employee.created";
    public static final String RK_EMP_UPDATED = "employee.updated";
    public static final String RK_EMP_DELETED = "employee.deleted";
    public static final String QUEUE_EMP_DELETED = "employee.deleted.queue";
    public static final String QUEUE_EMP_UPDATED = "employee.updated.queue";

    @Bean
    public DirectExchange employeeExchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public Queue employeeCreatedQueue() {
        return QueueBuilder.durable(QUEUE_EMP_CREATED).build();
    }

    @Bean
    public Queue employeeDeletedQueue() {
        return QueueBuilder.durable(QUEUE_EMP_DELETED).build();
    }

    @Bean
    public Queue employeeUpdatedQueue() {
        return QueueBuilder.durable(QUEUE_EMP_UPDATED).build();
    }

    @Bean
    public Binding employeeCreatedBinding(Queue employeeCreatedQueue, DirectExchange employeeExchange) {
        return BindingBuilder.bind(employeeCreatedQueue).to(employeeExchange).with(RK_EMP_CREATED);
    }

    @Bean
    public Binding employeeUpdatedBinding(Queue employeeUpdatedQueue, DirectExchange employeeExchange) {
        return BindingBuilder.bind(employeeUpdatedQueue).to(employeeExchange).with(RK_EMP_UPDATED);
    }

    @Bean
    public Binding employeeDeletedBinding(Queue employeeDeletedQueue, DirectExchange employeeExchange) {
        return BindingBuilder.bind(employeeDeletedQueue).to(employeeExchange).with(RK_EMP_DELETED);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory cf, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(cf);
        template.setMessageConverter(converter);
        return template;
    }

}
