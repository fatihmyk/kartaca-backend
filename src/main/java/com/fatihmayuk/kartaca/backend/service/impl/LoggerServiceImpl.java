package com.fatihmayuk.kartaca.backend.service.impl;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import com.fatihmayuk.kartaca.backend.dto.TravelDto;
import com.fatihmayuk.kartaca.backend.dto.UserDto;
import com.fatihmayuk.kartaca.backend.model.User;
import com.fatihmayuk.kartaca.backend.service.LoggerService;
import com.fatihmayuk.kartaca.backend.util.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {

    private final ModelMapper modelMapper;
    private final KafkaServiceImpl kafkaService;

    @Override
    public void sendToLoggerApi(TravelDto travelDto) {

        TravelDetailDto travelDetailDto = modelMapper.map(travelDto,TravelDetailDto.class);
        UserDto userDto=modelMapper.map(travelDto.getUser(), UserDto.class);
        travelDetailDto.setCurrentUserName(SecurityContextUtils.getCurrentUser());
        travelDetailDto.setTravelUser(userDto);

        kafkaService.kafkaSendMessage(travelDetailDto);


    }
}
