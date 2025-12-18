package com.aufgabe.mitarbeiterverwaltung.messaging;

import com.aufgabe.mitarbeiterverwaltung.messaging.Message;
import com.aufgabe.mitarbeiterverwaltung.employee.dto.EmployeeDto;
import com.aufgabe.mitarbeiterverwaltung.messaging.RabbitConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Slf4j
@Component
public class EmployeeCreatedConsumer {

    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitConfig.QUEUE_EMP_CREATED)
    public void onEmployeeCreated(Message<EmployeeDto> msg) {
        EmployeeDto dto = msg.getContent();

        log.info("""
                Employee created event received
                - status: {}
                - Employee:
                  - id: {}
                  - name: {}
                  - surname: {}
                  - hourlyWage: {}
                  - role: {}
                """,
                msg.getStatus(),
                dto != null ? dto.getId() : null,
                dto != null ? dto.getName() : null,
                dto != null ? dto.getSurname() : null,
                dto != null ? dto.getHourlyWage() : null,
                dto != null ? dto.getRole() : null
        );
    }
}


