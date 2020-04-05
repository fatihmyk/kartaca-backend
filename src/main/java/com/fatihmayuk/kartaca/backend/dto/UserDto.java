package com.fatihmayuk.kartaca.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private  Long id;

    @NotNull
    private String username;

    private String nameSurname;

    private String email;
}
