package com.fatihmayuk.kartaca.backend.service.impl;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import com.fatihmayuk.kartaca.backend.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    @Value("${fatihmayuk.kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, TravelDetailDto> kafkaTemplate;

    @Override
    public void kafkaSendMessage(TravelDetailDto travelDetailDto) {

        kafkaTemplate.send(topic, UUID.randomUUID().toString(),travelDetailDto);


    }
}
