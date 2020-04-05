package com.fatihmayuk.kartaca.backend.service;

import com.fatihmayuk.kartaca.backend.dto.RegistrationRequestDto;
import com.fatihmayuk.kartaca.backend.dto.UserDto;
import com.fatihmayuk.kartaca.backend.util.TPage;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto getById(Long id);

    TPage<UserDto> getAllPageable(Pageable pageable);

    UserDto getByUsername(String username);

    UserDto update(Long id, UserDto userDto);

    Boolean delete(Long id);

    Boolean register(RegistrationRequestDto registrationRequestDto);



}
