package com.aufgabe.mitarbeiterverwaltung.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Component
public class EmployeeDeletedConsumer {

    @RabbitListener(bindings = @QueueBinding(value = @org.springframework.amqp.rabbit.annotation.Queue(value = RabbitConfig.QUEUE_EMP_DELETED, durable = "true"), exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = RabbitConfig.EXCHANGE), key = RabbitConfig.RK_EMP_DELETED))
    public void onEmployeeDeleted(Message<UUID> msg) {
        UUID id = msg.getContent();

        log.info("""
                Employee deleted event received
                - status: {}
                - Employee ID: {}
                """,
                msg.getStatus(),
                id);
    }
}
