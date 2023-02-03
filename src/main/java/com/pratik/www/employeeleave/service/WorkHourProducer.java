package com.pratik.www.employeeleave.service;

import com.pratik.www.employeeleave.db.entity.EmployeeLeaveEntity;
import com.pratik.www.employeeleave.db.repository.EmployeeLeaveEntityRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class WorkHourProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkHourProducer.class);

    private NewTopic topic;

    @Autowired
    private EmployeeLeaveEntityRepository employeeLeaveRepo;

    private KafkaTemplate<String, EmployeeLeaveEntity> kafkaTemplate;

    public WorkHourProducer(NewTopic topic, KafkaTemplate<String, EmployeeLeaveEntity> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String employeeId, Number yearMonth){
        Optional<EmployeeLeaveEntity> result = employeeLeaveRepo.findByEmployeeId(employeeId)
                .stream()
                .filter(EmployeeLeave -> Objects.equals(EmployeeLeave.getYearMonth().intValue(),
                        yearMonth.intValue())).findFirst();
        LOGGER.info(String.format("Order Event => %s", result.toString()));

        Message<Optional<EmployeeLeaveEntity>> message = MessageBuilder.withPayload(result)
                .setHeader(KafkaHeaders.TOPIC ,topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
