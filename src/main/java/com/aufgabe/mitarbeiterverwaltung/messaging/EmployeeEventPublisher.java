package com.aufgabe.mitarbeiterverwaltung.messaging;

import com.aufgabe.mitarbeiterverwaltung.messaging.Message;
import com.aufgabe.mitarbeiterverwaltung.employee.dto.EmployeeDto;
import com.aufgabe.mitarbeiterverwaltung.messaging.RabbitConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void employeeCreated(EmployeeDto dto) {
        Message<EmployeeDto> payload = Message.of(dto);
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.RK_EMP_CREATED,
                payload
        );
    }

    public void employeeUpdated(EmployeeDto dto) {
        Message<EmployeeDto> payload = Message.of(dto);
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.RK_EMP_UPDATED,
                payload
        );
    }

    public void employeeDeleted(UUID id) {
        Message<UUID> payload = Message.of(id);
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.RK_EMP_DELETED,
                payload
        );
    }
}

