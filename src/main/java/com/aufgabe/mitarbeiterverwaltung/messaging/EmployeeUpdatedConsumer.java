package com.aufgabe.mitarbeiterverwaltung.messaging;

import com.aufgabe.mitarbeiterverwaltung.employee.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Slf4j
@Component
public class EmployeeUpdatedConsumer {

  private final ObjectMapper objectMapper;

  @RabbitListener(bindings = @QueueBinding(value = @org.springframework.amqp.rabbit.annotation.Queue(value = RabbitConfig.QUEUE_EMP_UPDATED, durable = "true"), exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = RabbitConfig.EXCHANGE), key = RabbitConfig.RK_EMP_UPDATED))
  public void onEmployeeUpdated(Message<EmployeeDto> msg) {
    EmployeeDto dto = msg.getContent();

    log.info("""
        Employee updated event received
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
        dto != null ? dto.getRole() : null);
  }
}
