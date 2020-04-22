package com.fatihmayuk.kartaca.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Travel Data Transfer Object")
public class TravelDto {

    @ApiModelProperty(required = true,value = "Travel ID")
    private Long id;

    @ApiModelProperty(required = true,value = "Travel Name")
    private String name;

    @ApiModelProperty(required = true,value = "Travel Date")
    private LocalDate date;

    @ApiModelProperty(required = true,value = "Travel Location")
    private String location;

    @ApiModelProperty(required = true,value = "Travel Notes")
    private String notes;

    @ApiModelProperty(required = true,value = "Travel User")
    private UserDto user;
}
