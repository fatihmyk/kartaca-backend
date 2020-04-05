package com.fatihmayuk.kartaca.backend.service.impl;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import com.fatihmayuk.kartaca.backend.dto.TravelDto;
import com.fatihmayuk.kartaca.backend.repository.TravelRepository;
import com.fatihmayuk.kartaca.backend.service.LoggerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggerServiceImpl implements LoggerService {

    private final ModelMapper modelMapper;


    public String getCurrentUser(){


        return "";
    }

    @Override
    public TravelDetailDto sendToLoggerApi(TravelDto travelDto, String username) {

        TravelDetailDto travelDetailDto = modelMapper.map(travelDto,TravelDetailDto.class);

        travelDetailDto.setCurrentUserName(username);

        return travelDetailDto;

    }
}
