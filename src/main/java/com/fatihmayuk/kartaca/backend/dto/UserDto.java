package com.fatihmayuk.kartaca.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User Data Transfer Object")
public class UserDto {

    @ApiModelProperty(required = true,value = "User ID")
    private  Long id;

    @ApiModelProperty(required = true,value = "Username Of User")
    @NotNull
    private String username;

    @ApiModelProperty(required = true,value = "Name Surname")
    private String nameSurname;

    @ApiModelProperty(required = true,value = "E-Mail")
    private String email;
}
