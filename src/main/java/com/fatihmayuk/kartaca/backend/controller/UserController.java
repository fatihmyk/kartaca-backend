package com.fatihmayuk.kartaca.backend.controller;

import com.fatihmayuk.kartaca.backend.dto.UserDto;
import com.fatihmayuk.kartaca.backend.service.impl.UserServiceImpl;
import com.fatihmayuk.kartaca.backend.util.ApiPaths;
import com.fatihmayuk.kartaca.backend.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL, description = "User APIs")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl ) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "User Get By Pagination Operation", response = TPage.class)
    public ResponseEntity<TPage<UserDto>> getAllByPagination(@ApiParam(value = "Pageable Object")Pageable pageable){
        TPage<UserDto> data = userServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    @ApiOperation(value = "User Get All By Operation", response = UserDto.class, responseContainer = "List")
    public ResponseEntity<List<UserDto>> getAll(){
        List<UserDto> data = userServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "User Get By Id Operation",response = UserDto.class)
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id",required = true) @ApiParam(value = "User Id") Long id) {
        UserDto userDto= userServiceImpl.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ApiOperation(value = "User Create Operation",response = UserDto.class)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody @ApiParam(value = "UserDto Object") UserDto userDto){

        return ResponseEntity.ok(userServiceImpl.save(userDto));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "User Update Operation",response = UserDto.class)
    public ResponseEntity<UserDto> updateUser(@PathVariable(value = "id",required = true) Long id,@Valid @RequestBody @ApiParam(value = "UserDto Object") UserDto userDto){

        return ResponseEntity.ok(userServiceImpl.update(id,userDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "User Delete Operation",response = Boolean.class)
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id",required = true) @ApiParam(value = "User Id") Long id){
        return ResponseEntity.ok(userServiceImpl.delete(id));
    }


}
