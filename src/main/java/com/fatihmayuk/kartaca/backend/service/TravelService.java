package com.fatihmayuk.kartaca.backend.service;

import com.fatihmayuk.kartaca.backend.dto.TravelDto;
import com.fatihmayuk.kartaca.backend.util.TPage;
import org.springframework.data.domain.Pageable;

public interface TravelService {

    TravelDto save(TravelDto travelDto);

    TravelDto getById(Long id);

    TPage<TravelDto> getAllPageable(Pageable pageable);

    Boolean delete(Long id);

    TravelDto update(Long id, TravelDto travelDto);

}
