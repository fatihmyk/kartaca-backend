package com.fatihmayuk.kartaca.backend.service;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListenerService {

    @KafkaListener(
            topics = "${fatihmayuk.kafka.topic}",
            groupId = "${fatihmayuk.kafka.group.id}"
    )
    public void listen(@Payload TravelDetailDto travelDetailDto) {
        log.info("Message received.. GeziId : {} GezininAdi: {} GeziTarihi : {}  Yer : {} GeziKullaniciAdi : {} GeziKullaniciAdSoyadi : {} GeziyeBakanKullanici : {}",
                travelDetailDto.getId(),
                travelDetailDto.getName(),
                travelDetailDto.getDate(),
                travelDetailDto.getLocation(),
                travelDetailDto.getTravelUser().getUsername(),
                travelDetailDto.getTravelUser().getNameSurname(),
                travelDetailDto.getCurrentUserName());
    }
}
