package com.fatihmayuk.kartaca.backend.service;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;

public interface KafkaService {

     void kafkaSendMessage(TravelDetailDto travelDetailDto);


}
