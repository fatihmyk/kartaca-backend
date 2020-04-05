package com.fatihmayuk.kartaca.backend.service;

import com.fatihmayuk.kartaca.backend.dto.TravelDto;

public interface LoggerService {

   void sendToLoggerApi(TravelDto travelDto);

}
