package com.fatihmayuk.kartaca.backend.service.impl;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import com.fatihmayuk.kartaca.backend.dto.TravelDto;
import com.fatihmayuk.kartaca.backend.dto.UserDto;
import com.fatihmayuk.kartaca.backend.model.Travel;
import com.fatihmayuk.kartaca.backend.model.User;
import com.fatihmayuk.kartaca.backend.repository.TravelRepository;
import com.fatihmayuk.kartaca.backend.repository.UserRepository;
import com.fatihmayuk.kartaca.backend.service.TravelService;
import com.fatihmayuk.kartaca.backend.util.TPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TravelServiceImpl implements TravelService {

    private final TravelRepository travelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public TravelDto save(TravelDto travelDto) {

        Date date = new Date();
        //Getting the default zone id
        ZoneId defaultZoneId = ZoneId.systemDefault();
        //Converting the date to Instant
        Instant instant = date.toInstant();
        //Converting the Date to LocalDate
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();

        travelDto.setDate(localDate);


        Travel traveldb = modelMapper.map(travelDto,Travel.class);
        traveldb = travelRepository.save(traveldb);
        return modelMapper.map(traveldb,TravelDto.class);
    }

    @Override
    public TravelDto getById(Long id) {

        Travel travel = travelRepository.getOne(id);
        return modelMapper.map(travel, TravelDto.class);

    }

    @Override
    public TPage<TravelDto> getAllPageable(Pageable pageable) {
        Page<Travel> data = travelRepository.findAll(pageable);
        TPage<TravelDto> tPage = new TPage<TravelDto>();
        TravelDto[] travelDtos = modelMapper.map(data.getContent(), TravelDto[].class);
        tPage.setStat(data, Arrays.asList(travelDtos));
        return tPage;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        travelRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public TravelDto update(Long id, TravelDto travelDto) {
        Travel traveldb = travelRepository.getOne(id);
        UserDto userDto = travelDto.getUser();
        User user = modelMapper.map(userDto,User.class);

        traveldb.setName(travelDto.getName());
        traveldb.setDate(travelDto.getDate());
        traveldb.setLocation(travelDto.getLocation());
        traveldb.setNotes(travelDto.getNotes());
        traveldb.setUser(user);
        travelRepository.save(traveldb);

       return modelMapper.map(traveldb,TravelDto.class);
    }
}
