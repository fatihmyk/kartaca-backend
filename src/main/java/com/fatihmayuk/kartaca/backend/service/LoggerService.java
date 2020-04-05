package com.fatihmayuk.kartaca.backend.service;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import com.fatihmayuk.kartaca.backend.dto.TravelDto;

public interface LoggerService {

   public String getCurrentUser();

   TravelDetailDto sendToLoggerApi(TravelDto travelDto, String username);

}
