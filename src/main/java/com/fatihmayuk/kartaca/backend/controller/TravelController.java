package com.fatihmayuk.kartaca.backend.controller;

import com.fatihmayuk.kartaca.backend.dto.TravelDetailDto;
import com.fatihmayuk.kartaca.backend.dto.TravelDto;
import com.fatihmayuk.kartaca.backend.service.impl.LoggerServiceImpl;
import com.fatihmayuk.kartaca.backend.service.impl.TravelServiceImpl;
import com.fatihmayuk.kartaca.backend.util.ApiPaths;
import com.fatihmayuk.kartaca.backend.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(ApiPaths.TravelCtrl.CTRL)
@Api(value = ApiPaths.TravelCtrl.CTRL, description = "Travel APIs")
public class TravelController {



    private final LoggerServiceImpl loggerService;
    private final TravelServiceImpl travelServiceImpl;

    public TravelController(  LoggerServiceImpl loggerService, TravelServiceImpl travelServiceImpl) {
        this.loggerService = loggerService;
        this.travelServiceImpl = travelServiceImpl;
    }


    @GetMapping("/pagination")
    @ApiOperation(value = "Travel Get By Pagination Operation", response = TPage.class)
    public ResponseEntity<TPage<TravelDto>> getAllByPagination(@ApiParam("Pageable Object") Pageable pageable) {
        TPage<TravelDto> data = travelServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Travel Get By Id Operation", response = TravelDto.class)
    public ResponseEntity<TravelDto> getById(@PathVariable(value = "id", required = true) @ApiParam(value = "Travel Id") Long id) {
        TravelDto travelDto = travelServiceImpl.getById(id);
        loggerService.sendToLoggerApi(travelDto);

        return ResponseEntity.ok(travelDto);
    }

    @PostMapping()
    @ApiOperation(value = "Travel Create Operation", response = TravelDto.class)
    public ResponseEntity<TravelDto> createTravel(@Valid @RequestBody @ApiParam(value = "TravelDto Object") TravelDto travelDto) {

        return ResponseEntity.ok(travelServiceImpl.save(travelDto));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Travel Create Operation", response = TravelDto.class)
    public ResponseEntity<TravelDto> createTravel(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody @ApiParam(value = "TravelDto Object") TravelDto travelDto) {

        return ResponseEntity.ok(travelServiceImpl.update(id, travelDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Travel Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> deleteProject(@PathVariable(value = "id", required = true) @ApiParam(value = "Travel Id") Long id) {

        return ResponseEntity.ok(travelServiceImpl.delete(id));
    }


}
