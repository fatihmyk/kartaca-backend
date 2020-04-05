package com.fatihmayuk.kartaca.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelDetailDto {

    private Long id;

    private String name;

    private LocalDate date;

    private String location;

    private String notes;

    private UserDto travelUser;

    private String currentUserName;
}
